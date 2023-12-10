package kafka.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;



public abstract class CommonPropertiesConfig {

	// Create Kafka producer configuration
	private Properties properties = new Properties();

	// Replace with your Kafka topic name
	public static final String TOPIC_NAME = "test-topic"; 

	protected void initProperties() {

		// Replace with your Kafka broker address
		String bootstrapServers = "85.239.238.109:9093"; 
		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		
		// SSL/TLS properties
		properties.put("security.protocol", "SSL");

		InputStream truststoreInputStream = null;
		// Load truststore from resources
		try (InputStream is = getClass().getClassLoader()
				.getResourceAsStream("./my_kafka_client.ts")) {

			if(is == null) {
				
				truststoreInputStream = getClass().getClassLoader()
						.getResourceAsStream("my_kafka_client.ts");
			}
			else {
				truststoreInputStream = is;
			}
			
			Path destinationPath = Path.of("/tmp/my_kafka_client.ts");
			copyInputStreamToFile(truststoreInputStream, destinationPath);
			if (truststoreInputStream != null) {
				properties.put("ssl.truststore.location", "/tmp/my_kafka_client.ts");
			} else {
				throw new RuntimeException("Truststore file not found in resources");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		properties.put("ssl.truststore.password", "MySecretPasswordToBeReplaced");
	}
	
	protected void copyInputStreamToFile(InputStream inputStream, Path destinationPath) throws IOException {
		// Use StandardCopyOption.REPLACE_EXISTING to overwrite the destination file if
		// it already exists
		Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public Properties getProperties() {
		return this.properties;
	}

}
