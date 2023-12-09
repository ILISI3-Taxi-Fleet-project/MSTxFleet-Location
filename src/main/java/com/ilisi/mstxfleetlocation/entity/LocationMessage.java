package com.ilisi.mstxfleetlocation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationMessage {
    private String userId;
    private String location;
    private String userType;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private Boolean isOnline = true;
}
