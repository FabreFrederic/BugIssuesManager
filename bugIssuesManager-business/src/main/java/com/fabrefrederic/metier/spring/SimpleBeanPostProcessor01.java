package com.fabrefrederic.metier.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;

import com.fabrefrederic.metier.implementationTest.Client;

/**
 * 
 * @author frederic.fabre
 * 
 */
// Plus l'ordre est grand, plus la priorité est haute
// Possibilité d'utiliser aussi Integer.MIN_VALUE et Integer.MAX_VALUE
@Order(10)
public class SimpleBeanPostProcessor01 implements BeanPostProcessor {

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {

        if (Client.class.isInstance(bean)) {
            (Client.class.cast(bean)).setNom("postProcessAfterInitialization01");
        }
        return bean;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        if (Client.class.isInstance(bean)) {
            (Client.class.cast(bean)).setNom("postProcessBeforeInitialization01");
        }
        return bean;
    }

}
