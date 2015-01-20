package com.fabrefrederic.service.interfaces;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.tmatesoft.svn.core.SVNLogEntry;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.library.RevisionControlSystem;

public class RevisionControlSystemServiceImpl implements RevisionControlSystemService {

    /** */
    @Autowired
    private RevisionControlSystem revisionControlSystem;

    @Override
    public Collection<SVNLogEntry> getCommits(final long startRevision, final long endRevision) {
        final Collection<SVNLogEntry> svnLogEntries = revisionControlSystem.getLogs(startRevision, endRevision);
        return svnLogEntries;
    }

    @Override
    public void saveCommits(final List<Commit> commits) {

    }

}
