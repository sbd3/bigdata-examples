package spark.examples;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.hadoop.hdfs.protocol.proto.NamenodeProtocolProtos.GetEditLogManifestRequestProto;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import scala.Tuple2;

public class JScriptSparkTest implements Serializable {

	private static final long serialVersionUID = 2173160634301125849L;

	public static void main(String[] args) throws NoSuchMethodException, ScriptException, NoSuchAlgorithmException {
		SparkConf conf = new SparkConf()
				.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
				.setAppName("JSTest")
				.setMaster("local[*]");
		JavaSparkContext jCtx = new JavaSparkContext(conf);
		SQLContext sqlCtx = new SQLContext(jCtx);

		JScriptSparkTest obj = new JScriptSparkTest();
		DataFrame df = obj.getDataFrame(sqlCtx);
		obj.executeJScript(df, jCtx);
		//obj.transform(df);
	}

	private ScriptEngine getEngine() throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("var fun1 = function(id) { " 
				+ "if(!id) return \"_nullable\"; else return id+ \"_cust\";"
				+ "};");
		return engine;
	}

	private void executeJScript(DataFrame df, JavaSparkContext jCtx) throws ScriptException, NoSuchMethodException {

		//final Broadcast<Invocable> broadcastVar = jCtx.broadcast(getInvocable());
		final Broadcast<ScriptContext> broadcastVar = jCtx.broadcast(getEngine().getContext());
		
		final int size = df.schema().size();
		JavaRDD<Row> rdd = df.toJavaRDD().mapPartitions(new FlatMapFunction<Iterator<Row>, Row>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Iterable<Row> call(Iterator<Row> rowIter) throws Exception {
				while(rowIter.hasNext()) {
					Row row = rowIter.next();
				}
				return null;
			}
		});
		/*JavaRDD<Row> rdd = df.toJavaRDD().map(new Function<Row, Row>() {
			private static final long serialVersionUID = -3407691374762911971L;
			ScriptEngine engine = null;
			Invocable invocable = null;
			public Row call(Row tblRow) throws Exception {
				if(invocable == null) {
					long s = System.currentTimeMillis();
					engine = new ScriptEngineManager().getEngineByName("nashorn");
					engine.setContext(broadcastVar.getValue());
					invocable = (Invocable) engine;
					long e = System.currentTimeMillis();
					System.out.println("created " + (e-s));
				}
				
				Object[] newRow = new Object[size + 1];
				int rowSize = tblRow.length();
				for (int itr = 0; itr < rowSize; itr++) {
					if (tblRow.apply(itr) != null) {
						newRow[itr] = tblRow.apply(itr);
					}
				}
				// System.out.println(tblRow.getClass());
				newRow[size] = (String) invocable.invokeFunction("fun1", tblRow.get(tblRow.fieldIndex("sender")));
				return RowFactory.create(newRow);
			}
		});*/
		List<Row> rows = rdd.collect();
		for (Row row : rows) {
			System.out.println(row.get(row.size()-1));
		}

		// String newColName = "iid";
		// df.schema().printTreeString();
		// DataFrame df1 = df.withColumn(newColName, df.col("date"));
		// Row row = df1.take(1)[0];

		// df1.schema().printTreeString();
		// StructType schema = df1.schema();

		// System.out.println(row.get(row.fieldIndex(newColName)) + "|" +
		// row.get(row.fieldIndex("date")));

		/*
		 * df1.foreach(new AbstractFunction1<Row, BoxedUnit>() {
		 * 
		 * @Override public BoxedUnit apply(Row row) { try { Object val =
		 * invocable.invokeFunction("fun1", row.get(row.fieldIndex("$oid")));
		 * row. } catch (NoSuchMethodException | ScriptException e) {
		 * e.printStackTrace(); } return BoxedUnit.UNIT; } });
		 */
	}
	
	private void transform(DataFrame df) throws NoSuchAlgorithmException {
		final HashMap<String, Object> rulesMap = getRules();
		final List<Transformer> transList = getRulesList();

		JavaPairRDD<String, Row> rdd = df.toJavaRDD().mapToPair(new PairFunction<Row, String, Row>() {
			@Override
			public Tuple2<String, Row> call(Row row) throws Exception {
				final MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(row.mkString().getBytes(), 0, row.mkString().length());
				return new Tuple2<String, Row>(new BigInteger(1, md.digest()).toString(16), row);
			}
		});

		JavaPairRDD<String, Row> validRDD = rdd.mapToPair(new PairFunction<Tuple2<String, Row>, String, Row>() {
      private static final long serialVersionUID = -8062114787861876347L;

      @Override
			public Tuple2<String, Row> call(Tuple2<String, Row> row) throws Exception {
				Row newRow = row._2;
				for (Transformer trans : transList) {
					newRow = trans.transform(newRow, rulesMap);
				}
				return new Tuple2<String, Row>(row._1, newRow);
			}
		});

		JavaPairRDD<String, Row> invalidRDD = rdd.subtractByKey(validRDD);

		System.out.println(rdd.count() + ":" + validRDD.count() + ":" + invalidRDD.count());

	}

	private List<Transformer> getRulesList() {
		List<Transformer> transList = new ArrayList<>();
		Transformer trans1 = new ConcatTransform();
		transList.add(trans1);
		return transList;
	}

	private HashMap<String, Object> getRules() {
		final HashMap<String, Object> rulesMap = new HashMap<>();
		String ruleName = "concat_3_cols";
		List<String> cols = new ArrayList<>();
		cols.add("sender");
		cols.add("recipients");
		rulesMap.put(ruleName, cols);
		return rulesMap;
	}

	private DataFrame getDataFrame(SQLContext sqlCtx) {
		sqlCtx.udf().register("stringLengthTest", new UDF1<String, Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(String str) {
				return str.length();
			}
		}, DataTypes.IntegerType);
		return sqlCtx.read().json("D:/downloads/enron.json");
		// return
		// sqlCtx.read().json("hdfs://<namenode>:<port>/user/hdfs/enron.json");
	}

}
