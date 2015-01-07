package com.fabrefrederic.metier.musicManager.implementation;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * @author frederic.fabre
 * 
 */
@Entity
@Table(name = "album")
@Component
public class Album implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = -8685931080649310891L;

    /** Id */
    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private int id;

    /** */
    @Column(name = "album_name")
    private String name;

    /** */
    // TODO traduction
    @Column(name = "album_dateSortie")
    private DateTime dateSortie;

    /** */
    @Column(name = "album_path")
    private String path;

    /** */
    @OneToMany()
    @JoinColumn(name = "album_tracks")
    private List<Track> tracks;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the dateSortie
     */
    public DateTime getDateSortie() {
        return dateSortie;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * @param id the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param dateSortie the dateSortie to set
     */
    public void setDateSortie(final DateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    /**
     * @param path the path to set
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(final List<Track> tracks) {
        this.tracks = tracks;
    }

}