/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;

/**
 *
 * @author test
 */
public class InsertData 
{
   DatabaseConnection dbcon;   
    public InsertData() 
    {
        dbcon = new DatabaseConnection();
        dbcon.dbconnection();
    }
    
    public void insertData(String name,String qualification,String designation)
    {
        try 
        {
            String statement="Select * FROM data WHERE resumeName='"+name+"'";
            ResultSet rs=dbcon.getResultSet(statement);
            if(rs.next())
            {
              statement="DELETE FROM data WHERE resumeName='"+name+"'";
              dbcon.getUpdate(statement);
            }
              statement="INSERT INTO data VALUES('"+name+"','"+qualification+"','"+designation+"')";              
              dbcon.getUpdate(statement);

            
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args)
    {
      InsertData indata=new InsertData();
      indata.insertData("test", "sdsdfd", "sdfdfdsf"); 
    }
}
