/**
 *
 */
package com.fabrefrederic.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.service.interfaces.CommitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/com/fabrefrederic/service/spring/applicationContext-service.xml"})

public class CommitServiceTest  {
    //    private static final Logger LOGGER = Logger.getLogger(CommitServiceTest.class);
    @Autowired
    private CommitService commitService;

    @Test
    public void getAllCommit() {
        //        LOGGER.debug("getAllCommit - begin");
        final List<Commit> commits = commitService.getCommits(1, 10);
        for (final Commit commit : commits) {
            System.out.println(commit.getNumber());
        }
    }
}
