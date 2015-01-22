/**
 *
 */
package com.fabrefrederic.service;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"service/src/test/resources/com/fabrefrederic/test/service/spring/applicationContext-service.xml"})
@ContextConfiguration(locations = {"classpath:applicationContext-dao-test.xml"})
public class CommitServiceTest  {

    @Test
    public void getAllAlbum() {

        // TODO : delete this line
        System.out.println("test");
    }
}
