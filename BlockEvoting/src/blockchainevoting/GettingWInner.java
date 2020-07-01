package blockchainevoting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import Database.DatabaseConnection;

public class GettingWInner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub


		DatabaseConnection dbcon=new DatabaseConnection();
		dbcon.dbconnection();
	
		 HashMap<String, String> usenameWithPartyname=new HashMap<>();
		 usenameWithPartyname.put("bjp.jpg","" );
		 usenameWithPartyname.put("bsp.jpg","" );
		 usenameWithPartyname.put("congress.jpg", "");
		 usenameWithPartyname.put("shivsena.jpg","" );
		 
		 HashMap<String, Integer> winnercount=new HashMap<>();
		
		 for (Map.Entry<String, String> entry : usenameWithPartyname.entrySet()) {
			   
			 String key=entry.getKey();
			 
			
			 
	        	
	        	String queryupdate="select * from partyaccount where partyname='"+key+"'";
	        	ResultSet rs=dbcon.getResultSet(queryupdate);
	        	if(rs.next())
	        	{
	        		String partyaccount=rs.getString("account");
	        		
	        		try {
						String op=gettingBalance.getbalanceofuser(partyaccount);
						
						int balnace=Integer.parseInt(op);
						winnercount.put(key, balnace);
						
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        		
//	        		if(winnercount.containsKey(key)) {
//	        		int account=winnercount.get(key);
//	        		int newbal=
//	        		}
//	        		else
//	        		{
//	        		winnercount.put(key, 1);
//	        		}
	        		
	        	}
	        	
	        	
	        	
			 
			}

		
	displayWinner(winnercount);
		
		
		
	}

	
	public static HashMap<String, Integer> returnResult() 
	{

		DatabaseConnection dbcon=new DatabaseConnection();
		dbcon.dbconnection();
	
		 HashMap<String, String> usenameWithPartyname=new HashMap<>();
		 usenameWithPartyname.put("bjp.jpg","" );
		 usenameWithPartyname.put("bsp.jpg","" );
		 usenameWithPartyname.put("congress.jpg", "");
		 usenameWithPartyname.put("shivsena.jpg","" );
		 
		 HashMap<String, Integer> winnercount=new HashMap<>();
		
		 for (Map.Entry<String, String> entry : usenameWithPartyname.entrySet()) {
			   
			 String key=entry.getKey();
			 
			
			 
	        	
	        	String queryupdate="select * from partyaccount where partyname='"+key+"'";
	        	ResultSet rs=dbcon.getResultSet(queryupdate);
	        	try {
					if(rs.next())
					{
						String partyaccount=rs.getString("account");
						
						try {
							String op=gettingBalance.getbalanceofuser(partyaccount);
							
							int balnace=Integer.parseInt(op);
							winnercount.put(key, balnace);
							
						} catch (InterruptedException | ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
//	        		if(winnercount.containsKey(key)) {
//	        		int account=winnercount.get(key);
//	        		int newbal=
//	        		}
//	        		else
//	        		{
//	        		winnercount.put(key, 1);
//	        		}
						
					}
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        	
			 
			}

		
	return displayWinner(winnercount);
	
	
	}
	
	
	private static HashMap<String, Integer> displayWinner(HashMap<String, Integer> winnercount) {
		
		 HashMap<String, Integer> hm1 = sortByValue(winnercount); 
		for (Map.Entry<String, Integer> en : hm1.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        }
		return hm1; 
		
		
		
	}
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
  

}
