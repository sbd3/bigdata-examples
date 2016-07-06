package kafka.examples;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaOldProducer {
	
	private Properties kafkaProps = new Properties();
	private Producer<String, String> producer;
	private ProducerConfig config;
	private String topic;

	public static void main(String[] args) throws InterruptedException {
		if(args.length == 0) {
			System.out.println("OldProducer {broker-list} {topic} {sync} {delay} {count}");
			return;
		}
		KafkaOldProducer counter = new KafkaOldProducer();
		String brokerList = args[0];
		counter.topic = args[1];
		String sync = args[2];
		int delay = Integer.parseInt(args[3]);
		int count = Integer.parseInt(args[4]);
		
		counter.configure(brokerList, sync);
		counter.start();
		
		long startTime = System.currentTimeMillis();
		System.out.println("Starting...");
		counter.produce("Starting...");
		for (int i = 0; i < count; i++) {
			counter.produce(Integer.toString(i));
			Thread.sleep(delay);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken:: " + (endTime-startTime));
		counter.produce("Time taken:: " + (endTime-startTime));
		counter.producer.close();
		System.exit(0);
	}
	
	private void configure(String brokerList, String sync) {
		kafkaProps.put("metadata.broker.list", brokerList);
		kafkaProps.put("serializer.class", "kafka.serializer.StringEncoder");
		kafkaProps.put("request.required.acks", "1");
		kafkaProps.put("producer.type", sync);
		
		config = new ProducerConfig(kafkaProps);
	}
	
	private void produce(String str) {
		KeyedMessage<String, String> message = new KeyedMessage<String, String>(topic, null, str);
		producer.send(message);
	}

	private void start() {
		producer = new Producer<>(config);
	}

	
}
