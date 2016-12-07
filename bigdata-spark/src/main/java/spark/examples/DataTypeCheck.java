package spark.examples;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import spark.examples.vo.DOB;

public class DataTypeCheck {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("JSTest").setMaster("local[*]");
		JavaSparkContext jCtx = new JavaSparkContext(conf);
		SQLContext sqlCtx = new SQLContext(jCtx);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		JavaRDD<DOB> rdd = jCtx.textFile("d:/input1.txt").map(line -> {
			String[] arr = line.split("\t");
			DOB p = new DOB();
			p.setId(Integer.parseInt(arr[0]));
			p.setName(arr[1]);
			p.setDob(new Date(format.parse(arr[2]).getTime()));
			return p;
		});
		
		Dataset<Row> df = sqlCtx.createDataFrame(rdd, DOB.class);
		df.show();
		df.printSchema();
	}

}
