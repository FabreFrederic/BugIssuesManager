package com.fabrefrederic.business.dto;

import java.io.Serializable;

import com.google.common.collect.ComparisonChain;

public class IssueDto implements Serializable, Comparable<IssueDto> {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Integer id;

    /**
     * Compare an issue with the current issue instance in order to sort a collection of issues
     *
     * @param issue issue to compare with the current issue instance
     * @return
     */
    @Override
    public int compareTo(IssueDto issue) {
        return ComparisonChain.start().compare(getId(), issue.getId()).result();
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
    public void setId(Integer id) {
        this.id = id;
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
}
