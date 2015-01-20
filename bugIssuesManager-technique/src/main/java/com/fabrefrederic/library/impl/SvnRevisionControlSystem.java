package com.fabrefrederic.library.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
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

        final ISVNOptions myOptions = SVNWCUtil.createDefaultOptions(true);
        final ISVNAuthenticationManager myAuthManager = SVNWCUtil.createDefaultAuthenticationManager(username,
                password);
        repository.setAuthenticationManager(myAuthManager);
        clientManager = SVNClientManager.newInstance(myOptions, myAuthManager);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<SVNLogEntry> getLogs(final long startRevision, final long endRevision) {
        Collection<SVNLogEntry> logEntries = null;
        try {
            logEntries = repository.log(new String[] { "/trunk/js/web/src/main/webapp" }, null,
                    startRevision, endRevision, true, true);
        } catch (final SVNException e) {
            LOGGER.error(e);
        }
        return logEntries;
    }

    /**
     * Converts svnlogs into commits
     *
     * @param svnLogs
     * @return a list fo commits
     */
    // TODO : Complete this method and manage the file, or paths of files ??
    private List<Commit> convert(List<SVNLogEntry> svnLogEntries) {
        final List<Commit> commits = new ArrayList<Commit>();
        for (final SVNLogEntry svnLogEntry : svnLogEntries) {
            final Commit commit = new Commit();
            commit.setAuthor(svnLogEntry.getAuthor());
            commit.setDate(svnLogEntry.getDate());
            final File file = new File();
            // file.setName(???);
            commit.setNumber(Long.toString(svnLogEntry.getRevision()));
            // commit.setFiles(svnLogEntry.getChangedPaths());

            commits.add(commit);
        }
        return commits;
    }
}
