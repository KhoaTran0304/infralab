package kafka.producer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import kafka.common.config.CommonPropertiesConfig;

public class KafkaProducerSimplePoC extends CommonPropertiesConfig{

	public KafkaProducerSimplePoC() {
		initProperties();
	}
	
	public static void main(String[] args) {
		
		KafkaProducerSimplePoC kafkaProducerSimplePoC = new KafkaProducerSimplePoC();
		
		Properties properties = kafkaProducerSimplePoC.getProperties();
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Create Kafka producer instance
		Producer<String, String> producer = new KafkaProducer<>(properties);

		try {
			// Send some test messages
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				String message = "Test message #" + i;
				ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
				producer.send(record);
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println("Sent message: " + message);
			}
		} catch (Exception e) {
			System.out.println("Error sending message: " + e.getMessage());
		} finally {
			// Close the Kafka producer
			producer.close();
		}
	}

	
}
