package com.fabrefrederic.library.impl;

import org.apache.commons.lang3.StringUtils;

import com.fabrefrederic.library.RevisionControlSystem;

public abstract class AbstractRevisionControlSystem implements RevisionControlSystem {

    /**
     * Extracts the issue name from the commit message
     *
     * @param commit message
     * @return the issue name
     */
    public String extractIssueNameFromMessage(String message) {
        final StringBuilder issueName = new StringBuilder();
        if (StringUtils.isNotBlank(message)) {
            final String ISSUE_TAG = "AIC-";
            final int begin = message.indexOf(ISSUE_TAG);
            if (begin != -1) {
                // The issue tag is found
                issueName.append(ISSUE_TAG);
                for (int i = begin + ISSUE_TAG.length(); i < message.length(); i++) {
                    final String a = Character.toString(message.charAt(i));
                    if (StringUtils.isNumeric(a)) {
                        issueName.append(a);
                    }
                    else {
                        break;
                    }
                }
            }
        }

        return issueName.toString();
    }
}
