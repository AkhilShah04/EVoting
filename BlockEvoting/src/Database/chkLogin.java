/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author test
 */
public class chkLogin 
{
    DatabaseConnection dbcon;
    public chkLogin()
    {
     dbcon=new DatabaseConnection();
     dbcon.dbconnection();
    }

     public boolean isAuthenticate(String userid,String password)
    {
        boolean flag=false;
        try 
        {
            String query="SELECT password FROM usertable WHERE username=?";
            PreparedStatement ps=dbcon.dbconnection().prepareStatement(query);
            ps.setString(1, userid);     
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
             String decpassword=rs.getString(1);
             if(decpassword.equals(password))
             {
              flag=true;
             }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
   
    
    
     public String getRole(String userid)
    {
        String role="";
        try 
        {
          String query="SELECT Role From usertable WHERE username='"+userid+"'"; 
          ResultSet rs=dbcon.getResultSet(query);
          if(rs.next())
          {
           role=rs.getString(1);
          }
        } catch (Exception e) 
        {
        }
        System.out.println("Role : "+role);
        return role;
    }
    
    
    public static void main(String[] args)
    {
       System.out.println(new chkLogin().isAuthenticate("hemantkochale@gmail.com", "2356"));
   
    //  System.out.println(new chkLogin().isAdmin("hemantkochale@gmail.com"));
    }
    
}
