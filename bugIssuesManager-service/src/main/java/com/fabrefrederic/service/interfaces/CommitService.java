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
     * @param commits
     */
    // TODO : comment
    void saveCommits(List<Commit> commits);

}
