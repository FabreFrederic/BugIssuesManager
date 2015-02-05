ALTER TABLE file
ALTER COLUMN file_id
SET DEFAULT bugIssuesManager_id_seq.nextval('bugIssuesManager_id_seq');

ALTER TABLE commit
ALTER COLUMN commit_id
SET DEFAULT bugIssuesManager_id_seq.nextval('bugIssuesManager_id_seq');

ALTER TABLE issue
ALTER COLUMN issue_id
SET DEFAULT bugIssuesManager_id_seq.nextval('bugIssuesManager_id_seq');