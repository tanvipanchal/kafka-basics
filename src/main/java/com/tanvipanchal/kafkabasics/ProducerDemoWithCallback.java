package com.tanvipanchal.kafkabasics;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemoWithCallback {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());
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
        //properties.setProperty("batch.size", "400");
        //properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());

        //create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for(int j=0;j<10;j++){

            for(int i =0;i<30;i++){
                //create producer record
                ProducerRecord<String , String > producerRecord = new ProducerRecord<>("demo_java", "hello world" + i);

                //send data
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        //executed everytime a record successfully sent or an exception is thrown
                        if(e == null){
                            //the record was successfully sent
                            log.info("Received new metadata \n" +
                                    "Topic: " + recordMetadata.topic() + "\n" +
                                    "partition: " + recordMetadata.partition() + "\n" +
                                    "offset: " + recordMetadata.offset() + "\n" +
                                    "timestamp: " + recordMetadata.timestamp() + "\n" );
                        }
                        else {
                            log.error("Error while producing" , e);
                        }
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }





        //tells the producer to send all the data and block until done -- synchronous
        producer.flush();

        //flush and close the producer
        producer.close();
    }
}
