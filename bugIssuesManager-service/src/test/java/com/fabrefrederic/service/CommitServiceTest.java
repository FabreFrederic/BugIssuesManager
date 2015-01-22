/**
 *
 */
package com.fabrefrederic.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"service/src/test/resources/com/fabrefrederic/service/spring/applicationContext-service.xml", 
		"classpath:applicationContext-dao.xml", "classpath:applicationContext-library.xml"})
public class CommitServiceTest  {

    @Test
    public void getAllAlbum() {

        // TODO : delete this line
        System.out.println("test");
    }
}
