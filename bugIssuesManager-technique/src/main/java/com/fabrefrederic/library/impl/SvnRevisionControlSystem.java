package com.fabrefrederic.library.impl;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
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

public class SvnRevisionControlSystem extends AbstractRevisionControlSystem {
    private static final Logger LOGGER = Logger.getLogger(SvnRevisionControlSystem.class);
    private static SVNRepository repository;
    // TODO : use it or delete it
    private static SVNClientManager clientManager;

    /**
     * Constructor
     *
     * @param url
     * @param username
     * @param password
     * @throws IOException
     */
    public SvnRevisionControlSystem(final String url, final String username, final String password) throws IOException {
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
        final ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(username,
                password);
        repository.setAuthenticationManager(authenticationManager);
        clientManager = SVNClientManager.newInstance(option, authenticationManager);
    }

    @Override
    public List<Commit> getLogs(final String path, final String startRevision, final String endRevision, final Integer limit) throws Exception {
        LOGGER.debug("path : " + path);
        LOGGER.debug("startRevision : " + startRevision);
        LOGGER.debug("endRevision : " + endRevision);
        LOGGER.debug("limit : " + limit);

        if (repository == null) {
            LOGGER.error("The repository is not instanciate");
            throw new Exception("The repository is not instanciate");
        }
        if (StringUtils.isBlank(path)) {
            LOGGER.error("The svn path must be specified");
            throw new InvalidParameterException("The svn path must be specified");
        }

        final Collection<SVNLogEntry> logEntries = new ArrayList<SVNLogEntry>();
        // The revision is the map id
        final Map<Long, SVNDirEntry> svnDirEntries = new HashMap<Long, SVNDirEntry>();
        final long svnStartRevision = Long.parseLong(startRevision);
        final long svnEndRevision = Long.parseLong(endRevision);
        try {
            repository.log(new String[] {path}, svnStartRevision, svnEndRevision, true, true,  limit,
                    false, null, new ISVNLogEntryHandler() {
                @Override
                public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
                    logEntries.add(logEntry);
                }
            });

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
    public List<Commit> getLogsToTheLastRevision(final String path, final String startRevision, final Integer limit) throws Exception {
        if (repository == null) {
            LOGGER.error("The repository is not instanciate");
            throw new Exception("The repository is not instanciate");
        }
        if (StringUtils.isBlank(path)) {
            LOGGER.error("The svn path must be specified");
            throw new InvalidParameterException("The svn path must be specified");
        }
        final String svnEndRevision = String.valueOf(repository.getLatestRevision());
        return getLogs(path, startRevision, svnEndRevision, limit);
    }

    @Override
    public Commit getTheLastCommitFromRepository(final String path) throws Exception {
        if (repository == null) {
            LOGGER.error("The repository is not instanciate");
            throw new Exception("The repository is not instanciate");
        }
        if (StringUtils.isBlank(path)) {
            LOGGER.error("The svn path must be specified");
            throw new InvalidParameterException("The svn path must be specified");
        }
        Commit commit = null;
        final String revisionNumber = String.valueOf(repository.getLatestRevision());
        final List<Commit> commits = getLogs(path, revisionNumber, revisionNumber, 1);
        if (commits != null && commits.size() > 0) {
            commit = commits.get(0);
        }
        return commit;
    }

    @Override
    public Commit getTheFirstCommitFromRepository(String path) throws Exception {
        Commit commit = null;
        Integer firstCommitNumber = 1;
        String begin = null;
        final Integer searchLimit = 10;
        Integer lastCommitNumber = firstCommitNumber + searchLimit;
        String end = null;
        final Integer loopLimit = 1000000;
        List<Commit> commits = new ArrayList<>();

        while (commits.size() < 1 && firstCommitNumber < loopLimit) {
            begin = Integer.toString(firstCommitNumber);
            end = Integer.toString(lastCommitNumber);

            commits = getLogs(path, begin, end, searchLimit);

            firstCommitNumber = lastCommitNumber;
            lastCommitNumber = firstCommitNumber + searchLimit;
        }
        if (commits != null && commits.size() > 0) {
            commit = commits.get(0);
        }
        return commit;
    }

    /**
     * Converts SVNLogEntries and SVNDirEntries into a list of business objects "commits"
     *
     * @param svnLogEntries
     * @param svnDirEntries
     * @return a list of populated commits
     */
    private List<Commit> convert(Collection<SVNLogEntry> svnLogEntries, Map<Long, SVNDirEntry> svnDirEntries) {
        final List<Commit> commits = new ArrayList<Commit>();

        for (final SVNLogEntry svnLogEntry : svnLogEntries) {
            // Commit
            final Commit commit = new Commit();
            commit.setAuthor(svnLogEntry.getAuthor());
            commit.setDate(svnLogEntry.getDate());
            commit.setNumber(Long.toString(svnLogEntry.getRevision()));

            // File
            final List<File> files = new ArrayList<File>();
            final Set<Map.Entry<String, SVNLogEntryPath>> entries = svnLogEntry.getChangedPaths().entrySet();
            for (final Entry<String, SVNLogEntryPath> entry : entries) {
                // We only want to keep the files, and not the repositories
                final String fileName = getFileNameFromPath(entry.getKey());
                if (fileName != null) {
                    final File file = new File();
                    file.setName(fileName);
                    file.setPath(entry.getKey());
                    files.add(file);
                }
            }
            commit.setFiles(files);

            // Commit message
            final SVNDirEntry svnDirEntry = svnDirEntries.get((svnLogEntry).getRevision());
            if (svnDirEntry != null) {
                final String message = svnDirEntry.getCommitMessage();
                commit.setMessage(message);
                final String issueName = super.extractIssueNameFromMessage(message);
                if (StringUtils.isNotBlank(issueName)) {
                    // Issue
                    final Issue issue = new Issue();
                    issue.setName(issueName);
                    commit.setIssue(issue);
                }
            }
            commits.add(commit);
        }
        return commits;
    }

    /**
     * Get the file name from the path <br />
     * The file must have a dot and an extension
     *
     * @param path
     * @return file name, null if no file found
     */
    protected String getFileNameFromPath(String path) {
        String fileName = FilenameUtils.getName(path);
        if (fileName != null) {
            // A file must have an extension and a dot
            if (!fileName.contains(".")) {
                fileName = null;
            }
        }
        return fileName;
    }

}
