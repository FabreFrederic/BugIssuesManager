/**
 *
 */
package com.fabrefrederic.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.service.interfaces.CommitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/service/spring/applicationContext-service.xml",
		"classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-datasource.xml",
		"classpath:/com/fabrefrederic/dao/spring/applicationContext-dao-model.xml",
"classpath:/com/fabrefrederic/dao/spring/applicationContext-dao.xml" })
public class CommitServiceTest {

	@Autowired
	private CommitService commitService;

	@Ignore
	@Test
	public void getAllCommit() {
		final List<Commit> commits = commitService.getCommits("/trunk/", 1, 5);
		commitService.saveCommits(commits);

		// for (final Commit commit : commits) {
		// System.out.println(commit.getNumber());
		// System.out.println("author : " + commit.getAuthor());
		// System.out.println("date : " + commit.getDate());
		// System.out.println("message : " + commit.getMessage());
		// if (commit.getFiles() != null) {
		// final List<File> files = commit.getFiles();
		// for (final File file : files) {
		// System.out.println("name : " + file.getName());
		// System.out.println("path : " + file.getPath());
		// }
		// }
		// }
	}
}
