package com.fabrefrederic.webapp.scheduler;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fabrefrederic.business.Commit;
import com.fabrefrederic.business.Issue;
import com.fabrefrederic.service.interfaces.CommitService;
import com.fabrefrederic.service.interfaces.IssueService;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {
    private static final Logger LOGGER = Logger.getLogger(SchedulerConfiguration.class);

    @Autowired
    private CommitService commitService;

    @Autowired
    private IssueService issueService;

    @Value("${repository.path}")
    String repositoryPath;

    @Value("${search.limit}")
    String searchLimit;

    @Scheduled(cron = "${scheduler.cron}")
    public void saveNewCommitsFromRepository() {
        LOGGER.debug("saveNewCommitsFromRepository() started : " + new Date());

        final Integer limit = getSearchLimit();

        Commit firstCommit = commitService.getTheLastSavedCommit();
        if (firstCommit == null) {
            LOGGER.debug("The first commit has not been find in DB");
            firstCommit = commitService.getTheFirstCommitFromRepository(repositoryPath);
        }

        if (firstCommit != null) {
            LOGGER.debug("First commit number from DB : " + firstCommit.getNumber());
            final List<Commit> commits = commitService.getCommitsToTheLastRevision(repositoryPath,
                    firstCommit.getNumber(), limit);
            if (commits != null && commits.size() > 0) {
                for (final Commit commit : commits) {
                    final Issue issue = issueService.getIssuesFromMessage(commit.getMessage());
                    commit.setIssue(issue);
                }
                LOGGER.debug("Number of commits to save from the repository : " + commits.size());
                commitService.saveCommits(commits);
            } else {
                LOGGER.info("No new commits from the repository");
            }
        } else {
            LOGGER.warn("The first commit has not been find from the repository");
        }
    }

    /**
     * @return the search limit
     */
    private Integer getSearchLimit() {
        Integer limit = null;
        if (StringUtils.isNotBlank(searchLimit)) {
            try {
                limit = Integer.valueOf(searchLimit);
            } catch (final NumberFormatException exception) {
                LOGGER.warn("Search limit does not have the appropriate format or is not is not a number", exception);
            }
        } else {
            limit = 10;
            LOGGER.warn("Search limit is not configured. default value is 10");
        }
        return limit;
    }

}
