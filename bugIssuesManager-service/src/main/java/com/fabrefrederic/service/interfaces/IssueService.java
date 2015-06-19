package com.fabrefrederic.service.interfaces;

import java.util.TreeSet;

import com.fabrefrederic.business.Issue;

public interface IssueService {

    /**
     * Returns the issues affected by another new issue
     *
     * @param issue name of the new issue
     * @return the list of issues
     */
    public TreeSet<Issue> getAffectedIssuesByIssueId(String issueName);

    /**
     * get the issues from the message
     *
     * @param message
     * @return an issues
     */
    public Issue extractIssuesFromMessage(String message);
}
