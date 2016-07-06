package spark.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import spark.examples.vo.Person;

public class DataFrameTest {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("JSTest").setMaster("local[*]");
		JavaSparkContext jCtx = new JavaSparkContext(conf);
		SQLContext sqlCtx = new SQLContext(jCtx);
		JavaRDD<Person> rdd = jCtx.textFile("d:/input.txt").map(line -> {
			String[] arr = line.split(" ");
			Person p = new Person();
			p.setId(Integer.parseInt(arr[0]));
			p.setName(arr[1]);
			return p;
		});
		
		DataFrame df = sqlCtx.createDataFrame(rdd, Person.class);
		df.toJavaRDD().map(row -> {
			System.out.println(Arrays.asList(row.schema().fieldNames()).contains("age"));
			return row;
		}).count();
		//addColumnWithQuery(sqlCtx, df);
		//addColumn(sqlCtx, df);
		//System.out.println("++ "+Arrays.asList(df.columns()).contains("age1"));
		//DataFrame df1 = df.withColumn("sal", new Column("age"));
		
	}

	private static void addColumnWithQuery(SQLContext sqlCtx, DataFrame df) {
		df.registerTempTable("temp");
		DataFrame newDF = sqlCtx.sql("select *, true as abc from temp");
		sqlCtx.dropTempTable("temp");
		newDF.show();
		for(StructField field : newDF.schema().fields()) {
			System.out.println(field.toString());
		}
	}

	private static void addColumn(SQLContext sqlCtx, DataFrame df) {
		StructField fieldNew = new StructField("abc", DataTypes.StringType, true, new Metadata());
		
		List<StructField> fields = new ArrayList<>();
		for (StructField f : df.schema().fields()) {
			fields.add(f);
		}
		fields.add(fieldNew);
		StructType schema =  DataTypes.createStructType(fields);
		DataFrame outputDF = sqlCtx.createDataFrame(df.toJavaRDD().map(row -> {
			Object[] arr = new Object[row.size() + 1];
			for (int i = 0; i < row.size(); i++) {
				arr[i] = row.get(i);
			}
			arr[row.size()] = "ok";
			return RowFactory.create(arr);
		}), schema);
		outputDF.show();
		DataFrame out = outputDF.withColumn("abc", outputDF.col("abc"), new Metadata());
		out.show();
	}
	
	/*private StructType getNewStructType(StructType oldStructType, List<Field> destFields) {
		for (Field field : destFields) {
			boolean found = false;
			for (String fieldName : oldStructType.fieldNames()) {
				if(fieldName.equalsIgnoreCase(field.getName())) {
					found = true;
				}
			}
			if(!found) {
				oldStructType.add(new StructField(field.getName(), field.getDataType(), true, new Metadata()));
			}
		}
		return oldStructType;
	}*/
}
