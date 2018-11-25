create table admins
    (admin_id		SERIAL,
     email          varchar(50),
     name           varchar(50),
	 password		varchar(20),
	 primary key (admin_id)
	);

create table posts
	(
		post_id        SERIAL,
		created_timestamp      timestamp,
        image			bytea,
        body           varchar(3000),
		title          varchar(50),
     	author_name        varchar(50),
     	primary key (post_id)
	);

create table users
	(user_id		SERIAL,
     email          varchar(50) UNIQUE, 
     name           varchar(50),
	 password		varchar(20), 
     phone_number   varchar(10),
	 primary key (user_id)
	);

create table topics
	(name			varchar(50), 
	 primary key (name)
	);

create table volunteers
	(user_id		    BIGINT, 
     admin_id           BIGINT,
     rating             numeric(3,2) check (rating >=0),
     posts_verified     numeric(10,0),
     correctly_verified     numeric(10,0),
	 primary key (user_id),
	 foreign key (admin_id) references admins
		on delete set null,
	 foreign key (user_id) references users
		on delete cascade
	);

create table admin_posts
	(admin_id		BIGINT,
	 post_id		BIGINT,
     primary key (post_id),
     foreign key (admin_id) references admins
        on delete cascade,
     foreign key (post_id) references posts
        on delete cascade
	);

create table published_posts
    (post_id        BIGINT,
     published_timestamp      timestamp,
 	 primary key (post_id),
     foreign key (post_id) references posts
        on delete cascade
    );


create table pending_posts
    (post_id        BIGINT ,
	 assigned_timestamp     timestamp,
	 current_volunteer		BIGINT,
	 score              numeric(3,1),
     primary key (post_id),
     foreign key (post_id) references posts
        on delete cascade,
     foreign key (current_volunteer) references users
       on delete cascade
    );

create table user_posts
	(user_id		BIGINT,
	 post_id		BIGINT,
     primary key (post_id),
     foreign key (user_id) references users
        on delete cascade,
     foreign key (post_id) references posts
        on delete cascade
	);

create table user_post_info
	(user_id		BIGINT,
     post_id		BIGINT,
     liked          boolean,
     saved          boolean,
     primary key (user_id,post_id),
     foreign key (user_id) references users
        on delete cascade,
     foreign key (post_id) references posts
        on delete cascade 
	);

create table post_topics
	(post_id			BIGINT, 
	 topic_name			varchar(50), 
	 primary key (post_id,topic_name),
     foreign key (post_id) references posts
        on delete cascade,
     foreign key (topic_name) references topics
        on delete set null
	);

create table applications
	(	user_id		    BIGINT,
		sop             varchar(500),
        requested_time_stamp     timestamp,
		primary key (user_id),
		foreign key (user_id) references users
		on delete cascade
		);

create table volunteer_topics
    (user_id        BIGINT,
     topic_name         varchar(50),
     primary key (user_id,topic_name),
     foreign key (user_id) references volunteers
		on delete cascade,
     foreign key (topic_name) references topics
        on delete set null
    );
create table application_topics
    (user_id        BIGINT,
     topic_name         varchar(50),
     primary key (user_id,topic_name),
     foreign key (user_id) references applications
		on delete cascade,
     foreign key (topic_name) references topics
        on delete set null
    );

create table responses
    (post_id        BIGINT,
     user_id        BIGINT,
     comment        varchar(1000),
     verify         boolean,
     primary key(user_id,post_id),
     foreign key (post_id) references posts
        on delete cascade,
     foreign key (user_id) references volunteers
        on delete cascade
    );

create table user_topics
    (user_id        BIGINT,
     topic_name         varchar(50),
     primary key (user_id,topic_name),
     foreign key (user_id) references users
		on delete cascade,
     foreign key (topic_name) references topics
        on delete set null
    );

create table rejected_posts
    (post_id        BIGINT,
     user_id        BIGINT,
 	 primary key (post_id),
     foreign key (post_id) references posts
        on delete cascade,
     foreign key (user_id) references users
        on delete cascade
    );



