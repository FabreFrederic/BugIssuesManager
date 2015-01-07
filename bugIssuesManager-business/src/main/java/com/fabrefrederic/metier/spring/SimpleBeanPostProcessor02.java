package com.fabrefrederic.metier.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;

import com.fabrefrederic.metier.implementationTest.Client;

/**
 * @author frederic.fabre
 * 
 */
@Order(5)
public class SimpleBeanPostProcessor02 implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (Client.class.isInstance(bean)) {
            (Client.class.cast(bean)).setNom("postProcessAfterInitialization02");
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        if (Client.class.isInstance(bean)) {
            (Client.class.cast(bean)).setNom("postProcessBeforeInitialization02");
        }
        return bean;
    }
}
