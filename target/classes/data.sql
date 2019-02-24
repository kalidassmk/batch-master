--Scheduler data
-- if you need to test scheduler please change the time column current time hour and minute eg 03:00
-- it will run at 3 am every day,and we can check the log SchedulerServiceImpl - Delay in seconds............ 120
insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
  values('1','userEmailScheduler','User Email Scheduler','daily','03:00','NEW',1,0,0);

-- User Data
insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('raja@yahoo.com',false,4,2);-- # MAIL_TYPE_1

insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('kali@gmail.com',false,0,0);-- # MAIL_TYPE_2
insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('kanna@hotmail.com',false,2,2);-- # MAIL_TYPE_2

insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('dass@live.com',false,2,0);-- # MAIL_TYPE_3
insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('latha@gmail.com',false,2,7);-- # MAIL_TYPE_3
insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('aruna@outlook.com',true,0,0);-- # MAIL_TYPE_3
insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('murukan@live.com',true,0,4);-- # MAIL_TYPE_3

insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('guna@gmail.com',true,4,3);-- # MAIL_TYPE_4

insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('venkat@outlook.com',true,5,4);  -- # MAIL_TYPE_5
insert into user (EMAIL,HAS_CONTRACT,FRIENDS_NUMBER,SENT_INVITATIONS_NUMBER)   values('ramu@live.com',true,8,9); -- # MAIL_TYPE_5




