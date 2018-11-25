delete from admins;
delete from admin_posts;
delete from posts;
delete from published_posts;
delete from pending_posts;
delete from user_posts;
delete from users;
delete from user_post_info;
delete from topics;
delete from post_topics;
delete from volunteers;
delete from applications;
delete from volunteer_topics;
delete from responses;
delete from user_topics;
delete from rejected_posts;

insert into admins values (DEFAULT, 'admin0@cse.iitb.ac.in','admin0','admin0@admin');
insert into admins values (DEFAULT, 'admin1@cse.iitb.ac.in','admin1','admin1@admin');
insert into admins values (DEFAULT, 'admin2@cse.iitb.ac.in','admin2','admin2@admin');
insert into admins values (DEFAULT, 'admin3@cse.iitb.ac.in','admin3','admin3@admin');
insert into admins values (DEFAULT, 'admin4@cse.iitb.ac.in','admin4','admin4@admin');
insert into admins values (DEFAULT, 'admin5@cse.iitb.ac.in','admin5','admin5@admin');
insert into admins values (DEFAULT, 'admin6@cse.iitb.ac.in','admin6','admin6@admin');
insert into admins values (DEFAULT, 'admin7@cse.iitb.ac.in','admin7','admin7@admin');
insert into admins values (DEFAULT, 'admin8@cse.iitb.ac.in','admin8','admin8@admin');
insert into admins values (DEFAULT, 'admin9@cse.iitb.ac.in','admin9','admin9@admin');

--insert into posts values (TIMESTAMP '2011-05-16 15:36:38','This is the body of the post 1 in the app. Stay tuned for more updates from Infact.','Post 1','user1');


insert into users values (DEFAULT, 'user0@cse.iitb.ac.in','user0','user0@user','9999999990');
insert into users values (DEFAULT, 'user1@cse.iitb.ac.in','user1','user1@user','9999999991');
insert into users values (DEFAULT, 'user2@cse.iitb.ac.in','user2','user2@user','9999999992');
insert into users values (DEFAULT, 'user3@cse.iitb.ac.in','user3','user3@user','9999999993');
insert into users values (DEFAULT, 'user4@cse.iitb.ac.in','user4','user4@user','9999999994');
insert into users values (DEFAULT, 'user5@cse.iitb.ac.in','user5','user5@user','9999999995');
insert into users values (DEFAULT, 'user6@cse.iitb.ac.in','user6','user6@user','9999999996');
insert into users values (DEFAULT, 'user7@cse.iitb.ac.in','user7','user7@user','9999999997');
insert into users values (DEFAULT, 'user8@cse.iitb.ac.in','user8','user8@user','9999999998');
insert into users values (DEFAULT, 'user9@cse.iitb.ac.in','user9','user9@user','9999999999');

insert into topics values ('Technology');
insert into topics values ('Movies');
insert into topics values ('Sports');
insert into topics values ('Art');
insert into topics values ('Politics');
insert into topics values ('Life Style');
insert into topics values ('Bussiness');
insert into topics values ('Crime');
insert into topics values ('Mythology');
insert into topics values ('International');

insert into volunteers values (2, 2, 0.0, 0, 0);
insert into volunteers values (3, 3, 0.0, 0, 0);
insert into volunteers values (5, 7, 0.0, 0, 0);
insert into volunteers values (7, 5, 0.0, 0, 0);

insert into volunteer_topics values (2,'Technology');
insert into volunteer_topics values (2,'Movies');
insert into volunteer_topics values (2,'Sports');
insert into volunteer_topics values (2,'Art');
insert into volunteer_topics values (2,'Politics');
insert into volunteer_topics values (2,'Mythology');
insert into volunteer_topics values (3,'Technology');
insert into volunteer_topics values (3,'Crime');
insert into volunteer_topics values (3,'International');
insert into volunteer_topics values (3,'Bussiness');
insert into volunteer_topics values (5,'Politics');
insert into volunteer_topics values (5,'Art');
insert into volunteer_topics values (7,'Sports');
insert into volunteer_topics values (7,'Movies');

insert into user_topics values (1,'Technology');
insert into user_topics values (1,'Movies');
insert into user_topics values (1,'Sports');
insert into user_topics values (1,'Art');
insert into user_topics values (1,'Politics');
insert into user_topics values (1,'Life Style');
insert into user_topics values (1,'Bussiness');
insert into user_topics values (1,'Crime');
insert into user_topics values (1,'Mythology');
insert into user_topics values (1,'International');
insert into user_topics values (2,'Politics');
insert into user_topics values (2,'Life Style');
insert into user_topics values (3,'Bussiness');
insert into user_topics values (3,'Crime');
insert into user_topics values (3,'Mythology');
insert into user_topics values (4,'International');
insert into user_topics values (5,'Life Style');
insert into user_topics values (6,'Crime');
insert into user_topics values (7,'Bussiness');
insert into user_topics values (8,'Sports');
insert into user_topics values (9,'Art');

