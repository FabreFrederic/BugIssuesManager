package com.fabrefrederic.service;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fabrefrederic.business.Issue;
import com.fabrefrederic.service.interfaces.IssueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/service/spring/applicationContext-service.xml",
        "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-datasource.xml",
        "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-model.xml",
        "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao.xml" })
public class IssueServiceTest {

    @Autowired
    private IssueService issueService;

    @Ignore
    @Test
    // TODO to implement this test
    public void getAffectedIssuesByIssueNameTest() {
        // given
        final String issueName = "AIC-1000";

        // when
        final Set<Issue> issues = issueService.getAffectedIssuesByIssueId(issueName);

        // then
        Assert.assertNotNull(issues);
        Assert.assertTrue(issues.size() > 0);
    }
}
