package com.spruds.covid.travellerregistration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration
@Profile({ "test" })
public class MockClockConfiguration {
    @Bean
    public Clock fixedClock() {
        Instant instant = Instant.parse("2020-08-07T16:45:42.00Z");
        ZoneId zone = ZoneId.of("UTC");
        return Clock.fixed(instant, zone);
    } 
} 