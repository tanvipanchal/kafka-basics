package com.tanvipanchal.kafkabasics;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());
    public static void main(String[] args) {
        log.info("Kafka producer");

        //create producer properties
        Properties properties = new Properties();

        //connect to local host
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        //connect to cluster (Conduktor playground)
//        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
//        properties.setProperty("security.protocol", "SASL_SSL");
//        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule"
//        properties.setProperty("sasl.mechanism", "PLAIN");

        //set the producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //create producer record
        ProducerRecord<String , String > producerRecord = new ProducerRecord<>("demo_java", "hello world");

        //send data
        producer.send(producerRecord);

        //tells the producer to send all the data and block until done -- synchronous
        producer.flush();

        //flush and close the producer
        producer.close();
    }
}
