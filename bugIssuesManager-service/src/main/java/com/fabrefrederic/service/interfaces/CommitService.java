package com.fabrefrederic.service.interfaces;

import java.util.List;

import com.fabrefrederic.business.Commit;

public interface CommitService {

    /**
     *
     * @param commits
     */
    // TODO : comment
    List<Commit> getCommits(String path, long startRevision, long endRevision);

    /**
     *
     * @param path
     * @param startRevision
     * @return
     */
    // TODO : comment
    List<Commit> getCommitsToTheLastRevision(String path, long startRevision);


    /**
     * Gets the last commit saved in DB
     *
     * @return commit
     */
    Commit getTheLastSavedCommit();

    /**
     *
     * @param commits
     */
    // TODO : comment
    void saveCommits(List<Commit> commits);

}
