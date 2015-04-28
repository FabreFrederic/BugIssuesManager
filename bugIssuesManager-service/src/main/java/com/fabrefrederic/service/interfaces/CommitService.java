package com.fabrefrederic.service.interfaces;

import java.util.List;

import com.fabrefrederic.business.Commit;

/**
 * Commit service
 *
 */
public interface CommitService {

    /**
     * Get the commits from the repository from the start to the end given revision
     *
     * @param path : path on the repository
     * @param startRevision : the start revision
     * @param endRevision : the end revision
     * @param limit the maximum number of commits to retrieve
     */
    List<Commit> getCommits(final String path, final String startRevision, final String endRevision, final Integer limit);

    /**
     * Gets the commits from the given start revision to the last revision on the repository
     *
     * @param path : path on the repository
     * @param startRevision : the start revision
     * @return the list of commits
     */
    List<Commit> getCommitsToTheLastRevision(final String path, final String startRevision, final Integer limit);

    /**
     * Gets the last commit saved in DB
     *
     * @return the commit found
     */
    Commit getTheLastSavedCommit();

    /**
     * Gets the last commit from the repository
     *
     * @param path : path on the repository
     * @return the commit found
     */
    Commit getTheLastCommitFromRepository(final String path);

    /**
     * Gets the first commit from the repository
     *
     * @param path : path on the repository
     * @return the commit found
     */
    Commit getTheFirstCommitFromRepository(final String path);

    /**
     * It saves the commits in database
     *
     * @param commits
     */
    void saveCommits(final List<Commit> commits);

}
