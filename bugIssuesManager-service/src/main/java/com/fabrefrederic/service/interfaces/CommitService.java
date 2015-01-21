package com.fabrefrederic.service.interfaces;

import java.util.List;

import com.fabrefrederic.business.Commit;

public interface CommitService {

    /**
     *
     * @return
     */
    List<Commit> getCommits(long startRevision, long endRevision);

    /**
     *
     * @param commits
     */
    void saveCommits(List<Commit> commits);

}
