package com.ilisi.mstxfleetlocation.controller;

import com.ilisi.mstxfleetlocation.entity.LocationMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class LocationController {

    private final KafkaTemplate<String, LocationMessage> kafkaTemplate;

    @MessageMapping("/location")
    public void getLocation(@Payload LocationMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String userId = headerAccessor.getSessionAttributes().get("userId").toString();
        message.setUserId(userId);
        kafkaTemplate.send("location", message);
        log.info("Message sent to Kafka topic location: {}", message);
    }

}