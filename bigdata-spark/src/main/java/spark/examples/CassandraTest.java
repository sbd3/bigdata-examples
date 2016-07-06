package spark.examples;

import java.util.UUID;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

import spark.examples.vo.Person;

public class CassandraTest {

	private SparkConf conf;
	private JavaSparkContext jCtx;
	private SQLContext sqlCtx;

	private void init() {
		conf = new SparkConf().setAppName("JSTest").setMaster("local[*]");
		conf.set("spark.cassandra.connection.host", "localhost");
		jCtx = new JavaSparkContext(conf);
		sqlCtx = new SQLContext(jCtx);
	}

	public static void main(String[] args) {
		CassandraTest test = new CassandraTest();
		test.init();
		test.testCassandra();
		// test.runMap1();
		// test.runMap2();
		// test.runAction();
		// javaFunctions(rdd).writerBuilder("", "", )
	}

	private void testCassandra() {
		JavaRDD<Person> rdd = jCtx.textFile("d:/input.txt").map(line -> {
			String[] arr = line.split(" ");
			Person p = new Person();
			p.setId(Integer.parseInt(arr[0]));
			p.setName(arr[1]);
			p.setUid(UUID.randomUUID().toString());
			return p;
		});
		DataFrame df = sqlCtx.createDataFrame(rdd, Person.class);
		df.show();
		df.write().format("org.apache.spark.sql.cassandra").option("keyspace", "cdip").option("table", "emp")
				.mode(SaveMode.Append).save();
	}

	/*
	 * private void runMap1() { rdd = jCtx.textFile("d:/input.txt").map(line ->
	 * { String[] arr = line.split(" "); Person1 p = new Person1();
	 * p.setAge(Integer.parseInt(arr[0])); p.setName(arr[1]); return p; });
	 * rdd.coalesce(1).foreach(System.out::println); }
	 * 
	 * private void runMap2() { rdd = rdd.map(p -> { p.setAge(0); return p; });
	 * }
	 * 
	 * private void runAction() { System.out.println(rdd.count()); }
	 */

	/*
	 * private void createRow() { Object[] rowArr = new Object[2]; rowArr[0] =
	 * "null val"; rowArr[1] = true; StructType schema =
	 * DataTypes.createStructType(new StructField[] {
	 * DataTypes.createStructField("id", DataTypes.IntegerType, false),
	 * DataTypes.createStructField("name", DataTypes.StringType, false)}); Row
	 * row = RowFactory.create(rowArr); System.out.println(row.schema()); for
	 * (int i = 0; i < row.size(); i++) { System.out.println(row.get(i)); } }
	 */
}