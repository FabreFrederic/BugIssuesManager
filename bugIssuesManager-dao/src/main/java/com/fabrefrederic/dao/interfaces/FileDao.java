package com.fabrefrederic.dao.interfaces;

import com.fabrefrederic.business.File;


public interface FileDao extends GenericDao<File> {

    /**
     * Find a file by its name
     *
     * @param name file
     * @return the file
     */
    File findByName(final String name);

}
