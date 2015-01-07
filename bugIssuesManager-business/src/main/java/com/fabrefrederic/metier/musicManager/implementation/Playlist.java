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

import org.springframework.stereotype.Component;

/**
 * @author frederic.fabre
 * 
 */
@Entity
@Table(name = "playlist")
@Component
public class Playlist implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -6445349218931263318L;

    /** Id */
    @Id
    @GeneratedValue
    @Column(name = "playlist_id")
    private Integer id;

    /** Playlist name */
    @Column(name = "playlist_name")
    private String name;

    /** Playlist tracks */
    @OneToMany()
    @JoinColumn(name = "playlist_tracks")
    private List<Track> tracks;

    /** Playlist Albums */
    /** */
    @OneToMany()
    @JoinColumn(name = "playlist_albums")
    private List<Album> albums;

    /** a playlist can contain playlists */
    @OneToMany()
    @JoinColumn(name = "playlist_playlists")
    private List<Playlist> playlists;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * @return the albums
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * @return the playlists
     */
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(final List<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * @param albums the albums to set
     */
    public void setAlbums(final List<Album> albums) {
        this.albums = albums;
    }

    /**
     * @param playlists the playlists to set
     */
    public void setPlaylists(final List<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

}