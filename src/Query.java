
public class Query {
	/** Register,Login related **/
//	public static final String Register_query = "with u_id as "
//			+ "(insert into users values (DEFAULT,?,?,?,?)  returning user_id),"
//			+ "with t_names as "
//			+ "(select topic_name from topics where topic_name = ("
//			+ "insert into user_topics (user_id,topics )values "; // (default,name,password,phone_number,email)
	public static final String Register_query =  "insert into users values (DEFAULT,?,?,?,?)";// (default,name,password,phone_number,email)
	public static final String UserLogin_query = "select password,user_id from users where email = (?)"; 
	public static final String AdminLogin_query = "select password,admin_id from admins where email = (?)";
	public static final String IsVolunteer_query = "select user_id from volunteers where user_id = (?)";
	public static final String isApplication_query = "select user_id from applications where user_id = (?)";
	
	/** Topics Related**/
	public static final String getUserTopics_query = "select topic_name from user_topics where user_id = (?)";
	public static final String getVolunteerTopics_query = "select topic_name from volunteer_topics where user_id = (?)";
	
	public static final String addUserTopic_query = "insert into user_topics values "; // remaining accordingly in servlet (topic,user_id)
	public static final String removeUserTopic_query = "delete from user_topics where user_id = (?) and topic_name = (?)";
	public static final String addVolunteerTopic_query = "insert into volunteer_topics values "; // remaining accordingly in servlet (topic,volunteer_id)
	//public static final String removeVolunteerTopic_query = "delete from volunteer_topics where topic_name = (?) and user_id = (?)";
	
	/** Adding Posts**/
	public static final String getPostID_query = "select * from posts where post_id = (?)"; 
	public static final String addPost_query = "insert into posts values (DEFAULT,now(),?,?,?,?,?) returning post_id"; // Image body title author_name
	public static final String addUserPost_query = "insert into user_posts values (?,?)"; // user_id postid
	public static final String addAdminPost_query = "insert into admin_posts values (?,?)"; // adminid postid
	
	/** Get Posts**/
	public static final String getPosts_query = "select posts.post_id,posts.created_timestamp,posts.image,posts.body,posts.title,posts.author_name from posts,post_topics,published_posts where published_posts.post_id =  posts.post_id and posts.post_id = post_topics.post_id and posts.created_timestamp < (?) and post_topics.topic_name in (select topic_name from user_topics where user_id = (?) ) order by posts.created_timestamp limit (?) "; //Expand 
	public static final String getPostImage_query = "select image from posts where post_id = (?)";
	/** User Specific **/
	public static final String UserInfo_query = "select * from users where user_id = (?)";
	public static final String UserSavedPosts_query = "select post_id from user_post_info where user_id = (?) and saved = TRUE";
	public static final String UserLikedPosts_query = "select post_id from user_post_info where user_id = (?) and liked = TRUE";
	
	public static final String VolunteerInfo_query = "select * from volunteer where user_id = (?)";
	
	/** Volunteer **/
	public static final String ApplyVolunteer_query = "insert into applications values (?,?,?)"; //(user_id,requested_timestamp,SOP
	
	public static final String getVolunteerResponses_query = "select * from responses,volunteers where volunteers.user_id = (?) and volunteers.user_id = responses.user_id";
	public static final String addVolunteerResponse_query = "insert into responses values (?,?,?,?)"; //post_id,user_id,comment,verify
	
	
	/** Admin **/
	public static final String AdminInfo_query = "select * from admins where admin_id = (?)";
	public static final String addAdmin_query = "insert into admins values (?,?)"; // (name, password)
	
