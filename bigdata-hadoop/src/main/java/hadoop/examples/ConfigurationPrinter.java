package hadoop.examples;

import java.io.IOException;
import java.net.URI;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ConfigurationPrinter extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new ConfigurationPrinter(), args);
	}

	public int run(String[] paramArrayOfString) throws Exception {
		Configuration conf = getConf();
		for (Entry<String, String> entry : conf) {
			System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
		}
		
		String[] args = new GenericOptionsParser(getConf(), paramArrayOfString).getRemainingArgs();
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(ConfigurationPrinter.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		
		
		return 0;
	}

	public static class TempMapper extends Mapper<LongWritable, Text, Text, Text> {

		@Override
		protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			super.setup(context);
			URI[] uris = context.getCacheFiles();
			System.out.println(uris[0].getPath());
		}

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			super.map(key, value, context);
			context.getCounter("groupname", "countername");
		}
	}

}
