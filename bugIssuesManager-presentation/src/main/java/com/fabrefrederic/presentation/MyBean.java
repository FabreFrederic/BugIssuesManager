package com.fabrefrederic.presentation;

import org.springframework.scheduling.annotation.Scheduled;

public class MyBean {
    @Scheduled(fixedRate=5000)
    public void printMessage() {
        System.out.println("I am called by Spring scheduler");
    }
}
