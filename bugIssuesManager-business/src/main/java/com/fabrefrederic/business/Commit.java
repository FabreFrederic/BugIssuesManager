package com.fabrefrederic.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "commit")
@Component("commit")
public class Commit implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bugIssuesManager_id_seq")
    @SequenceGenerator(name = "bugIssuesManager_id_seq", sequenceName = "bugIssuesManager_id_seq", allocationSize = 1)
    @Column(name = "commit_id")
    private int id;

    /** Commit number */
    @Column(name = "commit_number")
    private String number;

    /** Commit date */
    @Column(name = "commit_date")
    private Date date;

    /** Commit author */
    @Column(name = "commit_author")
    private String author;

    /** Commit message */
    @Column(name="commit_message")
    private String message;

    /** Issue */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commit_issue_id", unique = false, nullable = true)
    private Issue issue;

    /** Files committed */
    @ManyToMany(targetEntity = File.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "commit_file", joinColumns = @JoinColumn(name = "commit_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<File> files;

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the issue
     */
    public Issue getIssue() {
        return issue;
    }

    /**
     * @param issue the issue to set
     */
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
