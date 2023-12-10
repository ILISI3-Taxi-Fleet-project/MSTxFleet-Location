package com.ilisi.mstxfleetlocation.controller;

import com.ilisi.mstxfleetlocation.entity.LocationMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
@AllArgsConstructor
public class LocationController {

    private final KafkaTemplate<String, Map<String, ?>> kafkaTemplate;

    @MessageMapping("/location")
    public void getLocation(@Payload LocationMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String userId = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("userId").toString();
        message.setUserId(userId);

        Map<String, Object> data = Map.of(
                "userId", message.getUserId(),
                "location", message.getLocation(),
                "userType", message.getUserType(),
                "createdAt", message.getCreatedAt().toString(),
                "updatedAt", Objects.toString(message.getUpdatedAt(), ""),
                "isOnline", message.getIsOnline()
        );
        kafkaTemplate.send("location", data);
        log.info("Message sent to Kafka topic location: {}", data);
    }

}