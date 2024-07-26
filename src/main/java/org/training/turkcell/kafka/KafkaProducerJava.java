package org.training.turkcell.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerJava {
    public static void main(String[] args) {
        System.out.println("Producer Start");
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                          "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        propertiesLoc.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                          IntegerSerializer.class.getName());
        propertiesLoc.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                          StringSerializer.class.getName());
        try (KafkaProducer<Integer, String> producerLoc = new KafkaProducer<>(propertiesLoc)) {
            for (int i = 0; i < 1_000; i++) {
                producerLoc.send(new ProducerRecord<>("java-test-1",
                                                      "osman" + i));
            }
        }
        System.out.println("Producer finished");
    }
}
