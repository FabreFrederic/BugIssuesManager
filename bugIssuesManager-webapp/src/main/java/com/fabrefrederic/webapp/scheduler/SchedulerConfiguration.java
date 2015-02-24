package com.fabrefrederic.webapp.scheduler;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class SchedulerConfiguration {
    private static final Logger LOGGER = Logger.getLogger(SchedulerConfiguration.class);

    @Scheduled(fixedRate=1000)
    public void printMessage() {
        System.out.println("I am called by Spring scheduler - system");
        LOGGER.debug("I am called by Spring scheduler");
    }
}
