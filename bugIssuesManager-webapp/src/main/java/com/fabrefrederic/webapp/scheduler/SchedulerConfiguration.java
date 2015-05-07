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

    @Value("${search.limit}")
    String searchLimit;

    @Scheduled(cron = "${scheduler.cron}")
    public void saveNewCommitsFromRepository() {
        // Default value
        if (searchLimit == null) {
            searchLimit = "10";
        }
        final Integer limit = Integer.valueOf(searchLimit);

        // TODO : develop a method to know the running time
        LOGGER.debug("saveNewCommitsFromRepository() started : " + new Date());

        Commit firstCommit = commitService.getTheLastSavedCommit();
        if (firstCommit == null) {
            firstCommit = commitService.getTheFirstCommitFromRepository(repositoryPath);
        }
        final List<Commit> commits = commitService.getCommitsToTheLastRevision(repositoryPath,
                firstCommit.getNumber(), limit);
        if (commits != null && commits.size() > 0) {
            commitService.saveCommits(commits);
        }
        else {
            LOGGER.info("No commit found");
        }

    }

    /**
     * @param commitService the commitService to set
     */
    public void setCommitService(CommitService commitService) {
        this.commitService = commitService;
    }
}
