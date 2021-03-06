CREATE TABLE EMPLOYEE (
EID INTEGER PRIMARY KEY,
ENAME VARCHAR2(100) NOT NULL,
EDEPARTMENT INTEGER,
EMAIL VARCHAR2(20),
EPASSWORD VARCHAR2(20),
REPORTSTO INTEGER,
CONSTRAINT Unique_Email UNIQUE (EMAIL),
FOREIGN KEY (REPORTSTO) REFERENCES EMPLOYEE
);

ALTER TABLE EMPLOYEE ADD TOTALAMOUNT NUMBER;
Commit;
ALTER TABLE EMPLOYEE ADD CONSTRAINT Fk_Department FOREIGN KEY (EDEPARTMENT) REFERENCES DEPARTMENT (DEPARTMENTID);
INSERT INTO EMPLOYEE VALUES (1, 'Test Test', null, 'test@test','test',1);
Commit;
Select EPASSWORD
FROM EMPLOYEE
WHERE EMAIL='test@test';

CREATE TABLE REQUEST (
RID INTEGER PRIMARY KEY,
EID INTEGER,
FDATE DATE,
--FTIME TIME,
LOC VARCHAR2(100),
FDESCRIPTION VARCHAR2(100),
REASON VARCHAR2(100),
FCOST NUMBER,
RAMOUNT NUMBER,
FORMATID INTEGER,
EVENTID INTEGER,
STATUS VARCHAR2(100),
TIMEMISSED NUMBER,
EXCREASON VARCHAR2(200),
DENIAL VARCHAR2(200),
FOREIGN KEY (FORMATID) REFERENCES GRADEFORMAT,
FOREIGN KEY (EVENTID) REFERENCES EVENTTYPE
);

CREATE TABLE GRADEFORMAT (
FORMATID INTEGER PRIMARY KEY,
FDESCRIPTION VARCHAR2(100),
REQUIREMENT VARCHAR(50)
);

CREATE TABLE EVENTTYPE (
EVENTID INTEGER PRIMARY KEY,
EDESCRIPTION VARCHAR2(100),
COVERAGE INTEGER
);

CREATE TABLE DEPARTMENT (
DEPARTMENTID INTEGER PRIMARY KEY,
DNAME VARCHAR2(50),
HEAD INTEGER,
FOREIGN KEY (HEAD) REFERENCES EMPLOYEE
);

CREATE TABLE RFILE (
FID INTEGER PRIMARY KEY,
RID INTEGER,
FDESCRIPTION VARCHAR2(200),
FILETYPE VARCHAR2(20),
FPATH VARCHAR2(100),
FOREIGN KEY (RID) REFERENCES REQUEST
);

CREATE SEQUENCE REIM_REQ_IDS
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER TR_INSERT_REQUEST
BEFORE INSERT ON REQUEST
FOR EACH ROW
BEGIN
    SELECT REIM_REQ_IDS.NEXTVAL
    INTO :NEW.RID FROM DUAL;
END;

--Data for Event Types and Grade Formats
INSERT INTO EVENTTYPE VALUES (1, 'University Course', 80);
INSERT INTO EVENTTYPE VALUES (2, 'Seminar', 60);
INSERT INTO EVENTTYPE VALUES (3, 'Certification Preparation Class', 75);
INSERT INTO EVENTTYPE VALUES (4, 'Certification', 100);
INSERT INTO EVENTTYPE VALUES (5, 'Technical Training', 90);
INSERT INTO EVENTTYPE VALUES (6, 'Other', 30);
INSERT INTO GRADEFORMAT VALUES (1, 'Graded', 'Grade');
INSERT INTO GRADEFORMAT VALUES (2, 'Pass/Fail', 'Grade');
INSERT INTO GRADEFORMAT VALUES (3, 'Completion', 'Presentation');
INSERT INTO GRADEFORMAT VALUES (4, 'Participation', 'Presentation');
Insert Into DEPARTMENT VALUES (1, 'Benco', 1);
Commit;

Delete From REQUEST WHERE EID = 0;

Update EMPLOYEE Set EDEPARTMENT = 1 WHERE EID = 1;
Commit;