package kafka.consumer.examples;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

public class CustomConsumer {

    private static final String KAFKA_TOPIC = "test-topic";
    private static KafkaConsumer<String, String> consumer;

    public static void main(String[] args) {
	CustomConsumer con = new CustomConsumer();
	con.pollBasedMessages();
    }

    private KafkaConsumer<String, String> getKafkaConsumer() {
	if (consumer != null) {
	    return consumer;
	}
	consumer = new KafkaConsumer<>(getConsumerProperties());
	consumer.subscribe(Collections.singletonList(KAFKA_TOPIC));
	return consumer;
    }

    private void pollBasedMessages() {
	ConsumerRecords<String, String> records = getKafkaConsumer().poll(100);
	for (ConsumerRecord<String, String> consumerRecord : records) {
	    System.out.println(consumerRecord.checksum());
	}
	consumer.commitAsync(new OffsetCommitCallback() {
	    
	    @Override
	    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
		System.err.println("Unable to commit offset::" + offsets.values());
		System.err.println(exception.getLocalizedMessage());
	    }
	});
    }

    private Properties getConsumerProperties() {
	Properties props = new Properties();
	props.setProperty("bootstrap.servers", "10.2.0.61:6667");
	props.setProperty("group.id", "con_grp");
	props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	return props;
    }
}
