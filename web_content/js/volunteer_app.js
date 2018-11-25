$(document).ready(function () {
    $.getJSON(
        'gettags',
        null,
        function(data,status){
            if(status=='success')loadlist(data);
            else alert("Please check your internet connection.");
        }
    );

});
function loadlist(data){ 
	$.each(data.data, function(index, element) {
			$('#tags_list').append(' <label><input type="checkbox" name="tag_check" value='+element.name+' /> '+ element.name+ ' </label>');
    });
}

function but() {
    var chk_arr = document.getElementsByName("tag_check");
//    var chklength = chk_arr.length;
//    console.log(chk_arr)
    var selected_tags=[]
    for (k = 0; k < chk_arr.length; k++) {
//        console.log(chk_arr[k].checked)
//        console.log(chk_arr[k].value)
        if(chk_arr[k].checked)selected_tags.push(chk_arr[k].value);
    }
    $.post(
        'volunteer_add_application',
        {
            values: selected_tags+'',
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