package com.fabrefrederic.library.impl;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.File;
import com.fabrefrederic.business.Issue;
import com.fabrefrederic.library.RevisionControlSystem;

public class SvnRevisionControlSystem extends AbstractRevisionControlSystem {
	private static final Logger LOGGER = Logger
			.getLogger(SvnRevisionControlSystem.class);
	private static SVNRepository repository;
	// TODO : use it or delete it
	private static SVNClientManager clientManager;

	/**
	 * Constructor
	 *
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public SvnRevisionControlSystem(final String url, final String username,
			final String password) throws IOException {
		LOGGER.debug("url : " + url);
		LOGGER.debug("username : " + username);
		LOGGER.debug("password : " + password);
		SVNURL svnUrl = null;

		try {
			svnUrl = SVNURL.parseURIEncoded(url);
		} catch (final SVNException e) {
			LOGGER.error(e);
		}
		try {
			repository = SVNRepositoryFactory.create(svnUrl, null);
		} catch (final SVNException e) {
			LOGGER.error(e);
		}

		final ISVNOptions option = SVNWCUtil.createDefaultOptions(true);
		final ISVNAuthenticationManager authenticationManager = SVNWCUtil
				.createDefaultAuthenticationManager(username, password);
		repository.setAuthenticationManager(authenticationManager);
		clientManager = SVNClientManager.newInstance(option,
				authenticationManager);
	}

	@Override
	public List<Commit> getLogs(final String path, final long startRevision,
			final long endRevision) throws Exception {
		if (repository == null) {
			LOGGER.error("The repository is not instanciate");
			throw new Exception("The repository is not instanciate");
		}
		if (StringUtils.isBlank(path)) {
			LOGGER.error("the svn path must be specified");
			throw new InvalidParameterException(
					"the svn path must be specified");
		}

		Collection<SVNLogEntry> logEntries = new ArrayList<SVNLogEntry>();
		// The revision is the map id
		final Map<Long, SVNDirEntry> svnDirEntries = new HashMap<Long, SVNDirEntry>();
		try {
			logEntries = repository.log(new String[] { path }, null,
					startRevision, endRevision, true, true);

			for (final SVNLogEntry svnLogEntry : logEntries) {
				svnDirEntries.put(svnLogEntry.getRevision(), repository.getDir(
						path, svnLogEntry.getRevision(), true, null));
			}

		} catch (final SVNException e) {
			LOGGER.error(e);
			throw new Exception("Error while getting svn logs", e);
		} catch (final Exception e) {
			LOGGER.error(e);
			throw new Exception(e);
		}
		return convert(logEntries, svnDirEntries);
	}

	@Override
	public List<Commit> getLogsToTheLastRevision(final String path,
			final long startRevision) throws Exception {
		if (repository == null) {
			LOGGER.error("The repository is not instanciate");
			throw new Exception("The repository is not instanciate");
		}
		if (StringUtils.isBlank(path)) {
			LOGGER.error("the svn path must be specified");
			throw new InvalidParameterException(
					"the svn path must be specified");
		}
		return this
				.getLogs(path, startRevision, repository.getLatestRevision());
	}

	/**
	 * Converts SVNLogEntries and SVNDirEntries into a list of business objects
	 * "commits"
	 *
	 * @param svnLogEntries
	 * @param svnDirEntries
	 * @return a list of populated commits
	 */
	private List<Commit> convert(Collection<SVNLogEntry> svnLogEntries,
			Map<Long, SVNDirEntry> svnDirEntries) {
		final List<Commit> commits = new ArrayList<Commit>();

		for (final SVNLogEntry svnLogEntry : svnLogEntries) {
			// Commit
			final Commit commit = new Commit();
			commit.setAuthor(svnLogEntry.getAuthor());
			commit.setDate(svnLogEntry.getDate());
			commit.setNumber(Long.toString(svnLogEntry.getRevision()));

			// File
			final List<File> files = new ArrayList<File>();
			final Set<Map.Entry<String, SVNLogEntryPath>> entries = svnLogEntry
					.getChangedPaths().entrySet();
			for (final Entry<String, SVNLogEntryPath> entry : entries) {
				final File file = new File();
				file.setPath(entry.getKey());
				files.add(file);
			}
			commit.setFiles(files);

			// Comment
			final SVNDirEntry svnDirEntry = svnDirEntries.get((svnLogEntry)
					.getRevision());
			if (svnDirEntry != null) {
				String comment = svnDirEntry.getCommitMessage();
				commit.setMessage(comment);
				String issueName = this.getIssueNameFromComment(comment);
				if (StringUtils.isNotBlank(issueName)) {
					// Issue
					Issue issue = new Issue();
					issue.setName(issueName);
					commit.setIssue(issue);
				}
			}

			commits.add(commit);
		}
		return commits;
	}

}
