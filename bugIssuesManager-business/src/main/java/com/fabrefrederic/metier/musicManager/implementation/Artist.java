package com.fabrefrederic.metier.musicManager.implementation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author frederic.fabre
 * 
 */
@Entity
@Table(name = "artist")
@Component
public class Artist implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = -4680006485066322296L;

    /** Id */
    @Id
    @GeneratedValue
    @Column(name = "artist_id")
    private Integer id;

    /** name */
    @Column(name = "artist_name")
    private String name;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

}