package com.fabrefrederic.library.impl;

import java.io.IOException;
import java.util.Collection;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SvnRevisionControlSystem {
    
    /**
     * Svn client manager
     * 
     * @param url
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    private SVNClientManager getSvnClientManager(final String url, final String username, final String password) throws IOException {
        SVNURL svnUrl = null;
        try {
            svnUrl = SVNURL.parseURIEncoded(url);
        } catch (final SVNException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(svnUrl, null);
        } catch (final SVNException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        final ISVNOptions myOptions = SVNWCUtil.createDefaultOptions(true);
        final ISVNAuthenticationManager myAuthManager = SVNWCUtil.createDefaultAuthenticationManager(username,
                password);
        repository.setAuthenticationManager(myAuthManager);
        
        final SVNClientManager clientManager = SVNClientManager.newInstance(myOptions, myAuthManager);

        return clientManager;
    }

    private Collection<SVNLogEntry> getLogs(final SVNRepository svnRepository, final long startRevision, final long endRevision) {
        Collection<SVNLogEntry> logEntries = null;
        try {
            logEntries = svnRepository.log(new String[] { "/trunk/js/web/src/main/webapp" }, null, 
                    startRevision, endRevision, true, true);
        } catch (final SVNException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return logEntries;
    }
}
