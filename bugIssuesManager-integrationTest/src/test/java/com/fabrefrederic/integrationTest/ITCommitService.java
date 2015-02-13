/**
 *
 */
package com.fabrefrederic.integrationTest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.service.interfaces.CommitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/service/spring/applicationContext-service.xml" })
public class ITCommitService {

    private static final Logger LOGGER = Logger.getLogger(ITCommitService.class);
    @Autowired
    private CommitService commitService;

    @Test
    @Transactional
    public void getCommitsFromStartToEndRevision() {
        // given
        final int commitNumber = 5;
        final int startRevision = 1;
        final int endRevision = 5;
        final String repositoryPath = "/trunk/";

        // when
        List<Commit> commits = new ArrayList<Commit>();
        try {
            commits = commitService.getCommits(repositoryPath, startRevision, endRevision);
        } catch (final Exception exception) {
            LOGGER.error("Error during getting commits from the svn repository", exception);
            Assert.fail("Error during getting commits from the svn repository");
        }
        try {
            commitService.saveCommits(commits);
        }
        catch (final Exception exception) {
            LOGGER.error("Error during saving commits into the database", exception);
            Assert.fail("Error during saving commits into the database");
        }

        // then
        Assert.assertEquals(commits.size(), commitNumber);

    }
}
