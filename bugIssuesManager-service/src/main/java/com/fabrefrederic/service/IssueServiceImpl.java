package com.fabrefrederic.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.File;
import com.fabrefrederic.business.Issue;
import com.fabrefrederic.dao.interfaces.CommitDao;
import com.fabrefrederic.dao.interfaces.IssueDao;
import com.fabrefrederic.service.interfaces.IssueService;

public class IssueServiceImpl implements IssueService {
    private static final Logger LOGGER = Logger.getLogger(IssueServiceImpl.class);

    private final String ISSUE_PATTERN = "AIC-";

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private CommitDao commitDao;

    @Override
    @Transactional
    public Set<Issue> getAffectedIssuesByIssueId(String issueName) {
        LOGGER.debug("issueName : " + issueName);
        final Set<Issue> issues = new HashSet<>();

        if (StringUtils.isNotBlank(issueName)) {
            final Issue issue = issueDao.findByName(issueName);
            if (issue != null) {
                final List<Commit> commits = commitDao.findByIssue(issue);

                // Files list from all these commits
                final Set<File> files = new HashSet<>();
                for (final Commit commit : commits) {
                    files.addAll(commit.getFiles());
                }

                // Gets the commits list from all these files
                final Set<Commit> commitsOfFiles = new HashSet<>();
                for (final File file : files) {
                    final List<Commit> commitsByFile = commitDao.findByFile(file);
                    commitsOfFiles.addAll(commitsByFile);
                }

                // Set of issues from these commits
                for (final Commit commit : commitsOfFiles) {
                    issues.add(commit.getIssue());
                }
                issues.remove(issue);
            }
            LOGGER.debug("issues.size : " + issues.size());
        }
        return issues;
    }

    @Override
    public Issue extractIssuesFromMessage(String message) {
        Issue issue = null;

        if (StringUtils.isNotBlank(message)) {
            int pos = StringUtils.upperCase(message).indexOf(ISSUE_PATTERN);

            if (pos > -1) {
                issue = new Issue();
                String issueName = "";
                pos = pos + ISSUE_PATTERN.length();

                for (int i = pos; i < message.length(); i++) {
                    final String c = String.valueOf(message.charAt(i));
                    if (StringUtils.isNumeric(c)) {
                        issueName = issueName + c;
                    } else {
                        break;
                    }
                }
                issue.setName(ISSUE_PATTERN + issueName);
            }
        }
        return issue;
    }

    /**
     * @return the issueDao
     */
    public IssueDao getIssueDao() {
        return issueDao;
    }

    /**
     * @param issueDao the issueDao to set
     */
    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    /**
     * @return the commitDao
     */
    public CommitDao getCommitDao() {
        return commitDao;
    }

    /**
     * @param commitDao the commitDao to set
     */
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }

}
