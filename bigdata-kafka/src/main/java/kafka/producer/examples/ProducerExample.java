package kafka.producer.examples;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerExample {
    
    private static final String KAFKA_TOPIC = "test-topic";

    private Properties getKafkaProperties() {
	Properties kafkaProps = new Properties();
	kafkaProps.put("bootstrap.servers", "10.2.0.61:6667");
	kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	return kafkaProps;
    }
    
    private KafkaProducer<String, String> getKafkaProducer() {
	KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(getKafkaProperties());
	return kafkaProducer;
    }
    
    private ProducerRecord<String, String> getProducerRecord() {
	ProducerRecord<String, String> record = new ProducerRecord<String, String>(KAFKA_TOPIC, "key1", "value1");
	return record;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
	ProducerExample ex = new ProducerExample();
	KafkaProducer<String, String> producer = ex.getKafkaProducer();
	Future<RecordMetadata> metadata = producer.send(ex.getProducerRecord(), new Callback() {
	    @Override
	    public void onCompletion(RecordMetadata metadata, Exception ex) {
		System.out.println(metadata.offset() + ":" + metadata.partition());
		System.out.println(ex.getMessage());
	    }
	});
	System.out.println(metadata.get().topic());
    }
}
