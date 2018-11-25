var start  = "localhost:8080/Servlets/";
var user_topics = "";

$(document).ready(function () {
	
	$.ajax({
        url:'getUserTopics',
        type:'get',
        success:function(response){
        	var data = response.data;
        	if(response.status == true){
        		if (typeof data == 'undefined' || data.length == 0) {
        		    alert("No User Topics") 
        		    // TODO: Add Model ( pop-up to select topics )
        		}
        	}
        	console.log(ret);

        	
        }
    });
	
	
});