package spark.hbase.examples;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkHBaseIntegration {

  private SparkConf sparkConf;
  private JavaSparkContext jCtx;

  private void init() throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
    sparkConf = new SparkConf().setAppName("JSTest").setMaster("local[*]");
    sparkConf.set("spark.executor.extraClassPath", "$(hbase classpath)");
    System.setProperty("hadoop.home.dir", "C:\\hadoop\\hadoop-common-2.2.0-bin-master");
    System.setProperty("user.name", "hdfs");
    System.setProperty("HADOOP_USER_NAME", "hdfs");

    jCtx = new JavaSparkContext(sparkConf);
  }

  private Configuration getConf() {
    Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.master", "10.5.3.166:16000");
    conf.set("timeout", "120000");
    conf.set("hbase.zookeeper.quorum", "10.5.3.166:2181");
    conf.set("zookeeper.znode.parent", "/hbase-unsecure");
    conf.set(TableInputFormat.INPUT_TABLE, "books");
    return conf;
  }

  public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
    SparkHBaseIntegration test = new SparkHBaseIntegration();
    test.readHBase();
  }

  private void readHBase() throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
    init();
    JavaPairRDD<ImmutableBytesWritable, Result> hBasePairRDD = jCtx.newAPIHadoopRDD(getConf(), TableInputFormat.class, ImmutableBytesWritable.class, Result.class);

    JavaRDD<String> hBaseRDD = hBasePairRDD.map(v1 -> {
      byte[] o = v1._2().getValue(Bytes.toBytes("books"), Bytes.toBytes("author"));
      if (o != null) {
        return Bytes.toString(o);
      }
      return "";
    });

    hBaseRDD.collect().forEach(x -> System.out.println(x));
  }

}
