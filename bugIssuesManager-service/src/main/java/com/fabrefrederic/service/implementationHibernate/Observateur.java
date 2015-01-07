package com.fabrefrederic.service.implementationHibernate;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 
 * @author frederic.fabre
 * 
 */
public class Observateur implements ApplicationListener {

    /**
     * A chaque évènement publié, pour chaque observateur qui implemente ApplicationListener, Spring va executer la
     * méthode onApplicationEvent Il faut que l'observateur ait été déclaré dans le même application context que celui
     * du bean qui publie l'event
     */
    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        if (ModeleSaveOrUpdateEvent.class.isInstance(event)) {
            System.out
                    .println("Evenement enclenché : création d'un modele par le service ModeleService.modeleSaveOrUpdate()");
        }
    }

}
