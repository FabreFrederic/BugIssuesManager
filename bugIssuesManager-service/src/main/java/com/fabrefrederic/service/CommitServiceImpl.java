package com.fabrefrederic.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.dao.interfaces.CommitDao;
import com.fabrefrederic.library.RevisionControlSystem;
import com.fabrefrederic.service.interfaces.CommitService;

public class CommitServiceImpl implements CommitService {
    private static final Logger LOGGER = Logger.getLogger(CommitService.class);

    @Autowired
    private RevisionControlSystem revisionControlSystem;
    @Autowired
    private CommitDao commitDao;

    @Override
    public List<Commit> getCommits(final String path, final String startRevision, final String endRevision, final Integer limit) {
        List<Commit> commits = new ArrayList<Commit>();
        try {
            commits = revisionControlSystem.getLogs(path, startRevision, endRevision, limit);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Number of commits retrieved : " + commits.size());
        return commits;
    }

    @Override
    public void saveCommits(final List<Commit> commits) {
        if (commits != null) {
            for (final Commit commit : commits) {
                commitDao.save(commit);
            }
        }
        commitDao.flush();
    }

    @Override
    public Commit getTheLastSavedCommit() {
        final Commit commit = commitDao.findTheMostRecentCommit();
        return commit;
    }

    @Override
    public Commit getTheLastCommitFromRepository(String path) {
        Commit commit = null;
        try {
            commit = revisionControlSystem.getTheLastCommitFromRepository(path);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
        return commit;
    }

    @Override
    public Commit getTheFirstCommitFromRepository(String path) {
        Commit commit = null;
        try {
            commit = revisionControlSystem.getTheFirstCommitFromRepository(path);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
        return commit;
    }

    @Override
    public List<Commit> getCommitsToTheLastRevision(final String path, final String startRevision, final Integer limit) {
        List<Commit> commits = new ArrayList<Commit>();
        try {
            commits = revisionControlSystem.getLogsToTheLastRevision(path, startRevision, limit);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Number of commits retrieved : " + commits.size());
        return commits;
    }

    /**
     * @param revisionControlSystem the revisionControlSystem to set
     */
    public void setRevisionControlSystem(RevisionControlSystem revisionControlSystem) {
        this.revisionControlSystem = revisionControlSystem;
    }

    /**
     * @param commitDao the commitDao to set
     */
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }

}