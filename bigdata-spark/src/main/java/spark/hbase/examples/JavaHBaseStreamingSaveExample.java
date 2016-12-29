package spark.hbase.examples;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapred.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapred.JobConf;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;

/**
 * This is a simple example of BulkPut with Spark Streaming
 */
final public class JavaHBaseStreamingSaveExample {

    private JavaHBaseStreamingSaveExample() {
    }

    public static void main(String[] args) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("JavaHBaseStreamingSaveExample ");

        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        JavaStreamingContext jssc = new JavaStreamingContext(jsc, new Duration(1000));
        try {
            JavaReceiverInputDStream<String> javaDstream = jssc.socketTextStream("10.5.3.166", Integer.parseInt("9999"));
            javaDstream.foreachRDD(rdd -> {
                JavaPairRDD<String, String> pair = rdd.mapToPair(row -> new Tuple2<>("", row));
                pair.saveAsHadoopDataset(getJobConf());
            });
            jssc.start();
            jssc.awaitTermination();
        } finally {
            jssc.stop();
            jsc.stop();
        }
    }
    
    private static JobConf getJobConf() {
        JobConf jobConfig = new JobConf();
        jobConfig.set(TableOutputFormat.OUTPUT_TABLE, "testtablecreate");
        jobConfig.setOutputFormat(TableOutputFormat.class);
        return jobConfig;
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

    private static void createTable() {
        try (Connection conn = ConnectionFactory.createConnection(getConf()); HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();){
            if (!admin.isTableEnabled(TableName.valueOf("testtablecreate"))) {
                System.out.println("inserting data");
                HTableDescriptor hbaseTable = new HTableDescriptor(TableName.valueOf("testtablecreate"));
                HColumnDescriptor booking = new HColumnDescriptor("booking");
                HColumnDescriptor complex = new HColumnDescriptor("complex");
                hbaseTable.addFamily(booking);
                hbaseTable.addFamily(complex);
                admin.createTable(hbaseTable);
                System.out.println("Table created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
