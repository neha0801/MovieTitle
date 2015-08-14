import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Simple Java Program to connect Oracle database by using Oracle JDBC thin driver
 * Make sure you have Oracle JDBC thin driver in your classpath before running this program
 * @author
 */
public class MovieDB {
	

     //properties for creating connection to Oracle database
	public static Connection conn = null;
	 
    public static void openConnection(){
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
    	 Properties props = new Properties();
    	 props.setProperty("user", "testdb");
         props.setProperty("password", "password");
         try {
			conn = DriverManager.getConnection(url,props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void updateDB(String sql)throws SQLException {
	        //URL of Oracle database server
	       
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	    
	        preStatement.setQueryTimeout(10);
	        preStatement.executeUpdate(); 
	       
	      
	    }
	
    public ResultSet getFromDB(String sql)throws SQLException {
       
        PreparedStatement preStatement = conn.prepareStatement(sql);
        ResultSet result = preStatement.executeQuery(); 
        return result;
      
    }
    
    public String generateMovieName(){ 
    	ResultSet result;
    	String movieName ="", adj="",noun="";
		try {
			String sql = "SELECT * FROM (SELECT * FROM MovieTitleGenerator ORDER BY DBMS_RANDOM.RANDOM)"
					+ " WHERE rownum =1";
			
			result = getFromDB(sql);
			
			result.next();
			adj = Character.toUpperCase(result.getString("Adjectives").charAt(0)) 
					+ result.getString("Adjectives").substring(1);
			noun = Character.toUpperCase(result.getString("Nouns").charAt(0)) 
					+ result.getString("Nouns").substring(1);
			
			movieName =  adj + " " + noun;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
    	return movieName;
    }
    
    public void storeMovieName(String movieName, String description){
    	
    	try {
			String sql = "insert into MovieTitle values(seq_Movie.nextval, '" +movieName + "', '" + description + "')";
 
			updateDB(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }  
    
    public int getCount(){ 
    	ResultSet result;
    	int count=0;
		try {
			String sql = "SELECT count(*) as count FROM MovieTitleGenerator";
			
			result = getFromDB(sql);
			
			result.next();
			
			count = result.getInt("Count");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
    	return count;
    }
    
    
}