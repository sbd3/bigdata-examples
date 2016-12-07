package spark.jscript.example;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

public class JScriptSpark implements Serializable {

    private static final long serialVersionUID = 2173160634301125849L;

    public static void main(String[] args) throws NoSuchMethodException, ScriptException, NoSuchAlgorithmException {
	SparkSession session = SparkSession.builder().appName("JSTest").master("local[*]").config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
		.config("spark.sql.warehouse.dir", "D:/downloads/sampledata/warehouse").getOrCreate();
	SparkContext sc = session.sparkContext();
	JavaSparkContext jsc = new JavaSparkContext(sc);

	JScriptSpark obj = new JScriptSpark();
	Dataset<Row> df = obj.getDataFrame(session.sqlContext());
	obj.executeJScript(df, jsc);
    }

    private ScriptEngine getEngine() throws ScriptException {
	ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	engine.eval("var fun1 = function(id) { " + "if(!id) return \"_nullable\"; else return id+ \"_cust\";" + "};");
	return engine;
    }

    private void executeJScript(Dataset<Row> df, JavaSparkContext jCtx) throws ScriptException, NoSuchMethodException {

	// final Broadcast<Invocable> broadcastVar =jCtx.broadcast(getInvocable());
	final Broadcast<ScriptContext> broadcastVar = jCtx.broadcast(getEngine().getContext());
	final int size = df.schema().size();

	JavaRDD<Row> rdd = df.toJavaRDD().map(new Function<Row, Row>() {
	    private static final long serialVersionUID = -3407691374762911971L;
	    ScriptEngine engine = null;
	    Invocable invocable = null;

	    public Row call(Row tblRow) throws Exception {
		if (invocable == null) {
		    long s = System.currentTimeMillis();
		    engine = new ScriptEngineManager().getEngineByName("nashorn");
		    engine.setContext(broadcastVar.getValue());
		    invocable = (Invocable) engine;
		    long e = System.currentTimeMillis();
		    System.out.println("creation time: " + (e - s));
		}

		Object[] newRow = new Object[size + 1];
		int rowSize = tblRow.length();
		for (int itr = 0; itr < rowSize; itr++) {
		    if (tblRow.apply(itr) != null) {
			newRow[itr] = tblRow.apply(itr);
		    }
		}
		newRow[size] = (String) invocable.invokeFunction("fun1", tblRow.get(tblRow.fieldIndex("sender")));
		return RowFactory.create(newRow);
	    }
	});

	List<Row> rows = rdd.collect();
	for (Row row : rows) {
	    System.out.println(row.get(row.size() - 1));
	}

    }

    private Dataset<Row> getDataFrame(SQLContext sqlCtx) {
	sqlCtx.udf().register("stringLengthTest", new UDF1<String, Integer>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Integer call(String str) {
		return str.length();
	    }
	}, DataTypes.IntegerType);
	return sqlCtx.read().json("file:///D:/downloads/sampledata/enron.json");
    }

}
