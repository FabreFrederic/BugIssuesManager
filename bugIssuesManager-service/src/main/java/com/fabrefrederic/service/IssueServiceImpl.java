package com.fabrefrederic.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Autowired
	private IssueDao issueDao;

	@Autowired
	private CommitDao commitDao;

	@Override
	@Transactional
	public Set<Issue> getAffectedIssuesByIssueId(Integer issueId) {
		LOGGER.debug("issueId : " + issueId);

		// Result set
		final Set<Issue> issues = new HashSet<>();

		// Gets the commits list from the issueId
		final Issue issue = issueDao.findById(issueId);
		if (issue != null) {
			final List<Commit> commits = commitDao.findByIssueId(issue);

			// File list from the all these commits
			final Set<File> files = new HashSet<>();
			for (final Commit commit : commits) {
				files.addAll(commit.getFiles());
			}

			// Gets the commits list from all these files
			final Set<Commit> commitsOfFiles = new HashSet<>();
			for (final File file : files) {
				commitsOfFiles.addAll(file.getCommits());
			}

			// Set of issues from these commits
			for (final Commit commit : commitsOfFiles) {
				issues.add(commit.getIssue());
			}
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
