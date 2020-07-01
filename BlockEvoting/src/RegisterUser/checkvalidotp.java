package RegisterUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mesage91.SendSms;
import Database.DatabaseConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class checkvalidotp
 */
@WebServlet("/checkvalidotp")
public class checkvalidotp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 DatabaseConnection dbcon;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkvalidotp() {
        super();
        dbcon=new DatabaseConnection();
        dbcon.dbconnection();
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
		String userName=request.getParameter("name");
		String MobileNumber=request.getParameter("otp");
		
		PrintWriter out=response.getWriter();
		 ArrayList<Book> books=new ArrayList<Book>();
		 
		  books=getAllCountries(userName,MobileNumber);
		  
		  
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	}
	public  ArrayList<Book> getAllCountries(String userName, String MobileNumber) {
	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	        String sql="select * from userentry where username='"+userName+"' and otp='"+MobileNumber+"'";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=dbcon.getResultSet(sql);
	            if(rs.next()) { 
	            	
	            	String mobilenum=rs.getString("mobile");
	            	Book book=new Book();
	            book.setBookName("CORRECT");
	           
	                booklist.add(book);
	              
	            }
	            else
	            {
	              	Book book=new Book();
		            book.setBookName("INCORRECT");
		           
		                booklist.add(book);
	            	
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;
	}
}


