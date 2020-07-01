package RegisterUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
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
import blockchainevoting.PuttingVoteinBlockchain;

/**
 * Servlet implementation class puttingVote
 */
@WebServlet("/puttingVote")
public class puttingVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int i=0;
	DatabaseConnection dbcon;
	String inputFordecypt= "",inputFordecypt2="";    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public puttingVote() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		 String path = "."; 
//		 HashMap<String, String> usenameWithPartyname=new HashMap<>();
//		 usenameWithPartyname.put("bjp.jpg","" );
//		 usenameWithPartyname.put("bsp.jpg","" );
//		 usenameWithPartyname.put("congress.jpg", "");
//		 usenameWithPartyname.put("shivsena.jpg","" );
		 
		// usenameWithPartyname.put(, );
		 
		  
		HttpSession session=request.getSession(true);
		String q2 = request.getParameter("select");
		String input =getServletContext().getRealPath("/images/"+q2);
		
		String partyaccount="";
		 String sql="select * from partyaccount where partyname='"+q2+"'";
	    	
	    	PreparedStatement ps1;
	    	
	    		
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=dbcon.getResultSet(sql);
	            try {
					if(rs.next()) { 
						partyaccount=rs.getString("account");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		
		
		//response.setContentType("images/jpeg");  
	    ServletOutputStream out;  
	    Random num=new Random();
	    //num.nextInt();
	 //   out = response.getOutputStream();  
	    //FileInputStream fin = new FileInputStream("c:\\test\\"+q2);  
	    String filenamePath=q2;
	    String convertTopath=getServletContext().getRealPath("/images1/"+q2);//input.replace(".jpg", num.nextInt()+".jpg");
//	   input.replace(".jpg",num.nextInt()+".jpg");
//	    AESCrypt.acceptingVote("e", input,convertTopath);
String uid=request.getParameter("name");
	    
		String msg=isdoneVoting(uid,partyaccount);
		ArrayList<Book> books=new ArrayList<Book>();
		
		  ArrayList<Book> booklist = new ArrayList<Book>();
		  
			Book book=new Book();
     	
     book.setMsg(msg);
     	
         booklist.add(book);
    
         


		  
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(booklist, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	}
	public String isdoneVoting(String uid, String partyaccount)
    {
		
		String useraccount="";
		
		 String sql="select * from userentry where username='"+uid+"' and islogin=1";
	    	
	    	PreparedStatement ps1;
	    	
	    		
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=dbcon.getResultSet(sql);
	            try {
					if(rs.next()) { 
						
						return "voting already done by this user";
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	        
	            
	            String sql6="select * from userentry where username='"+uid+"'";
		    	
		    	PreparedStatement ps16;
		    	
		    		
		    		// ps1.setString(1, "1" );
		    		    
		    	     ResultSet rs6=dbcon.getResultSet(sql6);
		            try {
						if(rs6.next()) { 
							useraccount=rs6.getString("useraccount");
							
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            
	            
	            
		 int val=0;
        boolean flag=false;
        try 
        {
        	PuttingVoteinBlockchain.puttingvotefromuser(useraccount,partyaccount);
        	
            String query="update  userentry set islogin=? where username='"+uid+"'";
            
            PreparedStatement ps=dbcon.dbconnection().prepareStatement(query);
            ps.setString(1, "1");     
         val=ps.executeUpdate();
            if(val>0){
            	
            	
            	
            	
            	
            	
            	return "voting done successfully";
            	
            	
            	
            	
            	
            }
            if(val==0)
            {
            	return "voting already done by user successfully";	
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            return "voting not done due to insufficient blockchain balance";
        }
        return "voting not done ";
    }
}

