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

import com.fabrefrederic.business.Issue;
import com.fabrefrederic.business.Issue_;
import com.fabrefrederic.dao.interfaces.IssueDao;

@Component("issueDaoHibernate")
public class IssueDaoHibernate extends DaoHibernate<Issue> implements IssueDao {
    private static final Logger LOGGER = Logger.getLogger(IssueDaoHibernate.class);
    private static final long serialVersionUID = 1L;

    @Override
    public Issue findByName(String name) throws NoResultException {
        Issue issueResult = null;

        if (StringUtils.isBlank(name)) {
            LOGGER.error("The issue name cannot be null or empty");
            throw new IllegalArgumentException("The issue name cannot be null or empty");
        }

        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Issue> criteriaQuery = builder.createQuery(Issue.class);

        final Root<Issue> root = criteriaQuery.from(Issue.class);
        final ParameterExpression<String> paramExpression = builder.parameter(String.class);
        criteriaQuery.select(root).where(builder.equal(root.get(Issue_.name), paramExpression));

        final TypedQuery<Issue> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setParameter(paramExpression, name);

        try {
            issueResult = typedQuery.getSingleResult();
        } catch (final NoResultException noResultException) {
            LOGGER.info("No issue found with the name : " + name);
            LOGGER.debug(noResultException);
            throw noResultException;
        } catch (final Exception exception) {
            LOGGER.error(exception);
            throw exception;
        }

        if (LOGGER.isDebugEnabled()) {
            if (issueResult != null) {
                LOGGER.debug("An issue has been found");
            }
        }
        return issueResult;
    }

}
