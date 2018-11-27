var ra1 =`
          <div class = "row Mypost-row" data-value="1">
          <div class="col-lg-1"> </div>
          <div class="col-lg-11 Mypost-main"> 
            <div class="row Mypost-submain" >
            <div class = "row stat-row"> 
            	<img class = "stat-img" alt="responses-icon" src="./images/response.png">
            	<div class = "stat-total">
                        `;
                        
var ra2 =                 `
            	</div>
            	<img class = "stat-img" alt="correct-icon" src="./images/correct.png">
            	<div class = "stat-upvote">
                        `;
            
var ra3 =            `
            	</div>
            	<img class = "stat-img" alt="correct-icon" src="./images/incorrect.png">
            	<div class = "stat-downvote">
                  `;
var ra4 =            `
            	</div>
            </div>
              <div class = "row ">`;
var ra5 =               
              `
                <div class="col-lg-5 "> 
                  <div class = "Mypost-image"> `;
                  
var ra6 =      `
                  </div>
                </div>
                <div class="col-lg-7"> 
                  <div class="Mypost-title"> `;
                  
var ra7 =      `
                  </div>
                  <div class="Mypost-body">`;
                  
var  ra8 =     `
                  </div>  `;

var ra9  =  `
                </div>
              </div>
                  <div class="row User-Option-row" >
                        <button onclick="approve_post()" class = "Mybtn Accept_btn" type="button"  > Approve </button>
                        <button onclick="reject_post()" class = "Mybtn Reject_btn" type="button" > Reject </button>  
                  		<button onclick="view_responses_post()" class = "Mybtn View_responses_btn" type="button" > View Responses </button>
                  		<button onclick="view_post()" class = "Mybtn View_btn" type="button" > View Post</button>
                  </div>
                </div>
              </div>
          </div>
`











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
      <div class="col-lg-7 "> 
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

	
$(document).ready(function () { 
      
//	LoadVerificationPosts(3);
	$('#Home_button').click(function () {
		$('#Applications').hide();
		$('#VerificationPosts').show();
		LoadVerificationPosts(limit);
	});
	
	$('#Applications-btn').click(function(){
		$('#Applications').show();
		$('#VerificationPosts').hide();
//		TODO: Hardcoded limit
		LoadApplications(5);
	});
	
	$('#Volunteers-btn').click(function(){
			
		});
	$('#Logout-btn').click(function(){
			Logout();
	});
});

function LoadVolunteers(limit){
	
}
    
function LoadApplications(limit){
	
}


function Logout() {
	$.post(
		'Logout',
		{}
		,
		function (response, status) {
			if (status == "success" && response.status == true) {
				window.location.replace("admin_login.html");
			}
		}
	);
}

function LoadVerificationPosts(limit){
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
		function (response, status) {
			if (status == "success" && response.status == true) {
				console.log(response);
				var data = response.data;
				var len = data.length;
				for (var i = 0; i < len; i++) {
					var r = ra1 + " 1 " + ra2 + " 2 " + ra3 + " 3 "+ ra4 +
						ra5 +
						` <img src= "` + `getPostImage?post_id=` + data[i].post_id + `" id="image" alt=" ` + " Image" + ` "> ` +
						ra6 + data[i].title + ra7 + data[i].body + ra8 + ra9;
					$("#UserPosts").append(r);
				}
			}

		}
	);
	
	

}