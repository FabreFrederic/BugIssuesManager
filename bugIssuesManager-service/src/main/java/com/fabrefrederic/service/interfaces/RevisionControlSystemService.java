package com.fabrefrederic.service.interfaces;

import java.util.Collection;
import java.util.List;

import org.tmatesoft.svn.core.SVNLogEntry;

import com.fabrefrederic.business.Commit;

public interface RevisionControlSystemService {

    /**
     *
     * @return
     */
    Collection<SVNLogEntry> getCommits(long startRevision, long endRevision);

    /**
     *
     * @param commits
     */
    void saveCommits(List<Commit> commits);

}
