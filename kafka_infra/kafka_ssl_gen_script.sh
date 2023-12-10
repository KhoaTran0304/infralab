#!/bin/sh
rm my_kafka_broker*
rm my_kafka_client*

##### Kafka Broker Certificate #########
#1. Using keytool, create a certificate for the Kafka broker:

keytool -genkey -alias my_kafka_broker -keyalg RSA -keystore my_kafka_broker.ks -validity 3650 -storepass MySecretPasswordToBeReplaced -keypass MySecretPasswordToBeReplaced -dname "CN=kafkab=_broker_keystore, OU=saveupdeals, O=home, L=Daly City, ST=CA, C=US" -keysize 2048 -ext san=ip:85.239.238.109,dns:localhost  -noprompt

#2. Export the  Kafka broker’s certificate so it can be shared with clients:

keytool -export -alias my_kafka_broker -keystore my_kafka_broker.ks -file my_kafka_broker_cert -validity 3650  -storepass MySecretPasswordToBeReplaced -keypass MySecretPasswordToBeReplaced 

#3. Create a certificate/keystore for the Kafka client:
keytool -genkey -alias my_kafka_client -keyalg RSA -keystore my_kafka_client.ks -validity 3650 -storepass MySecretPasswordToBeReplaced -keypass MySecretPasswordToBeReplaced -dname "CN=kafka_client_keystore
, OU=saveupdeals, O=home, L=Daly City, ST=CA, C=US" -keysize 2048  -ext san=ip:85.239.238.109,dns:localhost -noprompt

#4.Create a truststore for the Kafka client, and import the my_kafka_broker’s certificate. This establishes that the Kafka client “trusts” the Kafka broker:

keytool -import -alias my_kafka_broker -keystore my_kafka_client.ts -file my_kafka_broker_cert -validity 3650 -storepass MySecretPasswordToBeReplaced -keypass MySecretPasswordToBeReplaced -noprompt


###### Kafka Client certificates ################

#1.Export the Kafka client’s certificate so it can be shared with Kafka broker

keytool -export -alias my_kafka_client -keystore my_kafka_client.ks -file my_kafka_client_cert -validity 3650  -storepass MySecretPasswordToBeReplaced -keypass MySecretPasswordToBeReplaced 

#2. Create a truststore for the Kafka broker, and import the Kafka client’s certificate. This establishes that the Kafka broker “trusts” the Kafka client

keytool -import -alias my_kafka_client -keystore my_kafka_broker.ts -file my_kafka_client_cert -validity 3650  -storepass MySecretPasswordToBeReplaced -keypass MySecretPasswordToBeReplaced -noprompt


### list
 ls -la |grep my_kafka_broker
 ls -la |grep my_kafka_client
 
 cp my_kafka_client.ts ../kafka_producer_consumer_java_maven_project/src/main/resources
 ls -la ../kafka_producer_consumer_java_maven_project/src/main/resources
