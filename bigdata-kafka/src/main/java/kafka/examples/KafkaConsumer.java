package kafka.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.ConsumerTimeoutException;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

public class KafkaConsumer {
	
	private Properties kafkaProps = new Properties();
	private ConsumerConnector consumer;
	private ConsumerConfig config;
	private KafkaStream<String, String> stream;
	private String waitTime;

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Error...");
			return;
		}
		String next;
		int num;
		KafkaConsumer consumer = new KafkaConsumer();
		String zkUrl = args[0];
		String groupId = args[1];
		String topic = args[2];
		int window = Integer.parseInt(args[3]);
		consumer.waitTime = args[4];
		
		CircularFifoBuffer buffer = new CircularFifoBuffer(window);
		consumer.configure(zkUrl, groupId);
		consumer.start(topic);
		
		while ((next = consumer.getNextMessage()) != null) {
			int sum = 0;
			try {
				num = Integer.parseInt(next);
				buffer.add(num);
			} catch (NumberFormatException e) {}
			
			for (Object object : buffer) {
				sum += (Integer) object;
			}
			if(buffer.size() > 0) {
				System.out.println("Moving avg is:: " + sum / buffer.size());
			}
		}
		
		consumer.consumer.shutdown();
		System.exit(0);
	}

	private String getNextMessage() {
		ConsumerIterator<String, String> iter = stream.iterator();
		try {
			return iter.next().message();
		} catch (ConsumerTimeoutException e) {
			System.out.println("Error");
			return null;
		}
	}

	private void start(String topic) {
		consumer = Consumer.createJavaConsumerConnector(config);
		
		Map<String, Integer> topicConfig = new HashMap<>();
		topicConfig.put(topic, new Integer(1));
		
		StringDecoder decoder = new StringDecoder(new VerifiableProperties());
		
		stream = consumer.createMessageStreams(topicConfig, decoder, decoder).get(topic).get(0);
		
	}

	private void configure(String zkUrl, String groupId) {
		kafkaProps.put("", "");
		
		config = new ConsumerConfig(kafkaProps);
	}
}
