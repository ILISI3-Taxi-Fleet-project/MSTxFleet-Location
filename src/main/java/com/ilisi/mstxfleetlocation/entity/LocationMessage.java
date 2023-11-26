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
    private String location;
    private String userType;
    private Instant createdAt = Instant.now();
}
