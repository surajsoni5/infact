var start  = "localhost:8080/Infact/";

$(document).ready(function () {
	
	// If user_topics doesn't exists;
	if(Cookies.get('user_topics') == 'undefined'){
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
	        			Cookies.set('user_topics', data);
	        		}
	        	}
	        }
	    });
	}
	
	LoadPosts(2);
});

function LoadPosts(limit){
	
	var userTopics = Cookies.get('user_topics');
	console.log("user_topics");
	var postdata = new FormData();
	postdata.append("limit",limit);
	postdata.append("user_topics",userTopics);
    
    $.post(
        'getPosts',
        {
        	limit: limit,
        	user_topics: userTopics
        	
        }, 
       function(response,status){
        	if(status== "success"){
        		console.log(response); 	
        	}
        	
        }
    );
    
	var r =  `<div class = "row Mypost-row"> 
              <div class="col-sm-1"> </div> 
              <div class="col-sm-9 "> 
                <div class="row Mypost-main" > 
                    <div class="col-sm-5 "> \
                      <div class = "Mypost-image"> 
                          <img src="./images/login/login_back.jpg" alt="Italian Trulli">
                      </div> 
                    </div> 
                    <div class="col-sm-7"> 
                      <div class="Mypost-title">
                        We made it Check the first post ever created !!
                      </div>
                      <div class="Mypost-body">
                          Material is an adaptable system of guidelines, components,
                           and tools that support the best practices of user interface design. 
                           Backed by open-source code, Material streamlines collaboration between 
                           designers and developers, and helps teams quickly build beautiful products.
                      </div> 
                    </div>
                </div> `;
}