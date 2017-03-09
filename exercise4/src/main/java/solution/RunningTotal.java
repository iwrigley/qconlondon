package solution;

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

public class RunningTotal {
	public static void main(String[] args) throws Exception {
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "runningTotal");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		streamsConfiguration.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
		streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		
		// Configure the KEY_SERDE_CLASS_CONFIG and VALUE_SERDE_CLASS_CONFIG to use GenericAvroSerde.class
		streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, GenericAvroSerde.class);
		streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, GenericAvroSerde.class);
		
		streamsConfiguration.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schemaregistry1:8081");

		// Create the KSTreamBuilder object
		KStreamBuilder builder = new KStreamBuilder();

		// Read from the input topic
		KStream<GenericRecord, GenericRecord> allCheckins = builder.stream("tutorial-checkins");

		// Create a KStream object by extracting just the 'amountSold' field from the value. Each key should be the same, so
		// we can do a reduce later
		KStream<String, Double> justSales = 
				allCheckins.map((k, v) -> new KeyValue<String, Double>("total", (double) v.get("amountSold")));
		
		// To debug, you could use the print() method to see the contents of the stream
		// justSales.print();

		// Now we do a groupByKey and a reduce, to add all the values together. We need to specify a name
		// for the internal StateStore
		KTable<String, Double> rt = justSales.groupByKey(Serdes.String(), Serdes.Double()).reduce((v1,  v2) -> v1 + v2, "runningTotal");
		
		// Again, to debug you could print the stream out
		// rt.print();
		
		// Now write the KTable to a topic called 'runningTotal'
		rt.to(Serdes.String(), Serdes.Double(), "runningTotal");
		
		// Finally, create the KafkaStreams object, and start processing
		KafkaStreams streams = new KafkaStreams(builder, streamsConfiguration);
		streams.start();
	}
}

