package com.fabrefrederic.metier.implementationTest;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author frederic.fabre
 * 
 */
@Entity
@Table(name = "Modele")
@Component
public class Modele implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1729519620650807717L;

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "musicmanager_id_seq")
    @SequenceGenerator(name = "musicmanager_id_seq", sequenceName = "musicmanager_id_seq", allocationSize = 1)
    @Column(name = "Modele_Id")
    private Long id;

    /** Marque */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Modele_MarqueId", unique = true, nullable = false)
    private Marque marque;

    /** Client qui a achet� le modele */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Modele_ClientId", nullable = true, updatable = true)
    // Injection automatique par Annotation de Spring
    @Autowired
    private Client client;

    /** Nom du mod�le */
    @Column(name = "Modele_Nom")
    private String nom;

    /** prix catalogue du mod�le */
    @Column(name = "Modele_PrixCatalogue")
    private Double prixCatalogue;

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the marque
     */
    public Marque getMarque() {
        return marque;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prixCatalogue
     */
    public Double getPrixCatalogue() {
        return prixCatalogue;
    }

    /**
     * @param client the client to set
     */
    public void setClient(final Client client) {
        this.client = client;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(final Marque marque) {
        this.marque = marque;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * @param prixCatalogue the prixCatalogue to set
     */
    public void setPrixCatalogue(final Double prixCatalogue) {
        this.prixCatalogue = prixCatalogue;
    }

}
