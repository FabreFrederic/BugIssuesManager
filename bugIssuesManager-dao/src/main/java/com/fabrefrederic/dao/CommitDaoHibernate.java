package com.fabrefrederic.dao;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.dao.interfaces.CommitDao;

@Component("CommitDaoHibernate")
public class CommitDaoHibernate extends DaoHibernate<Commit> implements CommitDao {
    private static final Logger LOGGER = Logger.getLogger(CommitDaoHibernate.class);
    private static final long serialVersionUID = 1L;

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
    @Transactional
    public Commit findByCommitNumber(String commitNumber) throws IllegalArgumentException {
        Commit commitResult = null;

        if (StringUtils.isBlank(commitNumber)) {
            throw new IllegalArgumentException("A commit number must be set");
        }
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Commit> criteriaQuery = criteriaBuilder.createQuery(Commit.class);
        final Root<Commit> root = criteriaQuery.from(Commit.class);

        criteriaQuery.select(root);
        final TypedQuery<Commit> typeQuery = getEntityManager().createQuery(criteriaQuery);
        final ParameterExpression<String> param = criteriaBuilder.parameter(String.class);
        typeQuery.setParameter(param, commitNumber);

        commitResult = typeQuery.getSingleResult();
        if (commitResult != null) {
            LOGGER.debug("A commit has been found with the commit number : " + commitNumber);
        }
        else {
            LOGGER.debug("No commit found with the commit number : " + commitNumber);
        }
        return commitResult;

    }

    @Override
    public Commit findTheMostRecentCommit() {
        final Commit commitResult = null;

        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Commit> criteriaQuery = criteriaBuilder.createQuery(Commit.class);
        final Root<Commit> root = criteriaQuery.from(Commit.class);

        final Expression<Object> minExpression = criteriaBuilder.min(root.get("date"));
        final CriteriaQuery<Object> select = criteriaQuery.select(minExpression);

        final TypedQuery<Commit> typeQuery = getEntityManager().createQuery(criteriaQuery);
        commitResult = typeQuery.getSingleResult();

        if (commitResult != null) {
            LOGGER.debug("A commit has been found");
        }
        else {
            LOGGER.debug("No commit found");
        }
        return commitResult;

    }
}
