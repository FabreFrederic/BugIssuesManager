--
insert into file (file_id, file_name, file_path) values (1, 'MyFile.java', '/trunk/dir1/dir2');
insert into issue (issue_id, issue_name, issue_description) values (1, 'ABC-100', 'Here is my issue description');

insert into commit (commit_id, commit_number, commit_date, commit_author, commit_message, commit_issue_id) 
values (1, '123456', current_date, 'Fred', 'ABC-100 : the bug is fixed', 1);

insert into commit_file (commit_id, file_id) values (1, 1);