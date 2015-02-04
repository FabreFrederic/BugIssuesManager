package com.fabrefrederic.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-test.xml" })
public class CommitDaoHibernateTest {

    @Test
    public void test() {

    }
}
