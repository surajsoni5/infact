select * from application_topics;

select * from user_topics;
select * from volunteers;
select * from applications;
select * from post_topics;
select * from responses;

select * from published_posts
select * from rejected_posts
 select * from pending_posts 

 
select * from responses where post_id = (?) limit (?) order by response_timestamp

select * from volunteers;
select * from posts;
delete from posts;
select * from users;
 with t(id,time) as (insert into posts values (DEFAULT,now(),null,'image/jpg','Title','Body',(select name from users where user_id = 2)) returning post_id,created_timestamp)
 insert into pending_posts 
  select t.id,null,t.time,null,0 from t
  
  t values (t.post_id,null,t.time,null,0)

select applications.*, application_topics.topic_name from applications,application_topics 
where applications.user_id = application_topics.user_id 
 group by applications.user_id, application_topics.topic_name  order by applications.requested_time_stamp limit 5


with p_id(post_id) as ( delete from pending_posts where post_id = 4 returning post_id ) 
 insert into published_posts 
 ( select post_id , now() from p_id );


 where pending_posts.score > 0
 order by pending_posts.added_timestamp; 