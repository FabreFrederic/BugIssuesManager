package com.fabrefrederic.library;

import java.util.List;

import com.fabrefrederic.business.Commit;

/**
 *
 *
 */
public interface RevisionControlSystem {

    /**
     * Asks logs to the repository
     *
     * @param path the path to the trunk ou the branch wanted
     * @param startRevision start revision number
     * @param endRevision end revision number
     * @return a list of {@code Commit}
     * @throws Exception
     */
    List<Commit> getLogs(final String path, final long startRevision, final long endRevision) throws Exception;
}
