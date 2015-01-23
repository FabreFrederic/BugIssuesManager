package com.fabrefrederic.library.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
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
import com.fabrefrederic.library.RevisionControlSystem;

public class SvnRevisionControlSystem implements RevisionControlSystem {
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
     * @return
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

    // TODO : develop specific exception type
    @Override
    public List<Commit> getLogs(final long startRevision, final long endRevision) throws Exception {
        if (repository == null) {
            throw new Exception();
        }
        Collection<SVNLogEntry> logEntries = null;
        try {
            logEntries = repository.log(new String[] { "/trunk/js/web/src/main/webapp" }, null,
                    startRevision, endRevision, true, true);
        } catch (final SVNException e) {
            LOGGER.error(e);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
        return convert(logEntries);
    }

    /**
     * Converts svnlogs into business objects "commits"
     *
     * @param svnLogs
     * @return a list of commits
     */
    private List<Commit> convert(Collection<SVNLogEntry> svnLogEntries) {
        final List<Commit> commits = new ArrayList<Commit>();
        for (final SVNLogEntry svnLogEntry : svnLogEntries) {
            // Commit
            final Commit commit = new Commit();
            commit.setAuthor(svnLogEntry.getAuthor());
            commit.setDate(svnLogEntry.getDate());
            commit.setNumber(Long.toString(svnLogEntry.getRevision()));

            // File
            final List<File> files = new ArrayList<File>();
            final Set<Map.Entry<String,SVNLogEntryPath>> entries = svnLogEntry.getChangedPaths().entrySet();
            for (final Entry<String, SVNLogEntryPath> entry : entries) {
                final File file = new File();
                file.setPath(entry.getKey());
                files.add(file);
            }
            commit.setFiles(files);
            commits.add(commit);
        }
        return commits;
    }
}
