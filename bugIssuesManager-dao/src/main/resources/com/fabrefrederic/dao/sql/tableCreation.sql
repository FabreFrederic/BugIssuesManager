-- File
create table file (
    file_id integer not null,
    file_name varchar(255),
    file_path varchar(255),
    primary key (file_id)
);
ALTER TABLE file
ALTER COLUMN file_id
SET DEFAULT nextval('bugIssuesManager_id_seq');
ALTER TABLE file OWNER TO fred;

-- Commit
create table commit (
    commit_id integer not null,
    commit_number varchar(255),
    commit_date timestamp with time zone,
    commit_author varchar(80),
    commit_issue_id integer,
    commit_message varchar(255),
    primary key (commit_id)
);
ALTER TABLE commit
ALTER COLUMN commit_id
SET DEFAULT nextval('bugIssuesManager_id_seq');
ALTER TABLE commit OWNER TO fred;

-- Commit file
create table commit_file (
    commit_id integer not null,
    file_id integer not null,
    primary key (commit_id, file_id)
);
ALTER TABLE commit_file OWNER TO fred;

-- Issue
create table issue (
    issue_id integer not null,
    issue_name varchar(10),
    issue_description varchar(255),
    primary key (issue_id)
);
ALTER TABLE issue
ALTER COLUMN issue_id
SET DEFAULT nextval('bugIssuesManager_id_seq');
ALTER TABLE issue OWNER TO fred;
