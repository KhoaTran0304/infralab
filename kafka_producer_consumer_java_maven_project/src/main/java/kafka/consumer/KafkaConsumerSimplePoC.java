package kafka.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import kafka.common.config.CommonPropertiesConfig;

public class KafkaConsumerSimplePoC extends CommonPropertiesConfig {

	public KafkaConsumerSimplePoC() {
		initProperties();
	}
	
	public static void main(String[] args) {
	
		// Replace with your consumer group ID
		String groupId = "test-consumer-group"; 
		

		KafkaConsumerSimplePoC kafkaConsumerSimplePoC = new KafkaConsumerSimplePoC();
		Properties properties = kafkaConsumerSimplePoC.getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		// Create Kafka consumer instance
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		
		
		// Subscribe to Kafka topic
		consumer.subscribe(Collections.singletonList(TOPIC_NAME));

		try {
			// Poll for new messages
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("Received message: " + record.value());
				}
			}
		} catch (Exception e) {
			System.out.println("Error consuming message: " + e.getMessage());
		} finally {
			// Close the Kafka consumer
			consumer.close();
		}
	}
}
