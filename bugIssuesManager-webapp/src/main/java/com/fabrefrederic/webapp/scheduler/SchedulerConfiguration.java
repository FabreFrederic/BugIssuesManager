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
        // TODO : develop a method to know the running time
        System.out.println("saveNewCommitsFromRepository() started : " + new Date());
        LOGGER.debug("saveNewCommitsFromRepository() started : " + new Date());

        Commit firstCommit = commitService.getTheLastSavedCommit();
        if (firstCommit != null) {
            System.out.println("First commit found in DB");
            System.out.println("firstCommit.getNumber() : " + firstCommit.getNumber());
            System.out.println("firstCommit.getDate() : " + firstCommit.getDate());
            LOGGER.debug("firstCommit.getNumber() : " + firstCommit.getNumber());
            LOGGER.debug("firstCommit.getDate() : " + firstCommit.getDate());
        } else {
            System.out.println("First commit found in repository");
            firstCommit = commitService.getTheFirstCommitFromRepository(repositoryPath);
            System.out.println("firstCommit.getNumber() : " + firstCommit.getNumber());
            System.out.println("firstCommit.getDate() : " + firstCommit.getDate());
        }
        // TODO : move the limit in a configuration file
        final List<Commit> commits = commitService.getCommitsToTheLastRevision(repositoryPath,
                firstCommit.getNumber(), 2);
        if (commits != null && commits.size() > 0) {
            commits.remove(firstCommit);
            commitService.saveCommits(commits);
        }
        else {
            System.out.println("No commit found");
        }

    }

    /**
     * @param commitService the commitService to set
     */
    public void setCommitService(CommitService commitService) {
        this.commitService = commitService;
    }
}
