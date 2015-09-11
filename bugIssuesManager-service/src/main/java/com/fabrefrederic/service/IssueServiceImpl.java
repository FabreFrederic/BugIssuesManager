package com.fabrefrederic.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.File;
import com.fabrefrederic.business.Issue;
import com.fabrefrederic.business.dto.IssueDto;
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

    @Autowired
    private Mapper dozerBeanMapper;

    @Override
    @Transactional
    public TreeSet<IssueDto> getAffectedIssuesByIssueId(String issueName) {
        LOGGER.debug("issueName : " + issueName);
        final TreeSet<IssueDto> issues = new TreeSet<IssueDto>();

        if (StringUtils.isNotBlank(issueName)) {
            Issue issueSearch = null;
            try {
                issueSearch = issueDao.findByName(issueName);
            } catch (final NoResultException noResultException) {
                // TODO : Display a message to the user
                LOGGER.info("This issue has not been found - issueName : " + issueName);
            }
            if (issueSearch != null) {
                final List<Commit> commits = commitDao.findByIssue(issueSearch);

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
                    if (commit.getIssue() != null) {
                        final IssueDto issueDto = dozerBeanMapper.map(commit.getIssue(), IssueDto.class);
                        issueDto.setDescription(commit.getMessage());
                        issues.add(issueDto);
                    }
                }
                final IssueDto issueToRemoveFromSearch = dozerBeanMapper.map(issueSearch, IssueDto.class);
                issues.remove(issueToRemoveFromSearch);
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
