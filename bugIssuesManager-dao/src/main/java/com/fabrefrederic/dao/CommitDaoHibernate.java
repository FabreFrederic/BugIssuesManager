package com.fabrefrederic.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.Commit_;
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
    @Transactional(noRollbackFor = NoResultException.class)
    public Commit findByCommitNumber(String commitNumber) throws IllegalArgumentException, NoResultException {
        Commit commitResult = null;

        if (StringUtils.isBlank(commitNumber)) {
            LOGGER.error("A commit number cannot be null or empty");
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
        Commit commitResult = null;

        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Commit> query = builder.createQuery(Commit.class);

        final Root<Commit> commit = query.from(Commit.class);
        query.multiselect(commit.get(Commit_.number), builder.greatest(commit.get(Commit_.date)));
        query.groupBy(commit.get(Commit_.number));

        try {
            final TypedQuery<Commit> typedQuery = getEntityManager().createQuery(query);
            commitResult = typedQuery.getSingleResult();
        } catch (final NoResultException noResultException) {
            LOGGER.info("No commit found");
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
}
