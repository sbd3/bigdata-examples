package spark.examples;

import org.apache.spark.Partition;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class ZipWithIndexTest {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf()
				.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
				.setAppName("JSTest")
				.setMaster("spark://ip-10-0-10-139.ec2.internal:7077");
		JavaSparkContext sc = new JavaSparkContext(conf);
		/*List<Integer> integers = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			integers.add(i);
		}*/
		JavaPairRDD<String, Long> rdd = sc.textFile("d:/seq.txt", 4).zipWithIndex();
		for(Partition partition: rdd.splits()) {
			System.out.println("Index:: "+partition.index());
		}
		System.out.println(rdd.count());
		rdd.foreach(System.out::println);
		sc.close();
	}
}
