

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class getVolunteer
 */
@WebServlet("/getVolunteer")
public class getVolunteer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getVolunteer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("userid") == null) {
			// Redirect
		}
		
		ArrayNode data = mapper.createArrayNode();
		ObjectNode isVolunteer = mapper.createObjectNode();
		ObjectNode isApplication = mapper.createObjectNode();
		
		res =  DbHelper.executeQueryList(Query.IsVolunteer_query, 
				new DbHelper.ParamType[] {DbHelper.ParamType.INT}, 
				new Object[] {userid});
		Long dbPass1 = res.isEmpty()? -1 : (Long) res.get(0).get(0);
		if(dbPass1 != -1 && dbPass1==userid) {
			session.setAttribute("isVolunteer", true);
			session.setAttribute("isApplication", false);
			
			isVolunteer.put("isVolunteer", true);
			isApplication.put("isApplication", true);
			System.out.println("He is a Voluteer");
		}else {
			session.setAttribute("isVolunteer", false);
			isVolunteer.put("isVolunteer", false);
			
			
			res =  DbHelper.executeQueryList(Query.isApplication_query, 
					new DbHelper.ParamType[] {DbHelper.ParamType.INT}, 
					new Object[] {userid});
			dbPass1 = res.isEmpty()? -1 : (Long)res.get(0).get(0);
			if(dbPass1 != -1 && dbPass1==userid) {
				session.setAttribute("isApplication", true);
				isApplication.put("isApplication", true);
				System.out.println("He Applied for Voluteer");
			}else {
				session.setAttribute("isApplication", false);
				isApplication.put("isApplication", false);
				System.out.println("He is just a User");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
