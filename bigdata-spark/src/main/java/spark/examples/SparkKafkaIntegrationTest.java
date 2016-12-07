package spark.examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

public class SparkKafkaIntegrationTest {

	private SparkConf conf;
	private JavaStreamingContext jssc;
	
	private static final Pattern SPACE = Pattern.compile(" ");

	private void init() throws InterruptedException {
		conf = new SparkConf().setAppName("JSTest").setMaster("local[*]");
		conf.set("spark.cassandra.connection.host", "localhost");
		jssc = new JavaStreamingContext(conf, new Duration(1L));

		JavaPairReceiverInputDStream<String, String> stream = KafkaUtils.createStream(jssc, "zkgrp", "group", getKafkaTopics());
		JavaDStream<String> lines = stream.map(tuple -> tuple._2);
		JavaPairDStream<String, Long> words = lines.flatMap(new FlatMapFunction<String, String>() {
		    private static final long serialVersionUID = 5951063943727167813L;
		    @Override
		    public Iterator<String> call(String line) throws Exception {
			return Arrays.asList(SPACE.split(line)).iterator();
		    }
		}).countByValue();
		words.print();
		jssc.start();
		jssc.awaitTermination();
	}

	private Map<String, Integer> getKafkaTopics() {
		Map<String, Integer> topics = new HashMap<String, Integer>();
		topics.put("test", 1);
		return topics;
	}

	public static void main(String[] args) throws InterruptedException {
		SparkKafkaIntegrationTest test = new SparkKafkaIntegrationTest();
		test.init();
	}
}
