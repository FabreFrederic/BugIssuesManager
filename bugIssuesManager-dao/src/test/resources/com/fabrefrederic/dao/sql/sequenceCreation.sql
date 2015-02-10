-- This allows to use the postgresql syntax in HSQLDB, for example the nextval()
-- On the other side the sequence creation is a HSQLDB syntax
SET DATABASE SQL SYNTAX PGS true;

-- Sequence: bugIssuesManager_id_seq
 CREATE SEQUENCE bugIssuesManager_id_seq
  INCREMENT BY 1
  START WITH 1;
