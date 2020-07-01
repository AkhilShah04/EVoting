package Authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;



/**
 * Servlet implementation class AdminLogin
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		String q = "select * from admin where username='"+username+"' and password ='"+password+"'";
		ResultSet rs = db.getResultSet(q);
		try {
			if(!rs.next())
			{
				out.println("<script type=\"text/javascript\">");
	        	out.println("alert('Sorry username or password Invalid')");
	        	out.println("location=\"index.jsp\"");
	        	out.println("</script>");
			}
			else
			{
				session.setAttribute("username", rs.getString("name"));
		    	out.println("<script type=\"text/javascript\">");
	        	out.println("alert('succsefully login')");
	        	out.println("location=\"adminHome.jsp\"");
	        	out.println("</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
