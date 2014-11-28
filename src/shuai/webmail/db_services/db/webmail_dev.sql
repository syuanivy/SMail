PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE incoming(id TXT PRIMARY KEY, sender TXT, recipient TXT NOT NULL, subject TXT, body TXT, attached INT DEFAULT 0, label INT DEFAULT 0, time TXT DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (recipient) REFERENCES accounts(email_address));
INSERT INTO "incoming" VALUES('GmailId149d8bf73becbfe0','syuanivy@gmail.com','syuanivy@gmail.com','get rid of the first colon in the sender and recipient string','using substring(1)
.
',0,0,'2014-11-22 18:24:29');
INSERT INTO "incoming" VALUES('GmailId149d571fd811c026','syuanivy@gmail.com','ivyandscorpio@gmail.com','test','testtest
.
',0,0,'2014-11-23 01:42:02');
INSERT INTO "incoming" VALUES('GmailId149da004b97bcef5','syuanivy@gmail.com','syuanivy@gmail.com','requestLogHandler added to handers','try out
.
',0,0,'2014-11-23 01:42:03');
INSERT INTO "incoming" VALUES('GmailId149da0cb4a2805eb','syuanivy@gmail.com','syuanivy@gmail.com','why null pointer for recipients when fetching','new bug?
.
',0,0,'2014-11-23 01:42:04');
INSERT INTO "incoming" VALUES('GmailId149da11217b8a069','syuanivy@gmail.com','syuanivy@gmail.com','why null pointer for recipients when fetching','don''t understand
.
',0,0,'2014-11-23 01:42:04');
INSERT INTO "incoming" VALUES('GmailId149da357246f914d','syuanivy@gmail.com','syuanivy@gmail.com','restart','wired null pointer 
.
',0,0,'2014-11-23 01:42:04');
INSERT INTO "incoming" VALUES('GmailId149da66198e899f4','syuanivy@gmail.com','syuanivy@gmail.com','requestLogHandler added to handers','see if log is generated
.
',0,0,'2014-11-23 03:10:15');
INSERT INTO "incoming" VALUES('GmailId149daa0bd36d8965','syuanivy@gmail.com','syuanivy@gmail.com','test handlers','add handlers array to handlers by handlers.setHandlers
and server.setHandler(handlers)
.
',0,0,'2014-11-23 03:10:16');
INSERT INTO "incoming" VALUES('GmailId149d55d1278407c8','syuanivy@gmail.com','syuanivy@gmail.com','Open your eyes','retest
.
',0,0,'2014-11-23 20:29:05');
INSERT INTO "incoming" VALUES('GmailId149d56483cfe12a8','syuanivy@gmail.com','syuanivy@gmail.com','Open your eyes','retest
.
',0,0,'2014-11-23 20:29:05');
INSERT INTO "incoming" VALUES('GmailId149d568609334324','syuanivy@gmail.com','syuanivy@gmail.com','Open your eyes','retest
.
',0,0,'2014-11-23 20:29:05');
INSERT INTO "incoming" VALUES('GmailId149d56cb20f89e05','syuanivy@gmail.com','syuanivy@gmail.com','Open your eyes','retest
.
',0,0,'2014-11-23 20:29:06');
INSERT INTO "incoming" VALUES('GmailId149d56f5fb737c22','syuanivy@gmail.com','syuanivy@gmail.com','Open your eyes','retest
.
',0,0,'2014-11-23 20:29:06');
INSERT INTO "incoming" VALUES('GmailId149d613d7b299757','syuanivy@gmail.com','syuanivy@gmail.com','let''s try to save it into the db','and then display them properly
.
',0,0,'2014-11-23 20:29:06');
INSERT INTO "incoming" VALUES('GmailId149d65b569eb3ae8','syuanivy@gmail.com','syuanivy@gmail.com','debug new smtp','what?
.
',0,0,'2014-11-23 20:29:06');
INSERT INTO "incoming" VALUES('GmailId149d65d7d03725c3','syuanivy@gmail.com','k.kpjs4s@gmail.com','halo from the non-threaded smtp',':-)
.
',0,0,'2014-11-23 20:29:07');
INSERT INTO "incoming" VALUES('GmailId149d683640e7ca9a','syuanivy@gmail.com','syuanivy@gmail.com','compose sesssion bug fixed','test outgoing into db
.
',0,0,'2014-11-23 20:29:07');
INSERT INTO "incoming" VALUES('GmailId149d8bb500c9fd3c','syuanivy@gmail.com','syuanivy@gmail.com','insert or ignore into values','avoid fetching same emails over again
.
',0,0,'2014-11-23 20:29:07');
INSERT INTO "incoming" VALUES('GmailId149db8cf29129ad4','syuanivy@gmail.com','syuanivy@gmail.com','sqlite busy','the database is locked, not helpful by closing the resultset 
cannot close db connection either
.
',0,0,'2014-11-23 20:29:08');
INSERT INTO "incoming" VALUES('GmailId149de32ac10f12d2','syuanivy@gmail.com','syuanivy@gmail.com','db','locked?
.
',0,0,'2014-11-23 20:29:08');
INSERT INTO "incoming" VALUES('GmailId149de58bfb1a4cb6','syuanivy@gmail.com','syuanivy@gmail.com','import static db solves locked db problem','how about multiple threads?
.
',0,0,'2014-11-23 20:30:06');
INSERT INTO "incoming" VALUES('GmailId149de804d6446aaf','syuanivy@gmail.com','syuanivy@gmail.com','test account pw encoding and decoding','does it work?
.
',0,0,'2014-11-23 21:13:19');
INSERT INTO "incoming" VALUES('GmailId149deb29b6bea408','syuanivy@gmail.com','syuanivy@gmail.com','test st header','plus javascript cc bcc
.
',0,0,'2014-11-23 22:08:12');
INSERT INTO "incoming" VALUES('GmailId149deb6985873065','Shuai Yuan <shuaiyuan@nyu.edu>','syuanivy@gmail.com','test other sender','<div dir="ltr">heihei<br clear="all"><div><br>-- <br><div class="gmail_signature"><div dir="ltr">***************************************************<div>Shuai Yuan, Ph.D.</div><div><br></div><div>New York University</div><div>Department of Biology</div><div>Center for Genomics and Systems Biology</div><div>12 Waverly Pl, 6th fl</div><div>New York, NY, 10003</div><div><a href="mailto:shuaiyuan@nyu.edu" target="_blank">shuaiyuan@nyu.edu</a></div></div></div>
</div></div>
',0,0,'2014-11-23 22:12:37');
INSERT INTO "incoming" VALUES('GmailId149e0714b741c714','syuanivy@gmail.com','syuanivy@gmail.com','Refakesubject@change.type.text.com','test reply
.
',0,0,'2014-11-24 06:16:11');
INSERT INTO "incoming" VALUES('GmailId149e075a0cc1e7d6','syuanivy@gmail.com','syuanivy@gmail.com','Re:fakesubject','why no autofill of text?
.
',0,0,'2014-11-24 06:20:52');
INSERT INTO "incoming" VALUES('GmailId149e07768a4e3fc0','syuanivy@gmail.com','syuanivy@gmail.com','Re:fakesubject','still no autofill?
.
',0,0,'2014-11-24 06:22:49');
INSERT INTO "incoming" VALUES('GmailId149e309eb2452849','syuanivy@gmail.com','syuanivy@gmail.com','Fwd:db--TEST FORWARD','TEST FORWARD
---------- Forwarded message ----------
From: syuanivy@gmail.com
Date: 2014-11-23 20:29:08
Subject: db
To: syuanivy@gmail.com

locked?
    		.
    		 
.
',0,0,'2014-11-24 18:22:10');
INSERT INTO "incoming" VALUES('GmailId149e4de43bd2ebcf','syuanivy@gmail.com','syuanivy@gmail.com','Fwd:Fwd:db--TEST FORWARD','test draft
---------- Forwarded message ----------
From: syuanivy@gmail.com
Date: 2014-11-24 18:22:10
Subject: Fwd:db--TEST FORWARD
To: syuanivy@gmail.com

TEST FORWARD
    		---------- Forwarded message ----------
    		From: syuanivy@gmail.com
    		Date: 2014-11-23 20:29:08
    		Subject: db
    		To: syuanivy@gmail.com

    		locked?
    		    		.
    		    		 
    		.
    		 
.
',0,0,'2014-11-25 04:53:57');
INSERT INTO "incoming" VALUES('GmailId149e5ee2e1fccded','syuanivy@gmail.com','syuanivy@gmail.com','Re:Fwd:db--TEST FORWARD','
adfa
--On 2014-11-24 18:22:10, syuanivy@gmail.com wrote: 
TEST FORWARD
    		---------- Forwarded message ----------
    		From: syuanivy@gmail.com
    		Date: 2014-11-23 20:29:08
    		Subject: db
    		To: syuanivy@gmail.com

    		locked?
    		    		.
    		    		 
    		.
    		 
.
',0,0,'2014-11-26 07:30:45');
INSERT INTO "incoming" VALUES('GmailId149e5ec4cffa36dd','syuanivy@gmail.com','syuanivy@gmail.com','Re:Fwd:Fwd:db--TEST FORWARD','sf
--On 2014-11-25 04:53:57, syuanivy@gmail.com wrote: 
test draft
    		---------- Forwarded message ----------
    		From: syuanivy@gmail.com
    		Date: 2014-11-24 18:22:10
    		Subject: Fwd:db--TEST FORWARD
    		To: syuanivy@gmail.com

    		TEST FORWARD
    		    		---------- Forwarded message ----------
    		    		From: syuanivy@gmail.com
    		    		Date: 2014-11-23 20:29:08
    		    		Subject: db
    		    		To: syuanivy@gmail.com

    		    		locked?
    		    		    		.
    		    		    		 
    		    		.
    		    		 
    		.
    		 
.
',0,0,'2014-11-26 07:30:45');
INSERT INTO "incoming" VALUES('GmailId149e89c7ae1d1448','cs601ceshi@gmail.com','syuanivy@gmail.com','RE:I figured the https problem out!','congratuation




---------Reply message----------



                Now it works!!!
                Thanks wangwang!

                S
                .

            
.
',0,0,'2014-11-26 07:30:45');
INSERT INTO "incoming" VALUES('GmailId149eb018321f19a1','syuanivy@gmail.com','syuanivy@gmail.com','reset head ','test
.
',0,0,'2014-11-26 07:30:45');
INSERT INTO "incoming" VALUES('GmailId149eb07ec9c8deb9','Shuai Yuan <ivyandscorpio@gmail.com>','syuanivy@gmail.com','Re: test sender and recipient','<p dir="ltr">reply back</p>
<div class="gmail_quote">On Nov 25, 2014 11:34 PM,  &lt;<a href="mailto:syuanivy@gmail.com">syuanivy@gmail.com</a>&gt; wrote:<br type="attribution"><blockquote class="gmail_quote" style="margin:0 0 0 .8ex;border-left:1px #ccc solid;padding-left:1ex">is sent retrieved as well?<br>
</blockquote></div>
',0,0,'2014-11-26 07:36:54');
INSERT INTO "incoming" VALUES('GmailId149eb27fb24a5a56','syuanivy@gmail.com','syuanivy@gmail.com','Re:new schema add default value back','

--On 2014-11-26 08:07:24, syuanivy@gmail.com wrote: 
why were they gone? wired... 
.
',0,0,'2014-11-26 08:12:04');
INSERT INTO "incoming" VALUES('GmailId149eb326dfcc66e4','syuanivy@gmail.com','syuanivy@gmail.com','Re:new schema add default value back','not sure why, but add the value back
--On 2014-11-26 08:07:24, syuanivy@gmail.com wrote: 
why were they gone? wired... 
.
',0,0,'2014-11-26 08:23:37');
INSERT INTO "incoming" VALUES('GmailId149eb39233741560','Shuai Yuan <shuaiyuan@nyu.edu>','syuanivy@gmail.com','test reply','<div dir="ltr">test</div>
',0,0,'2014-11-26 08:30:41');
CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,username TXT UNIQUE NOT NULL, password TXT NOT NULL);
INSERT INTO "users" VALUES(1,'shuai','shuai');
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
INSERT INTO "users" VALUES(20,'cola','cola');
INSERT INTO "users" VALUES(21,'zaozao','zaozao');
INSERT INTO "users" VALUES(22,'niurou','bml1cm91');
INSERT INTO "users" VALUES(23,'ivy','aXZ5');
INSERT INTO "users" VALUES(24,'wangwangwang','d2FuZ3dhbmd3YW5n');
CREATE TABLE accounts(email_address TEXT PRIMARY KEY,smtp TEXT NOT NULL, smtp_port INTEGER NOT NULL, pop TEXT NOT NULL, pop_port INTEGER NOT NULL, ssl INTEGER DEFAULT 1, username TEXT NOT NULL, password TEXT NOT NULL, my_user TEXT NOT NULL, FOREIGN KEY (my_user) REFERENCES users(username));
INSERT INTO "accounts" VALUES('yuanshuaikey@163.com','smtp.163.com',465,'pop.163.com',995,1,'yuanshuaikey','624426ivy','shuai');
INSERT INTO "accounts" VALUES('mengmeng@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'mengmeng','123','mengmeng');
INSERT INTO "accounts" VALUES('doudou@sina.com','smtp.sina.com',25,'pop.sina.com',110,0,'doudou','doudou','doudou');
INSERT INTO "accounts" VALUES('snail@sina.com','smtp.sina.com',25,'pop.sina.com',110,0,'snail','snail','snail');
INSERT INTO "accounts" VALUES('miaomiao@163.com','smtp.sina.com',25,'pop.sina.com',110,0,'miaomiao','miaomiao','miaomiao');
INSERT INTO "accounts" VALUES('cs601ceshi@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'cs601ceshi','12345678wxt','xitao');
INSERT INTO "accounts" VALUES('cola@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'cola','colacola','cola');
INSERT INTO "accounts" VALUES('zao@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'zaozao','zaozao','zaozao');
INSERT INTO "accounts" VALUES('niurou@163.com','smtp.163.com',25,'pop.sina.com',110,0,'niurou','niurou','niurou');
INSERT INTO "accounts" VALUES('syuanivy@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'syuanivy','NjI0NDI2QGl2eSEhIQ==','ivy');
INSERT INTO "accounts" VALUES('dd@gmail.com','smtp.gmail.com',465,'pop.gmail.com',995,1,'dd','ZGQ=','wangwangwang');
CREATE TABLE outgoing (id INTEGER PRIMARY KEY, sender TXT NOT NULL, recipient TXT, subject TXT, body TXT, attached INT DEFAULT 0, label INT DEFAULT 2, time TXT DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (sender) REFERENCES accounts(email_address));
INSERT INTO "outgoing" VALUES(1,'syuanivy@gmail.com','ivyandscorpio@gmail.com','test cleaned up code','testest',0,1,'2014-11-27 19:59:09');
INSERT INTO "outgoing" VALUES(2,'syuanivy@gmail.com','syuanivy@gmail.com','Re: test cleaned up code','
test
--On 2014-11-26 08:23:37, syuanivy@gmail.com wrote: 
not sure why, but add the value back
    		--On 2014-11-26 08:07:24, syuanivy@gmail.com wrote: 
    		why were they gone? wired... 
    		.
    		 ',0,2,'2014-11-27 20:00:04');
INSERT INTO "outgoing" VALUES(3,'syuanivy@gmail.com','','Fwd:test cleaned up code','
---------- Forwarded message ----------
From: syuanivy@gmail.com
Date: 2014-11-27 19:59:09
Subject: test cleaned up code
To: syuanivy@gmail.com

testest ',0,2,'2014-11-27 20:00:21');
CREATE TABLE label (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,label TXT UNIQUE NOT NULL);
INSERT INTO "label" VALUES(0,'inbox');
INSERT INTO "label" VALUES(1,'sent');
INSERT INTO "label" VALUES(2,'draft');
INSERT INTO "label" VALUES(3,'read');
INSERT INTO "label" VALUES(4,'trash');
INSERT INTO "label" VALUES(5,'deleted');
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('users',24);
INSERT INTO "sqlite_sequence" VALUES('label',6);
COMMIT;
