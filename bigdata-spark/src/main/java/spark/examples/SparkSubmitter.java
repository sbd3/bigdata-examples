package spark.examples;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.deploy.SparkSubmit;

public class SparkSubmitter {

  // Name of the jar to be deployed.
  private static final String JAR_NAME = "spark-jar-to-be-submitted-1.0.0.jar";

  public static void main(String[] args) throws URISyntaxException, IOException {
    SparkSubmit.main(buildArgsList());
  }

  private static String[] buildArgsList() {
    List<String> sparkArgList = new ArrayList<>();
    sparkArgList.add("--master");
    sparkArgList.add("spark://<master-ip>:6066");
    sparkArgList.add("--deploy-mode");
    sparkArgList.add("cluster");
    sparkArgList.add("--driver-memory");
    sparkArgList.add("4G");
    sparkArgList.add("--executor-memory");
    sparkArgList.add("8G");
    sparkArgList.add("--class");
    
    //sparkArgList.add("--jars");
    //sparkArgList.add("file:///home/puneet/antlr4-runtime-4.5.1.jar,file:///home/puneet/analytics_engine-1.0.0.jar");
    
    // Needs to contain the main class name.
    sparkArgList.add(SparkSubmitter.class.getName());
    sparkArgList.add(getJarPath());
    
    // Application parameters that you need to pass
    sparkArgList.add("params");
    
    String[] sparkArgs = new String[sparkArgList.size()];
    sparkArgList.toArray(sparkArgs);
    return sparkArgs;
  }

  private static String getJarPath() {
    String path = new File(".").getAbsolutePath();
    return path.substring(0, path.length() - 1) + JAR_NAME;
  }
}
