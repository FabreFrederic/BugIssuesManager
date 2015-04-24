package com.fabrefrederic.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.File;
import com.fabrefrederic.business.Issue;
import com.fabrefrederic.dao.interfaces.CommitDao;
import com.fabrefrederic.dao.interfaces.IssueDao;
import com.fabrefrederic.service.interfaces.IssueService;

public class IssueServiceImpl implements IssueService {
    private static final Logger LOGGER = Logger.getLogger(IssueServiceImpl.class);

    @Autowired
    private IssueDao issueDao;
    @Autowired
    private CommitDao commitDao;

    @Override
    public Set<Issue> getAffectedIssuesByIssueId(Integer issueId) {
        LOGGER.debug("issueId : " + issueId);

        // Gets the commits list from the issueId
        final Issue issue = issueDao.findById(issueId);
        List<Commit> commits = commitDao.findByIssueId(issue);

        // File list from the all these commits
        final List<File> files = new ArrayList<>();
        for (final Commit commit : commits) {
            LOGGER.debug("commit.getId() : " + commit.getId());
            files.addAll(commit.getFiles());
        }

        // Gets the commits list from all these files
        commits = new ArrayList<>();
        for (final File file : files) {
            commits.addAll(file.getCommits());
        }

        // Set of issues from these commits
        final Set<Issue> issues = new HashSet<>();
        for (final Commit commit : commits) {
            issues.add(commit.getIssue());
        }
        return issues;
    }

    /**
     * @param issueDao the issueDao to set
     */
    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    /**
     * @param commitDao the commitDao to set
     */
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }
}
