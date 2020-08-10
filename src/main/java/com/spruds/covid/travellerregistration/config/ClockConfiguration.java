package com.spruds.covid.travellerregistration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;

@Configuration
@Profile({ "production" })
public class ClockConfiguration {
    @Bean
    public Clock currentClock() {
        return Clock.systemDefaultZone();
    } 
} 