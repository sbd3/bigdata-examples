package spark.hbase.examples;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.google.common.collect.Lists;

import scala.Tuple2;
import spark.examples.util.ProcessXMLStream;
import spark.examples.vo.DataPayload;

public class SparkStreamingStaticMethod implements Serializable {

  private static final long serialVersionUID = -1265236397259062958L;

  public static Map<String, Integer> getKafkaTopics() {
		Map<String, Integer> topics = new HashMap<String, Integer>();
		topics.put("visits", 1);
		return topics;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParserConfigurationException, IOException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hdfs");
		SparkConf conf;
		JavaStreamingContext jssc;
		JavaPairReceiverInputDStream<String, String> stream;
		JavaDStream<String> lines;
		JavaDStream<String> filteredLinesTwo;
		Pattern SPACE = Pattern.compile(" ");

		conf = new SparkConf().setAppName("JSTest").setMaster("spark://10.5.3.166:7077");

		// conf = new SparkConf().setAppName("JSTest").setMaster("local[*]");

		/* conf.set("org.apache.spark.sql.SaveMode", "Append"); */

		jssc = new JavaStreamingContext(conf, new Duration(5000L));

		stream = KafkaUtils.createStream(jssc, "10.5.3.166:2181", "group", getKafkaTopics());

		lines = stream.map(tuple -> tuple._2);

		/*
		 * filteredLinesTwo = lines.filter(new Function<String, Boolean>() {
		 * 
		 * public Boolean call(String x) throws Exception {
		 * 
		 * 
		 * return x.contains("gautam"); } });
		 */

		JavaDStream<DataPayload> dp = lines.map(new Function<String, DataPayload>() {

			DataPayload d = new DataPayload();

			@Override
			public DataPayload call(String arg0) throws Exception {
				ProcessXMLStream pxs = new ProcessXMLStream();
				List<List<Object>> data = pxs.getXMLData(arg0);
				for (List<Object> lo : data) {
					String payload = "";
					for (Object o : lo) {
						payload = payload + "|" + (String) o;

					}
					d.payload.add(payload.substring(1, payload.length()));
				}

				return d;
			}

		});

		dp.foreachRDD(new VoidFunction<JavaRDD<DataPayload>>() {
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void call(JavaRDD<DataPayload> paramT1) throws Exception {
			paramT1.saveAsTextFile("hdfs://10.5.3.166:8020/user/storm/demo/wordcount");
		    }
		});

		// filteredLinesTwo.print();
		dp.print();
		jssc.start();
		jssc.awaitTermination();

	}
}