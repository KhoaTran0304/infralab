package kafka.common.config;

import kafka.consumer.KafkaConsumerSimplePoC;
import kafka.producer.KafkaProducerSimplePoC;

public class Main {
	
	public static void main(String[] args) {

		String type = System.getProperty("run-type") == null ? "":System.getProperty("run-type");
		if(type.equalsIgnoreCase("producer")) {
			KafkaProducerSimplePoC.main(new String[0]);
		}
		else if(type.equalsIgnoreCase("consumer")) {
			KafkaConsumerSimplePoC.main(new String[0]);
		}
		else {
			System.out.println("Please run java -jar -Drun-type=producer|consumer kafka.producer.consumer.poc-0.0.1-SNAPSHOT.jar");
		}
	}
}
