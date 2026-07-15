package com.h2.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class TimeZoneConfig {
    
    @PostConstruct
    public void init() {
        // Force the JVM to use the correct timezone ID
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("Timezone set to: " + TimeZone.getDefault().getID());
    }
}
