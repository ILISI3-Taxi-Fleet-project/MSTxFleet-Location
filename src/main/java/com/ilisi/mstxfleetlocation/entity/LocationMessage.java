package com.ilisi.mstxfleetlocation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationMessage {
    private String userId;
    private double latitude;
    private double longitude;
    private String userType;
    private Instant timestamp = Instant.now();
}
