insert into file (file_id, file_name, file_path) values (1, 'MyFile1.java', '/trunk/dir1/dir1');
insert into file (file_id, file_name, file_path) values (2, 'MyFile2.java', '/trunk/dir2/dir2');

insert into issue (issue_id, issue_name, issue_description) values (1, 'ABC-100', 'Here is my issue 1 description');
insert into issue (issue_id, issue_name, issue_description) values (2, 'ABC-200', 'Here is my issue 2 description');

-- First commit
insert into commit (commit_id, commit_number, commit_date, commit_author, commit_message, commit_issue_id)
values (1, '1001', current_date, 'Fred', 'ABC-100 : the bug is fixed', 1);

insert into commit_file (commit_id, file_id) values (1, 1);
insert into commit_file (commit_id, file_id) values (1, 2);

-- Last commit
insert into commit (commit_id, commit_number, commit_date, commit_author, commit_message, commit_issue_id)
values (2, '1002', timestamp'2099-03-28 10:00:00', 'Loann', 'ABC-100 : the bug is fixed again', 1);

insert into commit_file (commit_id, file_id) values (2, 1);
insert into commit_file (commit_id, file_id) values (2, 2);