PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE users(id INT PRIMARY KEY NOT NULL,username TXT NOT NULL, password TXT NOT NULL);
INSERT INTO "users" VALUES(1,'shuai',123);
INSERT INTO "users" VALUES(2,'su','xyz');
INSERT INTO "users" VALUES(3,'danyang',456);
INSERT INTO "users" VALUES(4,'huan','abc');
CREATE TABLE emails(id INT PRIMARY KEY NOT NULL, sender TXT, recipient TXT, subject TXT, body TXT, attached INT DEFAULT 0);
INSERT INTO "emails" VALUES(1,'syuan6@usfca.edu','nov22-brother@hotmail.com','shuaitosu','hello su!',0);
INSERT INTO "emails" VALUES(2,'nov22-brother@hotmail.com','ivyandscorpio@hotmail.com','sutoshuai','hello shuai!',0);
COMMIT;
