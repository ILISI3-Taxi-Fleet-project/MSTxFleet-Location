package com.ilisi.mstxfleetlocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LocationMessage {
    private String name;
    private double latitude;
    private double longitude;
}
