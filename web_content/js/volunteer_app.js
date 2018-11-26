var topics=[];
var selected_topics=[]
$(document).ready(function () {
     $.getJSON(
         'gettags',
         null,
         function(data,status){
             if(status=='success'){
            	 $.each(data.data, function(index, element) {
            		 topics.push( element.name)
            	 });
            	 loadlist(topics);
            	 console.log(topics)
             }
             else alert("Please check your internet connection.");
         }
     );
    myFunction();
});
function loadlist(data){ 
	$.each(topics, function(index, element) {
			$('#tags_list').prepend(' <label><input type="checkbox" name="tag_check" value='+element+' onchange = addcheck(this.value) /> '+ element+ ' </label>');
			
    });
}
function addcheck(value){
	console.log(selected_topics.indexOf(value))
	if(selected_topics.indexOf(value)==-1)selected_topics.push(value);
	else selected_topics.pop(value);
	console.log(selected_topics)
}	
function myFunction(){
    console.log($("#searchbox").val())
     $('#tags_list').empty()
    $.each(topics, function(index, element) {
    	if(element.match("^"+$("#searchbox").val() )){
    		$('#tags_list').prepend(' <label><input type="checkbox" name="tag_check" value='+element+' onchange = addcheck(this.value) /> '+ element+ ' </label>');
    		if(selected_topics.indexOf(element)!=-1){
    			$('input[value=\''+element+'\']').attr('checked', true);
    		}
    	}
    });
}

function but() {
//    var chk_arr = document.getElementsByName("tag_check");
//    var selected_tags=[]
//    for (k = 0; k < chk_arr.length; k++) {
//
//        if(chk_arr[k].checked)selected_tags.push(chk_arr[k].value);
//    }
    $.post(
        'volunteer_add_application',
        {
            values: selected_topics+'',
            sop: $("#sop").val()
        },
        function(data,status,request){
            if(status=='success'){
            	if(request.getResponseHeader('require_auth')=='yes'){
            		window.location.replace('index.html')
            	}else{
            		
            	}
            }
        }
    );
    console.log(selected_tags);
}