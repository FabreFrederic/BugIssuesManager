package com.fabrefrederic.webapp.scheduler;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.service.interfaces.CommitService;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {
	private static final Logger LOGGER = Logger.getLogger(SchedulerConfiguration.class);

	@Autowired
	private CommitService commitService;

	@Value("${repository.path}")
	String repositoryPath;

	@Scheduled(cron = "${scheduler.cron}")
	public void printMessage() {
		System.out.println("I am called by Spring scheduler - system");
		LOGGER.debug("I am called by Spring scheduler");
	}

	@Scheduled(cron = "${scheduler.cron}")
	public void saveNewCommitsFromRepository() {
		// TODO : delete all the system.out
		System.out.println("saveNewCommitsFromRepository() started : " + new Date());
		LOGGER.debug("saveNewCommitsFromRepository() started : " + new Date());

		final Commit lastCommit = commitService.getTheLastSavedCommit();
		if (lastCommit != null) {
			System.out.println("lastCommit.getNumber() : " + lastCommit.getNumber());
			System.out.println("lastCommit.getDate() : " + lastCommit.getDate());
			LOGGER.debug("lastCommit.getNumber() : " + lastCommit.getNumber());
			LOGGER.debug("lastCommit.getDate() : " + lastCommit.getDate());
			final List<Commit> commits = commitService.getCommitsToTheLastRevision(repositoryPath, Long.valueOf(lastCommit.getNumber()));
			if (commits != null && commits.size() > 0) {
				commits.remove(lastCommit);
				commitService.saveCommits(commits);
			}
		} else {
			System.out.println("No commit found in datebase, we have to retrieve the commits in database before launching the cron");
			LOGGER.debug("No commit found in datebase, we have to retrieve the commits in database before launching the cron");
		}

	}
}
