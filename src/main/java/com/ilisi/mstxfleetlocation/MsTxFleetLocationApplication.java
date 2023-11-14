package com.ilisi.mstxfleetlocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka // for the configuration of Kafka
public class MsTxFleetLocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsTxFleetLocationApplication.class, args);
    }

}
