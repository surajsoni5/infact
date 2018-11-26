var start  = "localhost:8080/Infact/";

$(document).ready(function () {
	
	$('#postForm').submit(function(e){
		console.log("Submitting the Post")
	
        var formData = new FormData();
        formData.append("post-title",$("#post-title").val());
        formData.append("post-body",$("#post-body").val());
        formData.append("file-upload",$("#file-upload")[0].files[0]);
        formData.append("author_name","Test-Author");
        
		e.preventDefault();
	    $.ajax({
	        url:'addPost',
	        type:'post',
	        data: formData,
//	        enctype:"multipart/form-data",
	        contentType:false,
	        processData: false,
	        success:function(data){
//	        	console.log(data);
	        	window.location.replace('post.html')
//	        	window.location = window.location.protocol + '//' + (start+"posts.html");
	            alert("Added Post");
	        }
	    });
	});
	
//    $("#SendPost").click(function () { 
//        console.log("Sending");
//        var title = $("#post-title").val(); 
//        var articlebody = $("#post-body").val(); 
//
//        var imgfile =$("#file-upload")[0].files[0];
//        console.log("Image File " + imgfile);
//        var formData = new FormData();
//        formData.append("title",title);
//        formData.append("articlebody",articlebody);
//        formData.append("imagefile",imgfile);
//        formData.append("file-upload",$("#file-upload"));
//        formData.append("author_name","Test-Author");
//            
//
//        var url = "addPost";
//        $.ajax({
//            url: url,
//            type:"POST",
//            data:formData,
//            enctype:"multipart/form-data",
//            // contentType:'application/json;charset=UTF-8',
//            processData: false,
//            dataType:"json",
//            success: function(data){
//                console.log(data);
//            }
//          })
//       
//    });

 });
