package com.fabrefrederic.service.interfaces;

import java.util.List;

import com.fabrefrederic.business.Commit;

public interface CommitService {

    /**
     * Get the commits from the svn repository from the start to the end given revision
     *
     * @param path : path on the svn repository
     * @param startRevision : the start revision
     * @param endRevision : the end revision
     */
    List<Commit> getCommits(String path, long startRevision, long endRevision);

    /**
     * Gets the commits from the given start revision to the last revision on the svn repository
     *
     * @param path : path on the svn repository
     * @param startRevision : the start revision
     * @return the list of commits
     */
    List<Commit> getCommitsToTheLastRevision(String path, long startRevision);


    /**
     * Gets the last commit saved in DB
     *
     * @return the commit found
     */
    Commit getTheLastSavedCommit();

    /**
     * It saves the commits in database
     * @param commits
     */
    void saveCommits(List<Commit> commits);

}
