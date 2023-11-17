package com.ilisi.mstxfleetlocation.controller;

import com.ilisi.mstxfleetlocation.entity.LocationMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class LocationController {

    private final KafkaTemplate<String, LocationMessage> kafkaTemplate;

    @MessageMapping("/location")
    public void getLocation(@Payload LocationMessage message)  {
        kafkaTemplate.send("location", message);
        System.out.println("Message sent: " + message.getLatitude() + " " + message.getLongitude());
    }

}