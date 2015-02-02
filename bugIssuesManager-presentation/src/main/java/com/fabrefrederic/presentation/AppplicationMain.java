package com.fabrefrederic.presentation;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AppplicationMain {

    @SuppressWarnings({ "unused", "resource" })
    public static void main(String args[]){
        final AbstractApplicationContext  context = new AnnotationConfigApplicationContext(SchedulerConfiguration.class);
    }

}