	/** Volunteer feed posts (For verification) **/
	// public static final String getVolunteerpostID_query = "select post_id from pending posts where current_volunteer is null and score < (?) limit (?)"; // (thersh,number)
	public static final String getVolunteerpostID_query = "update pending_posts set current_volunteer = (?) where current_volunteer is null and score < (?) limit (?) returning post_id"; // (thersh,number)
	public static final String getVolunteerposts_query = 
			"select posts.post_id,posts.created_timestamp,posts.image,posts.body,posts.title,posts.author_name "
			+ "from posts,pending_posts "
			+ "where pending_posts.post_id = posts.post_id and pending_posts.current_volunteer is null and pending_posts.score < (?) limit (?)"
			+ "and posts.post_id not in (select post_id from responses where response.user_id = (?) and posts.post_id = responses.post_id)";
	public static final String moveVolunteer_query = "update pending_posts set current_volunteer = null where post_id = (?)";
	
	
	
	public static final String addPublishpost_query = "insert into published_posts values (?,?)"; //(post_id and timestamp)
	
//	public static final String getPendingpostID_query = "select post_id from pending_posts where score < (?) limit (?)"; // (thresh) (limit)
//	public static final String getPendingposts_query = "select * from posts,pending_posts wheres pending_posts.post_id = posts.post_id ";
	
	public static final String getApprovePendingpostID_query = "select post_id from pending_posts where score > (?) limit (?)"; // (thresh) (limit)
	public static final String getApprovePendingposts_query = 
			"select posts.post_id,posts.created_timestamp,posts.image,posts.body,posts.title,posts.author_name "
			+ "from pending_posts,posts "
			+ "where pending_posts.post_id = posts.post_id and pending_posts.score > (?) limit (?) order by posts.created_timestamp";
	
	public static final String removePendingpost_query = "delete from pending_posts where post_id = (?)";
	
	public static final String addApprovedposts_query = "with p_id as "
			+ "( delete from pending_posts where post_id = (?) returning a ) "
			+ "insert into published_posts "
			+ "( select post_id , now() from p_id );";
	
	public static final String getResponsesofPost_query = "select * from responses where post_id = (?) limit (?) order by response_timestamp"; 
	public static final String getResponsesofVolunteer_query = "select * from responses where user_id = (?) limit (?) order by response_timestamp";
	
	public static final String getRejectedPosts_query = 
			"select "
			+ "from rejected_posts,posts"
			+ "where rejected_posts.post_id = posts.post_id ";
	
	
	public static final String addRejectpost_query = "insert into rejected_posts values (?)"; //post_id
	
	
	
	
//	public static final String ChangePassword_query = "";
	
	public static final String getApplication = "select * from applications limit (?) order by requested_timestamp";
	public static final String GetPosts_query = "select * from posts where post_id in ";
	
	// Get Tags
	public	static final String getTags= "select * from topics";
	
	// Add volunteers 
	
	public static final String addVolunteer = "insert into applications values (?,?,?)";
	public static final String addVolunteer_topics = "insert into application_topics values (?,?)";
	
	// Autocomplete
	public static final String autocomplete_tags="select * from topics where lower(name) similar to ?";
	
	// Assign post for volunteer
	public static final String check_assigned_post= "select post_id from pending_posts where current_volunteer= ?;" ;
	public static final String assign_post=
			"with helper(post_id) as (" + 
			"	select pending_posts.*" + 
			"	from volunteer_topics,pending_posts,post_topics" + 
			"	where volunteer_topics.user_id=? and pending_posts.post_id=post_topics.post_id and " + 
			"		post_topics.topic_name=volunteer_topics.topic_name and pending_posts.current_volunteer is null" + 
			")" + 
			"update pending_posts " + 
			"set current_volunteer=?, assigned_timestamp=now()" + 
			"where post_id in " + 
			"	(select p.post_id " + 
			"	from ((helper natural join posts) natural left outer join admin_posts)  p " + 
			"	order by admin_id is not null,created_timestamp limit 1)" + 
			"returning post_id";
	public static final String post_id_to_data ="select title,author_name,body,image_metadata,created_timestamp from posts where post_id=?" ; 
}
