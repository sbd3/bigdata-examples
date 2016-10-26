package kafka.serializer.examples;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public void close() {
	// nothing to close
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
	// nothing to configure
    }

    @Override
    public byte[] serialize(String topic, Customer cust) {
	try {
	    byte[] serializedName;
	    int stringSize;
	    if (cust == null) {
		return null;
	    } else {
		if (cust.getCustomerName() != null) {
		    serializedName = cust.getCustomerName().getBytes("UTF-8");
		    stringSize = serializedName.length;
		} else {
		    serializedName = new byte[0];
		    stringSize = 0;
		}
	    }
	    ByteBuffer buffer = ByteBuffer.allocate(4 * 4 * stringSize);
	    buffer.putInt(cust.getCustomerId());
	    buffer.putInt(stringSize);
	    buffer.put(serializedName);
	    return buffer.array();
	} catch (Exception e) {
	    throw new SerializationException("Error when serializing Customer to byte[] " + e);
	}
    }

}
