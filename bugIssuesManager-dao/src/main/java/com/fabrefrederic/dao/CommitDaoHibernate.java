package com.fabrefrederic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.Commit_;
import com.fabrefrederic.business.Issue;
import com.fabrefrederic.dao.interfaces.CommitDao;
import com.fabrefrederic.dao.interfaces.IssueDao;

@Component("commitDaoHibernate")
public class CommitDaoHibernate extends DaoHibernate<Commit> implements CommitDao {
	private static final Logger LOGGER = Logger.getLogger(CommitDaoHibernate.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private IssueDao issueDao;

	/**
	 * Constructor
	 *
	 * @param persistentClass
	 */
	public CommitDaoHibernate(Class<Commit> persistentClass) {
		super(persistentClass);
	}

	/**
	 * Default constructor
	 */
	public CommitDaoHibernate() {
		super();
	}

	@Override
	@Transactional
	public void save(final Commit commit) {
		try {
			// TODO : check if commit already exist in DB
		} catch (final Exception e) {
			LOGGER.error(e);
		}
		super.save(commit);
	}

	@Override
	@Transactional(noRollbackFor = NoResultException.class)
	public Commit findByCommitNumber(String commitNumber) throws IllegalArgumentException, NoResultException {
		Commit commitResult = null;

		if (StringUtils.isBlank(commitNumber)) {
			LOGGER.error("The commit number cannot be null or empty");
			throw new IllegalArgumentException("A commit number cannot be null or empty");
		}

		final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Commit> criteriaQuery = builder.createQuery(Commit.class);

		final Root<Commit> root = criteriaQuery.from(Commit.class);
		final ParameterExpression<String> paramExpression = builder.parameter(String.class);
		criteriaQuery.select(root).where(builder.equal(root.get(Commit_.number), paramExpression));

		final TypedQuery<Commit> typedQuery = getEntityManager().createQuery(criteriaQuery);
		typedQuery.setParameter(paramExpression, commitNumber);

		try {
			commitResult = typedQuery.getSingleResult();
		} catch (final NoResultException noResultException) {
			LOGGER.info("No commit found with the commit number : " + commitNumber);
			LOGGER.debug(noResultException);
			throw noResultException;
		} catch (final Exception exception) {
			LOGGER.error(exception);
			throw exception;
		}

		if (LOGGER.isDebugEnabled()) {
			if (commitResult != null) {
				LOGGER.debug("A commit has been found");
			}
		}
		return commitResult;
	}

	@Override
	@Transactional(noRollbackFor = NoResultException.class)
	public Commit findTheMostRecentCommit() {
		Commit resultCommit = null;
		List<Commit> commits = null;

		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Commit> criteriaQuery = criteriaBuilder.createQuery(Commit.class);
		final Root<Commit> from = criteriaQuery.from(Commit.class);
		final Path<Date> path = from.get(Commit_.date);
		final CriteriaQuery<Commit> select = criteriaQuery.select(from);

		final Subquery<Date> subquery = criteriaQuery.subquery(Date.class);
		final Root<Commit> subfrom = subquery.from(Commit.class);
		subquery.select(criteriaBuilder.greatest(subfrom.get(Commit_.date)));
		select.where(criteriaBuilder.in(path).value(subquery));

		try {
			final TypedQuery<Commit> typedQuery = getEntityManager().createQuery(criteriaQuery);
			commits = typedQuery.getResultList();
		} catch (final NoResultException noResultException) {
			LOGGER.info("No commit found");
			LOGGER.debug(noResultException);
			throw noResultException;
		} catch (final Exception exception) {
			LOGGER.error(exception);
			throw exception;
		}

		if (LOGGER.isDebugEnabled()) {
			if (commits != null) {
				LOGGER.debug("A commit has been found");
			}
		}
		if (commits != null && !commits.isEmpty()) {
			resultCommit = commits.get(0);
		}
		return resultCommit;
	}

	@Override
	@Transactional(noRollbackFor = NoResultException.class)
	public List<Commit> findByIssueId(Issue issue) throws IllegalArgumentException, NoResultException {
		if (issue == null) {
			LOGGER.error("The issue cannot be null");
			throw new IllegalArgumentException("The issue cannot be null");
		}

		List<Commit> commits = new ArrayList<>();

		final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Commit> criteriaQuery = builder.createQuery(Commit.class);

		final Root<Commit> root = criteriaQuery.from(Commit.class);
		final ParameterExpression<Issue> paramExpression = builder.parameter(Issue.class);
		criteriaQuery.select(root).where(builder.equal(root.get(Commit_.issue), paramExpression));

		final TypedQuery<Commit> typedQuery = getEntityManager().createQuery(criteriaQuery);
		typedQuery.setParameter(paramExpression, issue);

		try {
			commits = typedQuery.getResultList();
		} catch (final NoResultException noResultException) {
			LOGGER.info("No commit found with the issue id : " + issue.getId());
			LOGGER.debug(noResultException);
			throw noResultException;
		} catch (final Exception exception) {
			LOGGER.error(exception);
			throw exception;
		}

		if (LOGGER.isDebugEnabled()) {
			if (commits != null) {
				LOGGER.debug(commits.size() + " commits have been found");
			}
		}
		return commits;
	}
}
