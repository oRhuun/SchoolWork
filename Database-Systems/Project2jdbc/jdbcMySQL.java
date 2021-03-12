import java.sql.*;
import java.io.*;

class jdbcMySQL
{
	public static void main(String[] args) {
		Connection conn = null;
		try {
		    // db parameters
//		    String url       = "jdbc:mysql://129.244.40.38:3306/user55";
//		    String user      = "user55";
//		    String password  = "TU2020-silver-hippo";
		    // create a connection to the database
//		    conn = DriverManager.getConnection(url, user, password);

		    // Substitute your userId and your password in the following statement
		    conn = DriverManager.getConnection("jdbc:mysql://129.244.40.38:3306/user55?useSSL=false","user55", "TU2020-silver-hippo");
		    // more processing here
		    Statement stmt = conn.createStatement (); // Create a Statement

		    // Display, without duplicates, the Posters of one gender who 
		    //posted to Recipients of a different gender (display Poster and Recipint Ids and Genders).
		    String qry = "SELECT distinct Poster, pUser.Gender AS PosterGender, Recipient, rUser.Gender AS RecipientGender\r\n" + 
		    		"FROM Comments\r\n" + 
		    		"INNER JOIN Users as pUser ON pUser.Id = Comments.Poster\r\n" + 
		    		"INNER JOIN Users as rUser ON rUser.Id = Comments.Recipient\r\n" + 
		    		"WHERE NOT pUser.Gender = rUser.Gender;";
		    
		    // All the records after executing "qry" are fetched a ResultSet rset.
		    ResultSet rset = stmt.executeQuery (qry);

            ResultSetMetaData metadata = rset.getMetaData(); // get metadata from the ResultSet
		    int columnCount = metadata.getColumnCount();   // get the number of columns from metadata
		    for (int i = 1; i <= columnCount; i++) {  // print out the column headers
		        System.out.print(metadata.getColumnName(i) + " ");      
		    }
		    System.out.println();
		    
		    int i = 0;
		    String row = "";
		    while (rset.next ()){
		    		row = "";
		    		for(int j = 1; j <= columnCount; j++) {
		    			row += rset.getString (j) + " ";
		    		}
		    	System.out.println(row + " ");       
		    	i++;
		    }
		    System.out.println("\nNumber of records fetched: " + i +"\n");
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		} finally {
		 try{
//			 System.out.println("Success");
		           if(conn != null)
		             conn.close();
		 }catch(SQLException ex){
		           System.out.println(ex.getMessage());
		 }
		}
		
	}
}