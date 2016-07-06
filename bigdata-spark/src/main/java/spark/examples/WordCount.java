package spark.examples;

import org.apache.spark.Accumulator;
import org.apache.spark.HashPartitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;

import scala.Tuple2;

public class WordCount {

	public static void main(String[] args) {
		JavaSparkContext sc = new JavaSparkContext();
		/*
		 * JavaRDD<String> input = sc.textFile("d:/input.txt"); Map<String,
		 * Long> val = input.flatMap(x -> Arrays.asList(x.split(" "
		 * ))).countByValue(); val.entrySet().forEach(System.out::println);
		 */
		final Accumulator<Integer> acc = sc.accumulator(0);
		JavaRDD<String> input = sc.textFile("d:/input.txt");
		JavaPairRDD<String, String> pairRDD = input.mapToPair(line -> {
			acc.add(1);
			return new Tuple2<String, String>(line.split(" ")[0], line.split(" ")[1]);
		});
		JavaPairRDD<String, String> partitionedRDD = pairRDD.partitionBy(new HashPartitioner(100)).persist(StorageLevel.MEMORY_AND_DISK());
		partitionedRDD.foreach(System.out::println);
		
		//JdbcRDD<String> rdd = new JdbcRDD<String>(sc, getConnection, sql, lowerBound, upperBound, numPartitions, mapRow, evidence$1);
		
	}
}
