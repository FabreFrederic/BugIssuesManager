package com.fabrefrederic.library;

import java.util.List;

import com.fabrefrederic.business.Commit;

public interface RevisionControlSystem {

    /**
     * Asks a limited number of logs from the repository
     *
     * @param path the path to the trunk or the branch
     * @param startRevision start revision number
     * @param endRevision end revision number
     * @param limit the maximum number of log entries to process
     * @return a list of {@code Commit}
     * @throws Exception
     */
    List<Commit> getLogs(final String path, final String startRevision, final String endRevision, final Integer limit) throws Exception;

    /**
     * Asks logs from the repository from the start revision to the last revision
     *
     * @param path the path to the trunk or the branch wanted
     * @param startRevision start revision number
     * @param limit the maximum number of log entries to process
     * @return a list of {@code Commit}
     * @throws Exception
     */
    List<Commit> getLogsToTheLastRevision(String path, String startRevision, final Integer limit) throws Exception;

    /**
     * @return The last {@code Commit} from the repository
     * @param path the path to the trunk or the branch
     * @throws Exception
     */
    Commit getTheLastCommitFromRepository(final String path) throws Exception;

    /**
     * @return The first {@code Commit} from the repository
     * @param path the path to the trunk or the branch
     * @throws Exception
     */
    Commit getTheFirstCommitFromRepository(final String path) throws Exception;

}
