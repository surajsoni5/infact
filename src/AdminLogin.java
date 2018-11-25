

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminid") != null) { // logged in
			response.getWriter().print(DbHelper.okJson().toString());
		}
		else {
			response.getWriter().print(DbHelper.errorJson("Not logged in"));
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminid = request.getParameter("admin_id");
		String password = request.getParameter("password");
		
		String query = Query.AdminLogin_query;
		List<List<Object>> res = DbHelper.executeQueryList(query, 
				new DbHelper.ParamType[] {DbHelper.ParamType.STRING}, 
				new Object[] {adminid});
		
		String dbPass = res.isEmpty()? null : (String)res.get(0).get(0);
		if(dbPass != null && dbPass.equals(password)) {
			session.setAttribute("adminid", adminid);
			response.getWriter().print(DbHelper.okJson().toString());
		}
		else {
			response.getWriter().print(DbHelper.errorJson("Username/password incorrect").toString());
		}
	}

}
