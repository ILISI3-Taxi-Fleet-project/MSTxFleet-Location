package com.ilisi.mstxfleetlocation.controller;

import com.ilisi.mstxfleetlocation.entity.LocationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class LocationController {


    @MessageMapping("/location")
    public void getLocation(@Payload LocationMessage message) throws Exception {
        log.info("Message received: {" +
                "name: " + message.getName() +
                ", latitude: " + message.getLatitude() +
                ", longitude: " + message.getLongitude() +
                "}", message);
    }

}