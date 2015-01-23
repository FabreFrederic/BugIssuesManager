create table file (
    file_id int8 not null,
    file_name varchar(255),
    file_path varchar(255),
    primary key (file_id)
);

ALTER TABLE file
ALTER COLUMN file_id
SET DEFAULT nextval('bugIssuesManager_id_seq');

-- commit_file int8 not null,
create table commit (
    commit_id int8 not null,
    commit_number varchar(255),
    commit_date timestamp with time zone,
    commit_author varchar(80),
    commit_issue int8,
    commit_message varchar(255),
    primary key (commit_id)
);

ALTER TABLE commit
ALTER COLUMN commit_id
SET DEFAULT nextval('bugIssuesManager_id_seq');

create table issue (
    issue_id int8 not null,
    issue_name varchar(10),
    issue_description varchar(255),
    primary key (issue_id)
);

ALTER TABLE issue
ALTER COLUMN issue_id
SET DEFAULT nextval('bugIssuesManager_id_seq');
