package com.fabrefrederic.dao;

import org.springframework.stereotype.Component;

import com.fabrefrederic.business.Issue;
import com.fabrefrederic.dao.interfaces.IssueDao;

@Component("issueDaoHibernate")
public class IssueDaoHibernate extends DaoHibernate<Issue> implements IssueDao {

	private static final long serialVersionUID = 1L;

}
