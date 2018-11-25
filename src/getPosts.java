

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 
/**
 * Servlet implementation class getPosts
 */
@WebServlet("/getPosts")
public class getPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
			response.sendRedirect("index.html");
		}else {
			id = (int) session.getAttribute("userid");
		}
		
		System.out.println(" Request: " + request.toString());
		String usertopics = request.getParameter("user_topics");
		String limit = request.getParameter("limit");
		JSONParser parser = new JSONParser();
		try {
			JSONArray usertopicsList = (JSONArray) parser.parse(usertopics);
			String query = Query.getPosts_query + "( ";
			int len = usertopicsList.size();
			for(int i = 0;i < len;i++) {
				if(i == len - 1 ) {
					query += "\'" + usertopicsList.get(i).toString() + "\' ) ";
				}else {
					query += "\'" + usertopicsList.get(i).toString() + "\' , ";
				}
				
			}
			query += " limit " + limit  ;
			
			ResultSet rs = null;
			
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
	        {
	            conn.setAutoCommit(false);
	            try(PreparedStatement stmt = conn.prepareStatement(query)) {
	            	List<List<Object>> res = new ArrayList<>();
	                rs = stmt.executeQuery();
	                ResultSetMetaData rsmd = rs.getMetaData();
	                while(rs.next()) {
	                	
	                	List<Object> row = new ArrayList<>();
	                	for(int i=1;i<=rsmd.getColumnCount();i++) {
	                		row.add(rs.getObject(i));
	                		byte[] content = rs.getBytes("image");
//	                        response.setContentType(getServletContext().getMimeType(imageName));
	                        response.setContentType("image/jpg");
	                		response.setContentLength(content.length);
	                        response.getOutputStream().write(content);
	                	}
	                res.add(row);
	                response.getOutputStream().write(row);
	    			response.setContentType("application/json;charset=UTF-8");
	    			
	                }
	                conn.commit();
	            }
	            catch(Exception ex)
	            {
	                conn.rollback();
	                throw ex;
	            }
	            finally{
	                conn.setAutoCommit(true);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	
			
			
			
//			String json = DbHelper.executeQueryJson(query,
//					new DbHelper.ParamType[] {},
//					new Integer[] {});
//		
			System.out.println(query);
			
			
			
			
		} catch (ParseException e) {
			System.out.println("Error in Parsing" + e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
 
		
	
		 
	}

}
