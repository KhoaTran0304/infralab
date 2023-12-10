# labinfra

================================================================
Ref: https://activemq.apache.org/how-do-i-use-ssl

1. Using keytool, create a certificate for the broker:

keytool -genkey -alias broker -keyalg RSA -keystore broker.ks -validity 3650 -storepass V4Paseipif -keypass V4Paseipif -dname "CN=kafkab=_broker_keystore, OU=saveupdeals, O=home, L=Daly City, ST=CA, C=US" -keysize 2048 -ext san=ip:85.239.238.108,dns:localhost  -noprompt




2. Export the broker’s certificate so it can be shared with clients:

keytool -export -alias broker -keystore broker.ks -file broker_cert -validity 3650  -storepass V4Paseipif -keypass V4Paseipif 

3. Create a certificate/keystore for the client:
keytool -genkey -alias client -keyalg RSA -keystore client.ks -validity 3650 -storepass V4Paseipif -keypass V4Paseipif -dname "CN=kafka_client_keystore
, OU=saveupdeals, O=home, L=Daly City, ST=CA, C=US" -keysize 2048  -ext san=ip:85.239.238.108,dns:localhost -noprompt

4.Create a truststore for the client, and import the broker’s certificate. This establishes that the client “trusts” the broker:

keytool -import -alias broker -keystore client.ts -file broker_cert -validity 3650 -storepass V4Paseipif -keypass V4Paseipif -noprompt

Export -Djavax.net.ssl.keyStore=/path/to/broker.ks -Djavax.net.ssl.keyStorePassword=password

Client certificates

1.Export the client’s certificate so it can be shared with broker

keytool -export -alias client -keystore client.ks -file client_cert -validity 3650  -storepass V4Paseipif -keypass V4Paseipif 
2. Create a truststore for the broker, and import the client’s certificate. This establishes that the broker “trusts” the client
keytool -import -alias client -keystore broker.ts -file client_cert -validity 3650  -storepass V4Paseipif -keypass V4Paseipif -noprompt


-Djavax.net.ssl.trustStore=/path/to/broker.ts

To delete import alias
keytool -delete -alias client -keystore client.ks

================================================================

