

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class addPost
 */
@WebServlet("/addPost")
@MultipartConfig
public class addPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		boolean isUser = true;
		int id = -1;
		
		if(session.getAttribute("userid") == null) {
			isUser = false; //not logged in
			if(session.getAttribute("adminid") == null) { //not logged in
				response.sendRedirect("index.html");
			}else { 
				id = (int) session.getAttribute("adminid");
			}
		}else {
			id = (int) session.getAttribute("userid");
		}
		
		
		
		
		Part filePart=request.getPart("file-upload");
		System.out.println(filePart);
		String filePath = filePart.getSubmittedFileName();
		Path p = Paths.get(filePath);
		String fileName = p.getFileName().toString();
		int  filelen=(int) filePart.getSize();
		InputStream fileContent = filePart.getInputStream();
		
		System.out.println("Add Post: Image File Name " + fileName.substring(fileName.length()-3));
		System.out.println("Add Post: Image File Path " + p);
		System.out.println("Add Post: Image File Size " + filelen);
		
		String title = request.getParameter("post-title");
		String body = request.getParameter("post-body");
//		String image = request.getParameter("imagefile");
		String author_name = "Test-Author";
		
		
//		File file = new File(request.getParameter("articlebody"));
//		String requestbody = request.getReader().lines()
//			    .reduce("", (accumulator, actual) -> accumulator + actual);
		
//		System.out.println(requestbody);
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		
//		Map<String, Object> jsonMap = mapper.readValue(request.getInputStream(),
//			    new TypeReference<Map<String,Object>>(){});
////		
//		System.out.println( jsonMap.toString() + " Json");
		
//		List<Posts> myObjects = mapper.readValue(request.getInputStream() , new TypeReference<List<Posts>>(){});
		
//		System.out.println( myObjects.toString() + " Json");
		System.out.println("Add Post: Title " + title);
		System.out.println("Add Post: body " + body);
		
		
		 // Image body title author_name
		List<Object> bytea = new ArrayList<>();
		bytea.add(fileContent);
		bytea.add(filelen);
		
		String json =  DbHelper.executeQueryJson(Query.addPost_query, 
				new DbHelper.ParamType[] {DbHelper.ParamType.BYTEA,DbHelper.ParamType.STRING, DbHelper.ParamType.STRING,DbHelper.ParamType.STRING,DbHelper.ParamType.STRING}, 
				new Object[] {bytea,fileName.substring(fileName.length()-3).toLowerCase(),body,title,author_name});
		
		Map<String, Object> jsonRes = mapper.readValue(json,
			    new TypeReference<Map<String,Object>>(){});
		
		
		response.setContentType("application/json;charset=UTF-8");
		
		System.out.println("Add Post: List " + ((List) jsonRes.get("data")).get(0));
		
		 int post_id = (int) ((LinkedHashMap) (((List) jsonRes.get("data")).get(0)) ).get("post_id");
		 boolean status = (boolean) jsonRes.get("status");
		 
		 if(!status) {
				response.getWriter().print(json); 
		 }else {
			 System.out.println("Add Post: Post ID " + post_id);
			 
			 if(isUser) {
				 json =  DbHelper.executeUpdateJson(Query.addUserPost_query, 
							new DbHelper.ParamType[] {DbHelper.ParamType.INT,DbHelper.ParamType.INT}, 
							new Object[] {id,post_id});
			 }else {
				 json =  DbHelper.executeUpdateJson(Query.addAdminPost_query, 
						 	new DbHelper.ParamType[] {DbHelper.ParamType.INT,DbHelper.ParamType.INT}, 
							new Object[] {id,post_id});
			 }
			 
			 response.getWriter().print(json);
			 response.setContentType("application/json;charset=UTF-8");
		 }
	}

}

