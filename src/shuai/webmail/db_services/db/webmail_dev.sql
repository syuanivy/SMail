PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE incoming(id TXT PRIMARY KEY, sender TXT, recipient TXT NOT NULL, subject TXT, body TXT, attached INT DEFAULT 0, label INT DEFAULT 0, time TXT DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (recipient) REFERENCES accounts(email_address));
INSERT INTO "incoming" VALUES('gmail0001','syuanivy@gmail.com','syuanivy@gmail.com','receive1','is trouble!',0,0,'2014-11-09 02:36:25');
INSERT INTO "incoming" VALUES('gmail0002','yuanshuaikey@163.com','syuanivy@gmail.com','receive2','is it working?',0,0,'2014-11-09 02:36:26');
CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,username TXT UNIQUE NOT NULL, password TXT NOT NULL);
INSERT INTO "users" VALUES(1,'shuai','shuai');
INSERT INTO "users" VALUES(2,'ivy','ivy');
INSERT INTO "users" VALUES(3,'su','xyz');
INSERT INTO "users" VALUES(4,'danyang',456);
INSERT INTO "users" VALUES(5,'huan','abc');
CREATE TABLE accounts(email_address TEXT PRIMARY KEY,smtp TEXT NOT NULL, smtp_port INTEGER NOT NULL, pop TEXT NOT NULL, pop_port INTEGER NOT NULL, ssl INTEGER DEFAULT 1, username TEXT NOT NULL, password TEXT NOT NULL, my_user TEXT NOT NULL, FOREIGN KEY (my_user) REFERENCES users(username));
INSERT INTO "accounts" VALUES('syuanivy@gmail.com','smtp.gmail.com',465,'pop.gmail.com',110,1,'syuanivy','624426@ivy!!!','ivy');
INSERT INTO "accounts" VALUES('yuanshuaikey@163.com','smtp.163.com',25,'pop.163.com',110,0,'yuanshuaikey','624426ivy','shuai');
CREATE TABLE outgoing(id INTEGER PRIMARY KEY AUTOINCREMENT, sender TXT NOT NULL, recipient TXT, subject TXT, body TXT, attached INT DEFAULT 0, sent INT DEFAULT 0, time TXT DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (sender) REFERENCES accounts(email_address));
INSERT INTO "outgoing" VALUES(1,'syuanivy@gmail.com','syuanivy@gmail.com','db','is trouble!',0,0,'2014-11-09 02:30:22');
INSERT INTO "outgoing" VALUES(2,'yuanshuaikey@163.com','syuanivy@gmail.com',163,'is it working?',0,0,'2014-11-09 02:32:13');
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('users',5);
INSERT INTO "sqlite_sequence" VALUES('outgoing',2);
COMMIT;