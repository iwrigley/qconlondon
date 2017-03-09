package partial;

import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import utils.GenericAvroSerde;

public class SimpleStreams {
	public static void main(String[] args) throws Exception {
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "simpleStreams");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		streamsConfiguration.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
		streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		// Configure the KEY_SERDE_CLASS_CONFIG and VALUE_SERDE_CLASS_CONFIG to use String
		streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		

		// Create the KSTreamBuilder object
		KStreamBuilder builder = new KStreamBuilder();

		// Read from the input topic
		KStream<String, String> inputText = // TODO

		// Create a KStream object by turning the value to uppercase. Use mapvalues.
		// Java 8 syntax to convert to uppercase is String::toUpperCase
		// TODO
		
		// To debug, you could use the print() method to see the contents of the stream
		// inputText.print();

				
		// Now write to a topic called 'upperHamlet'
		// TODO
		
		// Finally, create the KafkaStreams object, and start processing
		KafkaStreams streams = new KafkaStreams(builder, streamsConfiguration);
		streams.start();
	}
}

