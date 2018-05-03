package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DatabaseServer 
{
	private String db_name;
	private Connection conn;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public DatabaseServer(String database_name)
	{
		try {
			//Register
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	    } catch (Exception e) { System.out.println("Database registration failed");
	    }
		
		try {
			//Login MySQL with preset username and password
			db_name = database_name;
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name + "?&useSSL=false","root","623062");
		    System.out.println("Connected successfully");
		} catch (SQLException e) {
		    // handle any errors
			System.out.println("Database connection failed");
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	
	//Initiate the tables required for the database
	public void initiate()
	{
		try
		{
			//Prepare query
			st = conn.createStatement();
		
			//Users table
			String sql = "CREATE TABLE USERS(ID char(20), First_Name char(20), Surname char(20), Type char(20), Eva_Type char(20));";
			st.executeUpdate(sql);
			
			System.out.println("Database initiated");
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Clear the database
	public void clear_tables()
	{
		try
		{
			//Prepare query
			st = conn.createStatement();
			String sql = "SELECT table_name FROM information_schema.tables where table_schema='" + db_name + "';";
			
			rs = st.executeQuery(sql);
			
			//Build a list of the names of all tables
			List<String> tables = new ArrayList<String>();
			
			while (rs.next())
			{
				tables.add(rs.getString(1));
			}
			
			Iterator<String> iter = tables.iterator();
			while (iter.hasNext())
			{
				sql = "DROP TABLE " + iter.next() + ";";
				st.executeUpdate(sql);
			}
			
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
