package org.training.turkcell.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerJava {
    public static void main(String[] args) {
        System.out.println("Consumer started");
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        propertiesLoc.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                          IntegerDeserializer.class.getName());
        propertiesLoc.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                          StringDeserializer.class.getName());
        propertiesLoc.put(ConsumerConfig.GROUP_ID_CONFIG,
                          "java-read-group-1");
        propertiesLoc.put(ConsumerConfig.CLIENT_ID_CONFIG,
                          args[0]);
        propertiesLoc.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG,
                          args[1]);

        propertiesLoc.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                          "earliest");
        try (KafkaConsumer<Integer,String> kafkaConsumerLoc = new KafkaConsumer<>(propertiesLoc)) {
            kafkaConsumerLoc.subscribe(Arrays.asList("java-test-1"));
            while (true) {
                ConsumerRecords<Integer, String> pollLoc = kafkaConsumerLoc.poll(Duration.ofMillis(1_000));
                for (ConsumerRecord<Integer, String> rec : pollLoc) {
                    System.out.println("received : " + rec);
                }
            }
        }
    }
}
