package com.fabrefrederic.dao.interfaces;

import javax.persistence.NoResultException;

import com.fabrefrederic.business.File;


public interface FileDao extends GenericDao<File> {

    /**
     * Find a file by its name
     *
     * @param name file
     * @return the file
     */
    File findByName(final String name) throws NoResultException;

}
