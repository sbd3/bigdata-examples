package kafka.serializer.examples;

import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AvroSerializer {

    public static void main(String[] args) {

	Properties props = new Properties();
	props.put("bootstrap.servers", "localhost:9092");
	props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
	props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
	props.put("schema.registry.url", "URL");

	String schemaString = "{\"namespace\": \"customerManagement.avro\",\"type\": \"record\", " + "\"name\": \"Customer\"," + "\"fields\": [" + "{\"name\": \"id\", \"type\": \"int\"},"
		+ "{\"name\": \"name\", \"type\": \"string\"}," + "{\"name\": \"email\", \"type\": [\"null\",\"string\"], \"default\":\"null\" }" + "]}";
	KafkaProducer<String, GenericRecord> producer = new KafkaProducer<String, GenericRecord>(props);

	Schema.Parser parser = new Schema.Parser();
	Schema schema = parser.parse(schemaString);

	for (int nCustomers = 0; nCustomers < 10; nCustomers++) {
	    String name = "exampleCustomer" + nCustomers;
	    String email = "example " + nCustomers + "@example.com";

	    GenericRecord customer = new GenericData.Record(schema);
	    customer.put("id", nCustomers);
	    customer.put("name", name);
	    customer.put("email", email);

	    ProducerRecord<String, GenericRecord> data = new ProducerRecord<String, GenericRecord>("customerContacts", name, customer);
	    producer.send(data);
	}
	producer.close();
    }
}
