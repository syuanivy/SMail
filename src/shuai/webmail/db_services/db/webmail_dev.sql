PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE incoming(id TXT PRIMARY KEY, sender TXT, recipient TXT NOT NULL, subject TXT, body TXT, attached INT DEFAULT 0, label INT DEFAULT 0, time TXT DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (recipient) REFERENCES accounts(email_address));
INSERT INTO "incoming" VALUES('gmail0001','syuanivy@gmail.com','syuanivy@gmail.com','receive1','is trouble!',0,0,'2014-11-09 02:36:25');
INSERT INTO "incoming" VALUES('gmail0002','yuanshuaikey@163.com','syuanivy@gmail.com','receive2','is it working?',0,0,'2014-11-09 02:36:26');
INSERT INTO "incoming" VALUES('fakeID1','fakesender@gmail.com','syuanivy@gmail.com','fake subject','+OK Gpop ready for requests from 24.5.180.36 s71mb147807938ios
+OK send PASS
+OK Welcome.
+OK 6 6261
',0,0,'2014-11-10 16:33:28');
INSERT INTO "incoming" VALUES('fakeID231','fakesender@gmail.com','syuanivy@gmail.com','fake subject','+OK Gpop ready for requests from 138.202.218.92 ri15mb20732188iec
+OK send PASS
+OK Welcome.
+OK 4 2131
+OK message follows
Bcc: syuanivy@gmail.com
Return-Path: <syuanivy@gmail.com>
Received: from smtp.gmail.com ([24.5.180.36])
        by mx.google.com with SMTP id h6sm17031897pdk.38.2014.11.10.08.41.30
        for <syuanivy@gmail.com>
        (version=TLSv1 cipher=ECDHE-RSA-RC4-SHA bits=128/128);
        Mon, 10 Nov 2014 08:41:35 -0800 (PST)
Message-ID: <5460eabf.a626460a.5698.0eba@mx.google.com>
Date: Mon, 10 Nov 2014 08:41:35 -0800 (PST)
From: syuanivy@gmail.com
Subject: second try saving outgoing to db

forgot to call the addFunction
.
',0,0,'2014-11-10 23:23:55');
CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,username TXT UNIQUE NOT NULL, password TXT NOT NULL);
INSERT INTO "users" VALUES(1,'shuai','shuai');
INSERT INTO "users" VALUES(2,'ivy','ivy');
INSERT INTO "users" VALUES(3,'su','xyz');
INSERT INTO "users" VALUES(4,'danyang',456);
INSERT INTO "users" VALUES(5,'huan','abc');
INSERT INTO "users" VALUES(6,'parrt',123);
INSERT INTO "users" VALUES(7,'wangwang',123);
INSERT INTO "users" VALUES(8,'hihi',123);
INSERT INTO "users" VALUES(9,'dd',111);
INSERT INTO "users" VALUES(10,'ddd',11);
INSERT INTO "users" VALUES(11,'ww',11);
INSERT INTO "users" VALUES(12,'mimi',666);
INSERT INTO "users" VALUES(13,'mengmeng',123);
INSERT INTO "users" VALUES(14,'doudou','doudou');
INSERT INTO "users" VALUES(15,'snail','snail');
INSERT INTO "users" VALUES(16,'miaomiao','miaomiao');
INSERT INTO "users" VALUES(17,'syuanivy','syuanivy');
INSERT INTO "users" VALUES(18,'xitao','xitao');
CREATE TABLE accounts(email_address TEXT PRIMARY KEY,smtp TEXT NOT NULL, smtp_port INTEGER NOT NULL, pop TEXT NOT NULL, pop_port INTEGER NOT NULL, ssl INTEGER DEFAULT 1, username TEXT NOT NULL, password TEXT NOT NULL, my_user TEXT NOT NULL, FOREIGN KEY (my_user) REFERENCES users(username));
INSERT INTO "accounts" VALUES('syuanivy@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'syuanivy','624426@ivy!!!','ivy');
INSERT INTO "accounts" VALUES('yuanshuaikey@163.com','smtp.163.com',465,'pop.163.com',995,1,'yuanshuaikey','624426ivy','shuai');
INSERT INTO "accounts" VALUES('mengmeng@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'mengmeng','123','mengmeng');
INSERT INTO "accounts" VALUES('doudou@sina.com','smtp.sina.com',25,'pop.sina.com',110,0,'doudou','doudou','doudou');
INSERT INTO "accounts" VALUES('snail@sina.com','smtp.sina.com',25,'pop.sina.com',110,0,'snail','snail','snail');
INSERT INTO "accounts" VALUES('miaomiao@163.com','smtp.sina.com',25,'pop.sina.com',110,0,'miaomiao','miaomiao','miaomiao');
INSERT INTO "accounts" VALUES('cs601ceshi@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'cs601ceshi','12345678wxt','xitao');
CREATE TABLE outgoing(id INTEGER PRIMARY KEY AUTOINCREMENT, sender TXT NOT NULL, recipient TXT, subject TXT, body TXT, attached INT DEFAULT 0, sent INT DEFAULT 0, time TXT DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (sender) REFERENCES accounts(email_address));
INSERT INTO "outgoing" VALUES(1,'syuanivy@gmail.com','syuanivy@gmail.com','db','is trouble!',0,1,'2014-11-09 02:30:22');
INSERT INTO "outgoing" VALUES(2,'yuanshuaikey@163.com','syuanivy@gmail.com',163,'is it working?',0,1,'2014-11-09 02:32:13');
INSERT INTO "outgoing" VALUES(3,'syuanivy@gmail.com','syuanivy@gmail.com','third try saving outgoing to db','forgot to restart the server',0,1,'2014-11-10 16:43:32');
INSERT INTO "outgoing" VALUES(4,'syuanivy@gmail.com','syuanivy@gmail.com','compose window test','send button',0,1,'2014-11-10 18:35:56');
INSERT INTO "outgoing" VALUES(5,'syuanivy@gmail.com','syuanivy@gmail.com','DBConnection class not loaded','why is that?',0,1,'2014-11-10 23:21:51');
INSERT INTO "outgoing" VALUES(6,'cs601ceshi@gmail.com','cs601ceshi@gmail.com','from shuai''s server','hahaha',0,1,'2014-11-11 02:25:19');
INSERT INTO "outgoing" VALUES(7,'syuanivy@gmail.com','ivyandscorpio@gmail.com','test','testtest',0,1,'2014-11-22 02:47:57');
INSERT INTO "outgoing" VALUES(8,'syuanivy@gmail.com','syuanivy@gmail.com','let''s try to save it into the db','and then display them properly',0,1,'2014-11-22 05:57:45');
INSERT INTO "outgoing" VALUES(9,'syuanivy@gmail.com','syuanivy@gmail.com','debug new smtp','what?',0,1,'2014-11-22 07:15:48');
INSERT INTO "outgoing" VALUES(10,'syuanivy@gmail.com','k.kpjs4s@gmail.com','halo from the non-threaded smtp',':-)',0,1,'2014-11-22 07:18:09');
INSERT INTO "outgoing" VALUES(11,'syuanivy@gmail.com','syuanivy@gmail.com','compose sesssion bug fixed','test outgoing into db',0,1,'2014-11-22 07:59:34');
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('users',18);
INSERT INTO "sqlite_sequence" VALUES('outgoing',11);
COMMIT;
