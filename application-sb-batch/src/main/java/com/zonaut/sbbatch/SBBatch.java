package com.zonaut.sbbatch;

import com.zonaut.common.Common;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

@Log4j2
@SpringBootApplication
@ConfigurationPropertiesScan
public class SBBatch {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));
    }

    public static void main(String[] args) {
        SpringApplication.run(SBBatch.class, args);
        log.info(Common.COMMON_STRING);
    }

}
