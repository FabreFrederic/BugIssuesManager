package com.fabrefrederic.dao;


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

    public Integer findByCommiNumber(String commitNumber) {
        //TODO : develop method
        return null;
    }
}
