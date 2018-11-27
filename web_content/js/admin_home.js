var r1 =`
          <div class = "row Mypost-row" data-value="1">
          <div class="col-lg-1"> </div>
          <div class="col-lg-11 Mypost-main"> 
            <div class="row Mypost-submain" >
            <div class = "row stat-row"> 
            	<img class = "stat-img" alt="responses-icon" src="./images/response.png">
            	<div class = "stat-total">
                        `;
                        
var r2 =                 `
            	</div>
            	<img class = "stat-img" alt="correct-icon" src="./images/correct.png">
            	<div class = "stat-upvote">
                        `;
            
var r3 =            `
            	</div>
            	<img class = "stat-img" alt="correct-icon" src="./images/incorrect.png">
            	<div class = "stat-downvote">
                  `;
var r4 =            `
            	</div>
            </div>
              <div class = "row ">`;
var r5 =               
              `
                <div class="col-lg-5 "> 
                  <div class = "Mypost-image"> `;
                  
var r6 =      `
                  </div>
                </div>
                <div class="col-lg-7"> 
                  <div class="Mypost-title"> `;
                  
var r7 =      `
                  </div>
                  <div class="Mypost-body">`;
                  
var  r8 =     `
                  </div>  
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
      
      
});
      

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
					var r = r1 + r2 +
						` <img src= "` + `getPostImage?post_id=` + data[i].post_id + `" id="image" alt=" ` + " Image" + ` "> ` +
						r3 + data[i].title + r4 + data[i].body + r5 + r6;
					$("#UserPosts").append(r);
				}
			}

		}
	);

}