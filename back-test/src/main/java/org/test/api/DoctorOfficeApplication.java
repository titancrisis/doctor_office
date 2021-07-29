package org.test.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class DoctorOfficeApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/La_Paz"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DoctorOfficeApplication.class, args);
    }
}
