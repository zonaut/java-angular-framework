package com.zonaut.sbbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SBBatch {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));
    }

    public static void main(String[] args) {
        SpringApplication.run(SBBatch.class, args);
    }

}
