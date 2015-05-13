package com.fabrefrederic.dao.interfaces;

import javax.persistence.NoResultException;

import com.fabrefrederic.business.File;


public interface FileDao extends GenericDao<File> {

    /**
     * Find a file by its path
     *
     * @param path and file name
     * @return the file
     */
    File findByPath(final String path) throws NoResultException;

}
