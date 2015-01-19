package com.fabrefrederic.library;

import java.util.List;

import com.fabrefrederic.business.Commit;

/**
 *
 *
 */
public interface RevisionControlSystem {

    /**
     *
     * @param startRevision
     * @param endRevision
     * @return
     */
    List<Commit> getLogs(final long startRevision, final long endRevision);
}
