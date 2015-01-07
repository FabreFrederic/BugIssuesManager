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
@Table(name = "Client")
// On peut indiquer a Spring le nom de l'instance du bean
// Par defaut, c'est le nom de la classe avec une minuscule
@Component("client")
public class Client implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = -5084756102583125500L;

    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "musicmanager_id_seq")
    @SequenceGenerator(name = "musicmanager_id_seq", sequenceName = "musicmanager_id_seq", allocationSize = 1)
    @Column(name = "Client_Id")
    private Long id;

    /** Nom du client */
    @Column(name = "Client_Nom")
    private String nom;

    /** Prenom du client */
    @Column(name = "Client_Prenom")
    private String prenom;

    /**
     * Default constructor
     */
    public Client() {
        super();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

}
