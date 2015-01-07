package com.fabrefrederic.dao.persistance;

import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/com/fabrefrederic/dao/spring/applicationContext-dao-test.xml" })
public abstract class AbstractJpaDaoTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private SessionFactory sessionFactory;

}
