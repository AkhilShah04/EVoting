package RegisterUser;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import Database.DatabaseConnection;
import Encyption.StrongAES;
import blockchainevoting.creatingaccount;

/**
 * Servlet implementation class registerUser
 */
@WebServlet("/registerUser")
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 DatabaseConnection dbcon;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerUser() {
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
		  doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String userName=request.getParameter("username");
		String MobileNumber=request.getParameter("mobno1");
		String password=request.getParameter("password1");
		String mail=request.getParameter("emailid");
		String day=request.getParameter("day");
		String month=request.getParameter("month");
		String year=request.getParameter("year");
		//String dob="14061990";//request.getParameter("14/06/1990");
		String gender=request.getParameter("branch");
		String location=request.getParameter("location");
		String uid1=request.getParameter("rollnumber");
		
		String Uid=uid1;
//		try {
//			Uid = StrongAES.Encrypting(uid1);
//		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
//				| BadPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	String valForUserCreation=create(userName, password, mail, MobileNumber, gender, day,month,year, location,Uid);
	
	
	
	ArrayList<Book> books=new ArrayList<Book>();
	
	  ArrayList<Book> booklist = new ArrayList<Book>();
	  Book book=new Book();
	  book.setMsg(valForUserCreation);	
	  booklist.add(book);
	 Gson gson = new Gson();
	 
	  JsonElement element = gson.toJsonTree(booklist, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

	  JsonArray jsonArray = element.getAsJsonArray();
	  jsonObject.add("jsonarrayval", jsonArray);
	  response.setContentType("application/json");
	  response.getWriter().print(jsonObject);
	
	
	
	
	
		
		
		
	}

	String create(String username, String password,String mail,String mobile,String gender,
            String day,String month,String year,String location,String uid)
    {
        boolean flag=false;
        
        
        String qry="select * from userEntry where uid='"+uid+"'";
        ResultSet rs=dbcon.getResultSet(qry);
        try {
			if(rs.next())
			{
			return "User with this id already registered";	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try
        {
        	String allaccount= creatingaccount.createblockchainaccount(password);
        	String accountalldet[]=allaccount.split("@SP");
        	String account="0x"+accountalldet[0];
        	String keystorepath=accountalldet[1];
        	
        	//INSERT INTO userEntry VALUES('ningesh','abcd','male', '26' , '', '4' , '', '1987' , 'fsdfsd', 'nmk@gm.com', '8655221446','1234')
           String encpassword=password;
           String query="INSERT INTO userEntry VALUES('"+username+"','"+encpassword+"','"+gender+
                   "', '"+day+"' , '"+month+"' , '"+year+"' , '"+location+"', '"+mail+"', '"+mobile+"','"+uid+"','"+"0"+"',''"+",'"+account+"','"+keystorepath+"')";
           System.out.println(query);
           int rslt=dbcon.getUpdate(query);
           if(rslt==1)
           {
        	   
        	  
        	   
            return "successfully registered";
           }
        }catch(Exception e)
        {
        	return "Got error. user not registered";
        // e.printStackTrace();                 
        }
        return "";
    }	

}

