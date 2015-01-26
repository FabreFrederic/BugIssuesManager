package com.fabrefrederic.library.impl;

import org.apache.commons.lang3.StringUtils;

import com.fabrefrederic.library.RevisionControlSystem;

public abstract class AbstractRevisionControlSystem implements RevisionControlSystem {

	/**
	 * 
	 * @param comment
	 * @return
	 */
	public String getIssueNameFromComment(String comment) {
		final StringBuilder issueName = new StringBuilder();
		if (StringUtils.isNotBlank(comment)) {
			final String ISSUE_TAG = "AIC-";
			int begin = comment.indexOf(ISSUE_TAG);
			if (begin != -1) {
				// The tag issue is found
				issueName.append(ISSUE_TAG);
				for (int i = begin + ISSUE_TAG.length(); i < comment.length(); i++) {
					String a = Character.toString(comment.charAt(i));
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
