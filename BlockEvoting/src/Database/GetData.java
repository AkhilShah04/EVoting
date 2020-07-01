/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author test
 */
public class GetData 
{
    DatabaseConnection dbcon;
    public GetData() {
         dbcon = new DatabaseConnection();
        dbcon.dbconnection();
    }
    
    public Vector getNames() {
        Vector vnames = new Vector();
        try {
            
            String statement = "SELECT resumeName From data";
            ResultSet rs = dbcon.getResultSet(statement);
            while (rs.next()) {
                vnames.add(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vnames;
    }
    
    
    public Vector getQulaificationVector(String userName)
    {
      Vector qualification=new Vector();
        try 
        {
              String sql="SELECT qualification FROM data WHERE resumeName='"+userName+"'";
              ResultSet rs=dbcon.getResultSet(sql);
              if(rs.next())
              {
                String element=rs.getString(1);
                element=element.replaceAll("[\\[\\]]","");
                String elements[]=element.split(",");
               
                for(String elem:elements)
                {
                 Vector v=new Vector();
                  String individualelem[]=elem.split(":");
                  for(String s:individualelem)
                  {
                   v.add(s);
                  }
                  qualification.add(v);
                       
                }
                
              }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
      return qualification;
    }
 
        
    public Vector getDesignationVector(String userName)
    {
      Vector designation=new Vector();
        try 
        {
              String sql="SELECT Designation FROM data WHERE resumeName='"+userName+"'";
              ResultSet rs=dbcon.getResultSet(sql);
              if(rs.next())
              {
                String element=rs.getString(1);
                element=element.replaceAll("[\\[\\]]","");
                String elements[]=element.split(",");
               
                for(String elem:elements)
                {
                 Vector v=new Vector();
                  String individualelem[]=elem.split(":");
                  for(String s:individualelem)
                  {
                   v.add(s);
                  }
                  designation.add(v);
                       
                }
                
              }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
      return designation;
    }
    
    
    
    public static void main(String[] args)
    {
        GetData getData=new GetData();
        Vector v=getData.getDesignationVector("Hemant S Kochale");
        for(Object elem:v)
        {
         Vector v2=(Vector)elem;
         System.out.println(v2.get(1));
        }
//     System.out.println(new GetData().getQulaificationVector("Hemant S Kochale"));
    }
    
    
}
