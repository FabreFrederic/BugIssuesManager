/**
 *
 */
package com.fabrefrederic.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.dao.interfaces.CommitDao;
import com.fabrefrederic.service.interfaces.CommitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/service/spring/applicationContext-service.xml",
		"classpath:/com/fabrefrederic/dao/spring/applicationContext-dao.xml"})
public class CommitServiceTest {
	private static final Logger LOGGER = Logger.getLogger(CommitServiceTest.class);
	@Autowired
	private CommitService commitService;
	@Autowired
	private CommitDao commitDao;
	
	@Test
	public void getAllCommit() {
		LOGGER.debug("getAllCommit - begin");
		final List<Commit> commits = commitService.getCommits("/trunk/", 1, 5);
		
		for (final Commit commit : commits) {
			commitDao.save(commit);
//			System.out.println(commit.getNumber());
//			System.out.println("author : " + commit.getAuthor());
//			System.out.println("date : " + commit.getDate());
//			System.out.println("message : " + commit.getMessage());
//			if (commit.getFiles() != null) {
//				final List<File> files = commit.getFiles();
//				for (final File file : files) {
//					System.out.println("name : " + file.getName());
//					System.out.println("path : " + file.getPath());
//				}
//			}
		}
	}
}
