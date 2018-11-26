import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


@WebServlet("/getVolunteer")
public class getVolunteer extends HttpServlet {
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
=======
>>>>>>> 672ce6d29fbbd97129da157a4583e74623746133
    public getVolunteer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("userid") == null) {
			// Redirect
		}
		int userid=(int) 1;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode out = mapper.createObjectNode();
		
		List<List<Object>>  res = DbHelper.executeQueryList(Query.IsVolunteer_query, 
									new DbHelper.ParamType[] {DbHelper.ParamType.INT}, 
									new Object[] {userid});
		if(!res.isEmpty()) {
			out.put("isVolunteer", true);
			System.out.println("He is a Voluteer");
			try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
	        {
	            conn.setAutoCommit(false);
	            int post_id=-1;
	            try(PreparedStatement stmt1 = conn.prepareStatement(Query.check_assigned_post);
	            	PreparedStatement stmt2 = conn.prepareStatement(Query.assign_post)){
	            	stmt1.setInt(1, userid);
	            	ResultSet rset= stmt1.executeQuery();
	            	if(rset.next()) {
	            		post_id=rset.getInt(1);
	            	}else {
	            		stmt2.setInt(1, userid);
	            		stmt2.setInt(2, userid);
	            		stmt2.execute();
	            		ResultSet rset1 = stmt2.getResultSet();
	            		if(rset1.next()) {
	            			post_id=rset1.getInt(1);
	            		}
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
	            if(post_id!=-1) {
	            	try(PreparedStatement stmt = conn.prepareStatement(Query.post_id_to_data)){
	            		stmt.setInt(1, post_id);
	            		ResultSet rset= stmt.executeQuery();
	            		if(rset.next()) {
// Refer below for reference	            			
//public static final String post_id_to_data ="select title,author_name,body,image_metadata,created_timestamp from posts where post_id=1" ; 
	            			out.put("title", rset.getString(0));
	            			out.put("author_name", rset.getString(1));
	            			out.put("body", rset.getString(2));
	            			out.put("image_metadata", rset.getString(3));
	            			out.put("created_timestamp", (String) rset.getObject(4));
	            		}
	            	}catch(Exception ex) {
	            		out.put("post_available", false);
	            		throw ex;
	            	}finally {
	            		out.put("post_available", true);
	            	}
	            }else {
	            	out.put("post_available", false);
	            }
	            
	        } catch (Exception e) {
	            response.getWriter().flush();
	            response.getWriter().print(DbHelper.errorJson("Network error"));
	            return;
	        }

		}else {
			out.put("isVolunteer", false);
			System.out.println("He is a not Volunteer");
			res =  DbHelper.executeQueryList(Query.isApplication_query, 
					new DbHelper.ParamType[] {DbHelper.ParamType.INT}, 
					new Object[] {userid});
			if(!res.isEmpty()) {
				out.put("Application", true);
				System.out.println("He is a Application");
			}else {
				out.put("Application", false);
				System.out.println("He is a not Application");
			}
		}
		System.out.println(out.toString());
		response.getWriter().write(out.toString());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
