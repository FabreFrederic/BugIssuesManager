package com.fabrefrederic.dao;

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

	@Test
	public void findByCommitNumberTest() {
		// given
		final String commitNumber = "123456";
		final int commitId = 1;

		// when
		final Commit commit = commitDao.findByCommitNumber(commitNumber);

		// then
		Assert.assertNotNull(commit);
		Assert.assertEquals(commit.getId(), commitId);
	}
}
