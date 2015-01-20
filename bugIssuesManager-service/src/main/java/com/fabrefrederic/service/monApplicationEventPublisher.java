package com.fabrefrederic.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 
 * @author frederic.fabre
 * 
 */
public class monApplicationEventPublisher implements ApplicationEventPublisher {

    @Override
    public void publishEvent(final ApplicationEvent event) {
        System.out.println("----------------- Mon �v�nement a eu lieu -----------------");
    }

}
