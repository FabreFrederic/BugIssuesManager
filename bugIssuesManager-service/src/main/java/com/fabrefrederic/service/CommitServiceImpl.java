package com.fabrefrederic.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.library.RevisionControlSystem;
import com.fabrefrederic.service.interfaces.CommitService;

public class CommitServiceImpl implements CommitService {
    private static final Logger LOGGER = Logger.getLogger(CommitService.class);

    /** */
    @Autowired
    private RevisionControlSystem revisionControlSystem;

    @Override
    public List<Commit> getCommits(final long startRevision, final long endRevision) {
        List<Commit> commits = new ArrayList<Commit>();
        try {
            commits = revisionControlSystem.getLogs(startRevision, endRevision);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Number of commits retrieved : " + commits.size());
        return commits;
    }

    @Override
    public void saveCommits(final List<Commit> commits) {

    }

}