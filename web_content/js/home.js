var start  = "localhost:8080/Infact/";

var r1 = ` <div class = "row Mypost-row">
<div class="col-lg-1"> </div>
<div class="col-lg-10 Mypost-main">
<div class="row Mypost-submain" >
<div class = "row"> `;

var r2 = `
      <div class="col-lg-5 "> 
        <div class = " Mypost-image"> `
            
var r3=  ` </div>
      </div>
      <div class="col-lg-7"> 
        <div class=" Mypost-title">`
         
 var r4 =       ` </div>
        <div class=" Mypost-body">`;
              
var r5 = ` </div> 
			
      `;
var r6 = `</div>
		</div>
      <div class="row User-Option-row">
      	 <button class = "Mybtn Like_Post" type="button" id="Like_Post" > Like </button>
      	 <button class = "Mybtn Save_Post" type="button" id="Save_Post" > Save </button>
		 <button onclick="viewFunction(this)" class = "Mybtn View_Post" type="button" id="View_Post" value="small"> View </button>
		  </div>
  </div> `


var response = null;
const click_color = "#333333";
const unclick_color = "#57b846";


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
		LoadPosts(4);
	}
	
	$('#Logout_button').click(function(){
		Logout();
	});
	
	$('#Volunteer_button').click(function(){
		LoadVolunteer();
	});
	
	$('#Home_button').click(function(){
		$('#UserPosts').show();
		$('#VolunteerPosts').hide();
	});
	
	$('#Upvote_button').click(function(){
		if(response == 1){
			$('#Upvote_button').css('background-color', unclick_color);
			response = null;
			
			
		}else{
			
			if(response == 0){
				$('#Downvote_button').css('background-color', unclick_color);
			}
			response = 1;
			$('#Upvote_button').css('background-color', click_color);
		}
	});
	
	$('#Downvote_button').click(function(){
		if(response == 0){
			$('#Downvote_button').css('background-color' ,unclick_color);
			response = null;
		}else{
			
			if(response == 1){
				$('#Upvote_button').css('background-color', unclick_color);
			}
			response = 0;
			$('#Downvote_button').css('background-color', click_color);
		}
	});
	
//	$('.View_Post').click(function(){
//		console.log("Viewing posts");
//		$(this).parent().clear();
//	});
	
	
	$('#vol-submit').click(function(){
		var comment = $('#post-comment').val();
		if(response == null){
			alert('Please add Response');
		}else if(comment == null || comment.length == 0){
			alert('Please add Comment');
		}
	});
	
});

function LoadHome(){
	
}


function LoadVolunteer(){
	$('#UserPosts').hide();
	$('#VolunteerPosts').show();
}

function Logout(){
	$.post(
	       'Logout',
	        {}
	        , 
	       function(response,status){
	        	if(status== "success" && response.status == true){
	        		window.location.replace("index.html");
	        	}
	        }
	       );
}

function viewFunction(but){
	console.log("F: viewing post");
//	but.parent.clear();
	console.log(but.attributes.value.nodeValue);
	if(but.attributes.value.nodeValue == "small"){
		console.log("Expanding");
		var ParentNode = but.parentNode.parentNode.children[0];
		
		var image = ParentNode.children[0].children[0];
		var post_title = ParentNode.children[1].children[0];
		var post_body = ParentNode.children[1].children[1];
//		ParentNode.hidden = true;
		ParentNode.innerHTML = "";
		ParentNode.appendChild(image);
		ParentNode.appendChild(post_title);
		ParentNode.appendChild(post_body);
		ParentNode.style.display = "block";
		but.attributes.value.nodeValue="large";
		but.innerHTML = "Collapse";
		

//		console.log(ParentNode);
	//	
	}else {
		var ParentNode = but.parentNode.parentNode.children[0];
		 ParentNode.style.display = "flex";
		var image = ParentNode.children[0];
		var post_title = ParentNode.children[1];
		var post_body = ParentNode.children[2];
		console.log(image.innerHTML);
		ParentNode.innerHTML = 
			r2 + image.innerHTML +
			 r3 + post_title.innerHTML + r4 + post_body.innerHTML + r5; 
		console.log(ParentNode.parentNode);
		$('html, body').animate({ scrollTop: ParentNode.parentNode.parentNode.offsetTop - 100}, 'slow');
		
		but.attributes.value.nodeValue="small";
		but.innerHTML = "View";
	}
//	if(ParentNode)
	
//	console.log(image);
//	console.log(post_title);
//	console.log(post_body);
	
}

 
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
        			var r = r1 + r2 +
        			` <img src= "` + `getPostImage?post_id=` + data[i].post_id +`" id="image" alt=" `+ " Image" +  ` "> ` +
        			 r3 + data[i].title + r4 + data[i].body + r5 + r6; 
        			$("#UserPosts").append(r);
        			
        		}
        	}
        	
        }
    );
    
	
}