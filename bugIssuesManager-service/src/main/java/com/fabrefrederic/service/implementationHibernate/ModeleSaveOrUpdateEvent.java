package com.fabrefrederic.service.implementationHibernate;

import org.springframework.context.ApplicationEvent;

import com.fabrefrederic.metier.implementationTest.Modele;

public class ModeleSaveOrUpdateEvent extends ApplicationEvent {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;
    /** Modele */
    private final Modele modele;

    /**
     * 
     * @param source
     * @param modeleSavedOrUpdated modele nouvellement cree ou update
     * @category Constructor
     */
    public ModeleSaveOrUpdateEvent(final Object source, final Modele modeleSavedOrUpdated) {
        super(source);
        // Stocke le modele dans l'ï¿½vï¿½nement
        modele = modeleSavedOrUpdated;
    }

    /**
     * Accesseur du modele pour que les consommateurs puissent y accïeer
     * 
     * @return the modele
     * @category Accessor
     */
    public Modele getModele() {
        return modele;
    }

}
