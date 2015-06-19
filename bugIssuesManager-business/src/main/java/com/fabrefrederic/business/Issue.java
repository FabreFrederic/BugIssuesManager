package com.fabrefrederic.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.collect.ComparisonChain;

@Entity
@Table(name = "issue")
public class Issue implements Serializable, Comparable<Issue> {
    private static final long serialVersionUID = 1L;

    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bugIssuesManager_id_seq")
    @SequenceGenerator(name = "bugIssuesManager_id_seq", sequenceName = "bugIssuesManager_id_seq", allocationSize = 1)
    @Column(name = "issue_id")
    private int id;

    /** Issue name */
    @Column(name = "issue_name", unique = true)
    private String name;

    /** Issue description */
    @Column(name = "issue_description")
    private String description;

    /**
     * Compare an issue with the current issue instance in order to sort a collection of issues
     *
     * @param issue issue to compare with the current issue instance
     * @return
     */
    @Override
    public int compareTo(Issue issue) {
        return ComparisonChain.start().compare(getId(), issue.getId()).result();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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