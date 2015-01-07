package com.fabrefrederic.metier.implementationTest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author frederic.fabre
 * 
 */
@Entity
@Table(name = "Marque")
@Component
public class Marque implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 8968007432338838180L;

    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "musicmanager_id_seq")
    @SequenceGenerator(name = "musicmanager_id_seq", sequenceName = "musicmanager_id_seq", allocationSize = 1)
    @Column(name = "Marque_Id")
    private Long Marque_Id;

    /** Nom de la marque */
    @Column(name = "Marque_Nom")
    private String Nom;

    /**
     * @return the marque_Id
     */
    public Long getMarque_Id() {
        return Marque_Id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * @param marque_Id the marque_Id to set
     */
    public void setMarque_Id(final Long marque_Id) {
        Marque_Id = marque_Id;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(final String nom) {
        Nom = nom;
    }

}
