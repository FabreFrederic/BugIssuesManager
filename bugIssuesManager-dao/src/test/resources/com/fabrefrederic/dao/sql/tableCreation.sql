-- File
create table file (
    file_id integer not null,
    file_name varchar(255),
    file_path varchar(255),
    primary key (file_id)
);

-- Commit
create table commit (
    commit_id integer not null,
    commit_number varchar(255) unique,
    commit_date timestamp with time zone,
    commit_author varchar(80),
    commit_issue_id integer,
    commit_message varchar(1000),
    primary key (commit_id)
);

-- Commit file
create table commit_file (
    commit_id integer not null,
    file_id integer not null,
    primary key (commit_id, file_id)
);

-- Issue
create table issue (
    issue_id integer not null,
    issue_name varchar(10),
    issue_description varchar(255),
    primary key (issue_id)
);
