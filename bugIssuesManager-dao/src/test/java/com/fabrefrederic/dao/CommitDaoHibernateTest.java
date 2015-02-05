package com.fabrefrederic.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.dao.interfaces.CommitDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-test.xml" })
public class CommitDaoHibernateTest {
    @Autowired
    CommitDao commitDao;

    @Test
    public void test() {
        final Commit commit = commitDao.findByCommitNumber("1");
        if (commit != null) {
            System.out.println("toto");
        }
    }
}
