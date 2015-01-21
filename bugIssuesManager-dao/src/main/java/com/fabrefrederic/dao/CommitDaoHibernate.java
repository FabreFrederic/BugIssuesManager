package com.fabrefrederic.dao;

import org.springframework.stereotype.Component;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.dao.interfaces.CommitDao;

@Component("CommitDaoHibernate")
public class CommitDaoHibernate extends DaoHibernate<Commit> implements CommitDao {
    /** UID */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param persistentClass
     */
    public CommitDaoHibernate(Class<Commit> persistentClass) {
        super(persistentClass);
    }

}
