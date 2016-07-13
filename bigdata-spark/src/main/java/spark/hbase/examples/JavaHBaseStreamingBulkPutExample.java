package spark.hbase.examples;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.spark.JavaHBaseContext;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * This is a simple example of BulkPut with Spark Streaming
 */
final public class JavaHBaseStreamingBulkPutExample {

  private JavaHBaseStreamingBulkPutExample() {
  }

  public static void main(String[] args) {

    SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("JavaHBaseStreamingBulkPutExample ");

    JavaSparkContext jsc = new JavaSparkContext(sparkConf);

    try {
      JavaStreamingContext jssc = new JavaStreamingContext(jsc, new Duration(1000));

      JavaReceiverInputDStream<String> javaDstream = jssc.socketTextStream("10.5.3.166", Integer.parseInt("9999"));
      /*JavaPairReceiverInputDStream<String, String> stream = KafkaUtils.createStream(jssc, "10.5.3.166", "group", getKafkaTopics());
      JavaDStream<String> javaDstream = stream.map(new Function<Tuple2<String, String>, String>() {
        private static final long serialVersionUID = -334361041187559656L;
        @Override
        public String call(Tuple2<String, String> v1) throws Exception {
          return v1._2;
        }
      });*/

      JavaHBaseContext hbaseContext = new JavaHBaseContext(jsc, getConf());

      hbaseContext.streamBulkPut(javaDstream, TableName.valueOf("testtable"), new PutFunction());
      jssc.start();
      jssc.awaitTermination();
    } finally {
      jsc.stop();
    }
  }
  
  private static Configuration getConf() {
    Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.master", "10.5.3.166:16000");
    conf.set("timeout", "120000");
    conf.set("hbase.zookeeper.quorum", "10.5.3.166:2181");
    conf.set("zookeeper.znode.parent", "/hbase-unsecure");
    conf.set(TableInputFormat.INPUT_TABLE, "testtable");
    return conf;
  }

  private static Map<String, Integer> getKafkaTopics() {
    Map<String, Integer> topics = new HashMap<String, Integer>();
    topics.put("visits", 1);
    return topics;
  }

  public static class PutFunction implements Function<String, Put> {

    private static final long serialVersionUID = 1L;

    public Put call(String v) throws Exception {
      String[] part = v.split(",");
      Put put = new Put(Bytes.toBytes(part[0]));

      put.addColumn(Bytes.toBytes(part[1]), Bytes.toBytes(part[2]), Bytes.toBytes(part[3]));
      return put;
    }

  }
}
