package com.fabrefrederic.dao;

import javax.persistence.NoResultException;

import junit.framework.Assert;

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

    /**
     * When we search for a commit by its commit number, we have to find one commit with this number
     */
    @Test
    public void findByCommitNumberTest() {
        // given
        final String commitNumber = "1001";

        // when
        final Commit commit = commitDao.findByCommitNumber(commitNumber);

        // then
        Assert.assertNotNull(commit);
        Assert.assertEquals(commit.getNumber(), commitNumber);
    }

    /**
     * When we search for a commit by a wrong commit number, we have to find no commit
     */
    @Test(expected = NoResultException.class)
    public void findByCommitNumberWithWrongNumber() {
        // given
        final String commitNumber = "9999";

        // when
        final Commit commit = commitDao.findByCommitNumber(commitNumber);

        // then
        Assert.assertNull(commit);
    }

    /**
     * When we search for a commit by a wrong commit number, we have to find no commit
     */
    @Test(expected = IllegalArgumentException.class)
    public void findByCommitNumberWithoutNumberMustFailed() {
        // given
        final String blanckCommitNumber = "";
        final String nullCommitNumber = null;

        // when
        commitDao.findByCommitNumber(blanckCommitNumber);
        commitDao.findByCommitNumber(nullCommitNumber);
    }

    @Test
    public void findTheMostRecentCommitTest() {
        // when
        final Commit commit = commitDao.findTheMostRecentCommit();

        // then
        Assert.assertNotNull(commit);
        System.out.println(commit.getNumber());
    }
}
