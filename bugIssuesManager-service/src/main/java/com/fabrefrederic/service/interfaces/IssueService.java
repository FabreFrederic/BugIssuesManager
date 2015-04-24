package com.fabrefrederic.service.interfaces;

import java.util.Set;

import com.fabrefrederic.business.Issue;

public interface IssueService {

    /**
     * Returns the issues affected by another new issue
     *
     * @param issueId of the new issue
     * @return the list of issues
     */
    public Set<Issue> getAffectedIssuesByIssueId(Integer issueId);
}
