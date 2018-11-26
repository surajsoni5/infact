var start  = "localhost:8080/Infact/";

var r1 = ` <div class = "row Mypost-row">
<div class="col-sm-1"> </div>
<div class="col-sm-9 "> 
  <div class="row Mypost-main" >
      <div class="col-sm-5 "> 
        <div class = "Mypost-image"> `
            
var r2=  ` </div>
      </div>
      <div class="col-sm-7"> 
        <div class="Mypost-title">`
         
 var r3 =       ` </div>
        <div class="Mypost-body">`;
              
var r4 = ` </div> 
      </div>
  </div> `


$(document).ready(function () {
	
	console.log("User Topics: " + Cookies.get('user_topics'));
	// If user_topics doesn't exists;
//	Cookies.clear('user_topics');
	if(Cookies.get('user_topics') == undefined){
		$.ajax({
	        url:'getUserTopics',
	        type:'get',
	        success:function(response){
	        	var data = response.data;
	        	if(response.status == true){
	        		if (typeof data == 'undefined' || data.length == 0) {
	        		    alert("No User Topics") 
	        		    // TODO: Add Model ( pop-up to select topics )
	        		}else {
	        			var usertopic = data;
	        			for (var i = 0; i < data.length; i++) {
	        				usertopic[i] = data[i].topic_name;
	        			} 
	        			console.log(" New "+ JSON.stringify(usertopic));
	        			Cookies.set('user_topics', JSON.stringify(usertopic));
	        			LoadPosts(2);
	        		}
	        	}
	        }
	    });
	}else {
		LoadPosts(2);
	}
	
	
	
	
	
});
 
function LoadPosts(limit){
	$("#UserPosts").empty();
	
	var userTopics = JSON.parse(Cookies.get('user_topics'));
	
	var postdata = {
    	'limit': limit,
//    	'user_topics': JSON.stringify(userTopics)
    };
	console.log(postdata);
	console.log("Load " + userTopics);
    $.post(
        'getPosts',
        postdata
        , 
       function(response,status){
        	if(status== "success" && response.status == true){
        		console.log(response); 	
        		var data = response.data;
        		var len = data.length;
        		for(var i =0 ;i<len;i++){
        			var r = r1 + 
        			` <img src= "` + `getPostImage?post_id=` + data[i].post_id +`" id="image" alt=" `+ " Image" +  ` "> ` +
        			 r2 + data[i].title + r3 + data[i].body + r4; 
        			$("#UserPosts").append(r);
        		}
        	}
        	
        }
    );
    
	
}