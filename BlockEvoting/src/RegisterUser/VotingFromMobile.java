package RegisterUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import Database.DatabaseConnection;

/**
 * Servlet implementation class VotingFromMobile
 */
public class VotingFromMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int i=0;
	DatabaseConnection dbcon;
	String inputFordecypt= "",inputFordecypt2="";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotingFromMobile() {
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
		 String path = "."; 
		 
		  
		 
		  
		HttpSession session=request.getSession(true);
		String q2 = request.getParameter("select");
		String input =getServletContext().getRealPath("/images/"+q2);
		//response.setContentType("images/jpeg");  
	    ServletOutputStream out;  
	    Random num=new Random();
	    //num.nextInt();
	    out = response.getOutputStream();  
	    //FileInputStream fin = new FileInputStream("c:\\test\\"+q2);  
	    String filenamePath=q2;
	    String convertTopath=getServletContext().getRealPath("/images1/"+q2);//input.replace(".jpg", num.nextInt()+".jpg");
	   //input.replace(".jpg",num.nextInt()+".jpg");
	   // AESCrypt.acceptingVote("e", input,convertTopath);
 String uid=request.getParameter("uid");
	    
		int msg=isdoneVoting(uid);
		ArrayList<Book> books=new ArrayList<Book>();
		
		  ArrayList<Book> booklist = new ArrayList<Book>();
		  
			Book book=new Book();
      	if(msg==1)
      	{
      book.setMsg(msg);
      	}
      	else
      	{
      		  book.setMsg(msg);	
      	}
          booklist.add(book);
     
 


		  
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(booklist, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
		
		
	
	}
	public int isdoneVoting(String uid)
    {
		 int val=0;
        boolean flag=false;
        try 
        {
            String query="update  userentry set islogin=? where uid="+uid;
            
            PreparedStatement ps=dbcon.dbconnection().prepareStatement(query);
            ps.setString(1, "1");     
         val=ps.executeUpdate();
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }
}
