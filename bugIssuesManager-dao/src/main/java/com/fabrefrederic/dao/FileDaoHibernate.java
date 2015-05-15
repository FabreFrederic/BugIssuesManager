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

import com.fabrefrederic.business.File;
import com.fabrefrederic.business.File_;
import com.fabrefrederic.dao.interfaces.FileDao;

@Component("fileDaoHibernate")
public class FileDaoHibernate extends DaoHibernate<File> implements FileDao {
    private static final Logger LOGGER = Logger.getLogger(FileDaoHibernate.class);
    private static final long serialVersionUID = 1L;

    @Override
    public File findByPath(String path) throws NoResultException {
        File fileResult = null;

        if (StringUtils.isBlank(path)) {
            LOGGER.error("The file path cannot be null or empty");
            throw new IllegalArgumentException("The file path cannot be null or empty");
        }

        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<File> criteriaQuery = builder.createQuery(File.class);

        final Root<File> root = criteriaQuery.from(File.class);
        final ParameterExpression<String> paramExpression = builder.parameter(String.class);
        criteriaQuery.select(root).where(builder.equal(root.get(File_.path), paramExpression));

        final TypedQuery<File> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setParameter(paramExpression, path);

        try {
            fileResult = typedQuery.getSingleResult();
        } catch (final NoResultException noResultException) {
            LOGGER.info("No file found with the path : " + path);
            LOGGER.debug(noResultException);
            throw noResultException;
        } catch (final Exception exception) {
            LOGGER.error(exception);
            throw exception;
        }

        if (LOGGER.isDebugEnabled()) {
            if (fileResult != null) {
                LOGGER.debug("A file has been found");
            }
        }
        return fileResult;
    }
}
