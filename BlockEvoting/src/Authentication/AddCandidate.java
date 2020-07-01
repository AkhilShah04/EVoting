package Authentication;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Database.DatabaseConnection;

/**
 * Servlet implementation class AddCandidate
 */
@WebServlet("/AddCandidate")
public class AddCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidate() {
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
		//response.setContentType("text/plain");
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();

		String UPLOAD_DIRECTORY=Path.path;
		
		ArrayList<String> textbox = new ArrayList<>();
		ArrayList<String> textbox1 = new ArrayList<>();
	    try 
	    {
	    	System.out.println("dfgdkfjld");
	    	List<FileItem> multiparts = new ServletFileUpload(
	                                         new DiskFileItemFactory()).parseRequest(request);
	    	for(FileItem item : multiparts){
		        if(!item.isFormField()){
		        	String name = new File(item.getName()).getName();
		            textbox1.add(name);
		            System.out.println(name);
		            item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
		        }
		        else
		        {
		           	String fieldValue = item.getString();
		    		textbox.add(fieldValue);	                    	
		        }
	        }
        } catch (Exception ex) 
        {
        	request.setAttribute("message", "File Upload Failed due to " + ex);
	    }          
	    
	    DatabaseConnection db=new DatabaseConnection();
		db.dbconnection();
			 
		String q = "select * from candidate_details where name='"+textbox.get(0)+"' and partyname ='"+textbox.get(1)+"'";
		ResultSet rs = db.getResultSet(q);
		try 
		{
			if(!rs.next())
			{
				String query1="insert into candidate_details(name,partyname,partyimage,manifesto) values('"+textbox.get(0)+"','"+textbox.get(1)+"','"+textbox1.get(0)+"','"+textbox.get(2)+"')";
	   			int i=0;
	   			i=db.getUpdate(query1);
	   			System.out.println(query1);
	   			if(i==1)
	   				if(!rs.next())
	   				{
	   					out.println("<script type=\"text/javascript\">");
	   		        	out.println("alert('New candidate added successfully')");
	   		        	out.println("location=\"AddCandidate.jsp\"");
	   		        	out.println("</script>");
	   				}
	   				else
	   				{
	   					session.setAttribute("username", rs.getString("name"));
	   			    	out.println("<script type=\"text/javascript\">");
	   		        	out.println("alert('Something went wrong')");
	   		        	out.println("location=\"AddCandidate.jsp\"");
	   		        	out.println("</script>");
	   				}
			}
			else
			{
				//session.setAttribute("username", rs.getString("name"));
		    	out.println("<script type=\"text/javascript\">");
	        	out.println("alert('Already candidate available')");
	        	out.println("location=\"AddCandidate.jsp\"");
	        	out.println("</script>");
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    
	}
}