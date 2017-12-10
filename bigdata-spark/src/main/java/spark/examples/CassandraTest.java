package spark.examples;

import java.util.HashMap;
import java.util.UUID;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import spark.examples.vo.Person;

public class CassandraTest {

    private JavaSparkContext jCtx;
    private SQLContext sqlCtx;

    private void init() {
        SparkConf conf = new SparkConf();
        conf.set("spark.cassandra.connection.host", "96.118.152.55");
        conf.set("spark.cassandra.connection.port", "9042");
        SparkSession sparkSession = SparkSession.builder().master("local[3]").config(conf).appName("Test")
                .getOrCreate();
        Dataset<Row> monthlyTruckRolls1 = sparkSession.read().format("org.apache.spark.sql.cassandra")
                .options(new HashMap<String, String>() {
                    private static final long serialVersionUID = -1791084642785094154L;
                    {
                        put("table", "monthly_truck_rolls_1");
                        put("keyspace", "comcastforecasting");
                    }
                }).load();

        monthlyTruckRolls1.createOrReplaceTempView("monthly_truck_rolls_1");

        Dataset<Row> monthlyTruckRolls2 = sparkSession.read().format("org.apache.spark.sql.cassandra")
                .options(new HashMap<String, String>() {
                    private static final long serialVersionUID = -305828187820274883L;
                    {
                        put("table", "monthly_truck_rolls_2");
                        put("keyspace", "comcastforecasting");
                    }
                }).load();

        monthlyTruckRolls2.createOrReplaceTempView("monthly_truck_rolls_2");

        Dataset<Row> df1 = sparkSession.sql("SELECT monthly_truck_rolls_1.region_name, " + "monthly_truck_rolls_1.year, "
                + "monthly_truck_rolls_1.month, " + "truck_roll_type, " + "truck_roll_count, " + "subscribers, "
                + "connects, " + "change_of_service, " + "equivalent_weeks, " + "actuals_projected "
                + "FROM monthly_truck_rolls_1 "
                + "INNER JOIN monthly_truck_rolls_2 USING (region_name, year, month,actuals_projected) "
                + "where actuals_projected = 'A'");

        df1.show();
        sparkSession.close();
    }

    public static void main(String[] args) {
        CassandraTest test = new CassandraTest();
        test.init();
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
        Dataset<Row> df = sqlCtx.createDataFrame(rdd, Person.class);
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