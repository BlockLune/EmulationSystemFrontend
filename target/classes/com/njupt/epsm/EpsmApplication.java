package com.njupt.epsm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.njupt.epsm.mapper"})
public class EpsmApplication {

    private static final Logger logger = LoggerFactory.getLogger(EpsmApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(EpsmApplication.class, args);
        } catch (Exception e) {
            logger.error("EpsmApplication start error", e);
        }
    }

}
