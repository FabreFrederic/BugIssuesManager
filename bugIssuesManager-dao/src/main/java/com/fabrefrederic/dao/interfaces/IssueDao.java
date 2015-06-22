package com.fabrefrederic.dao.interfaces;

import javax.persistence.NoResultException;

import com.fabrefrederic.business.Issue;

public interface IssueDao extends GenericDao<Issue> {

    /**
     * Find an issue by its name
     *
     * @param name issue name
     * @return the issue
     */
    Issue findByName(String name) throws NoResultException;

}
