

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class AddPostResponse
 * Adds response to a post by the volunteer
 */
@WebServlet("/AddPostResponse")
public class AddPostResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddPostResponse() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("userid") == null) {
			// Redirect
		}
		int userid=(int) session.getAttribute("userid");
		int postid= Integer.parseInt(request.getParameter("post_id"));
		String comment= request.getParameter("comment");
		Boolean volresponse = Integer.parseInt(request.getParameter("response"))==1?true: false;
    	int success=-1;
    	try (Connection conn = DriverManager.getConnection(Config.url, Config.user, Config.password))
        {
            conn.setAutoCommit(false);
            try(
            		PreparedStatement stmt1 = conn.prepareStatement(Query.addresponse);
            		PreparedStatement stmt2 = conn.prepareStatement(Query.increasescore);
//            		PreparedStatement stmt3 = conn.prepareStatement(Query.delete_pending_posts);
//            		PreparedStatement stmt4 = conn.prepareStatement(Query.insert_published_posts);
            		) {
                stmt1.setInt(1, postid);
                stmt1.setInt(2,userid);
                stmt1.setString(3, comment);
                stmt1.setBoolean(4, volresponse);
                success=stmt1.executeUpdate();
                System.out.println(success);
                stmt2.setInt(1, postid);
                success=stmt2.executeUpdate();
                System.out.println(success);
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
    	if(success!=-1) {
    		response.getWriter().println(DbHelper.okJson());
    	}else {
    		response.getWriter().println(DbHelper.errorJson(null));
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
