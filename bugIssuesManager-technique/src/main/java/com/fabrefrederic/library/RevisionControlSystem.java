package com.fabrefrederic.library;

import java.util.Collection;

import org.tmatesoft.svn.core.SVNLogEntry;


/**
 *
 *
 */
public interface RevisionControlSystem {

    Collection<SVNLogEntry> getLogs(long startRevision, long endRevision);

}
