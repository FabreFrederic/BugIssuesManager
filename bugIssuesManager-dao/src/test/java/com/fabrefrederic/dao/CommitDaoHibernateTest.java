package com.fabrefrederic.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-model.xml",
        "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-datasource.xml",
"classpath:/com/fabrefrederic/dao/spring/applicationContext-dao.xml" })
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
        // given
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse("2099-03-28 10:00:00");
        } catch (final ParseException e) {
            Assert.fail("Error while initializing junit data sets");
        }

        // when
        final Commit commit = commitDao.findTheMostRecentCommit();

        // then
        Assert.assertNotNull(commit);
        Assert.assertNotNull("The commit date should not be null", commit.getDate());
        Assert.assertTrue(date.equals(commit.getDate()));
        Assert.assertNotNull("The commit number should not be null", commit.getNumber());
    }
}
