package com.fabrefrederic.dao.interfaces;

import java.util.List;

import javax.persistence.NoResultException;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.Issue;

public interface CommitDao extends GenericDao<Commit> {

    /**
     * Find the most recent commit saved in DB. <br />
     * If several commits have the same date, we return the first found.
     *
     * @return the commit, otherwise null
     */
    Commit findTheMostRecentCommit();

    /**
     * Find a commit by its number
     *
     * @param commitNumber commit number
     * @return the commit if found, otherwise null
     * @throws IllegalArgumentException if no commit number
     */
    Commit findByCommitNumber(String commitNumber) throws IllegalArgumentException, NoResultException;

    /**
     * Find a list of commits by an issue
     *
     * @param issue
     * @return list of commits
     * @throws IllegalArgumentException
     * @throws NoResultException
     */
    List<Commit> findByIssue(Issue issue) throws IllegalArgumentException, NoResultException;
}
