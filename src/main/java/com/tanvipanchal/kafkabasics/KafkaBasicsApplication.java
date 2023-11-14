package com.tanvipanchal.kafkabasics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaBasicsApplication {

    private static final Logger log = LoggerFactory.getLogger(KafkaBasicsApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(KafkaBasicsApplication.class, args);
        log.info("Hello World!");
    }

}
