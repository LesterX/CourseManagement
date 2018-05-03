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
import systemUsers.*;
import offerings.ICourseOffering;

public class DatabaseServer implements IDatabase
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
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" 
			+ db_name + "?&useSSL=false","root","623062");
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
		
			//User table
			String sql = "CREATE TABLE IF NOT EXISTS USER(ID CHAR(20) NOT NULL PRIMARY KEY, PASSWORD CHAR(40) NOT NULL, "
					+ "FIRST_NAME CHAR(20), SURNAME CHAR(20), TYPE CHAR(20));";
			st.executeUpdate(sql);
			
			//CourseAllowed table
			sql = "CREATE TABLE IF NOT EXISTS COURSE_ALLOWED(STUDENT_ID CHAR(20) NOT NULL,"
					+ "COURSE_ID CHAR(20) NOT NULL);";
			st.executeUpdate(sql);
			
			//CourseEnrolled table
			sql = "CREATE TABLE IF NOT EXISTS COURSE_ENROLLED(STUDENT_ID CHAR(20) NOT NULL, "
					+ "COURSE_ID CHAR(20) NOT NULL);";
			st.executeUpdate(sql);
			
			//EvaluationType table
			sql = "CREATE TABLE IF NOT EXISTS EVALUATION_TYPE(COURSE_ID CHAR(20) NOT NULL, "
					+ "STUDENT_ID CHAR(20) NOT NULL, EVA_TYPE CHAR(4));";
			st.executeUpdate(sql);
			
			//NotificationType table
			sql = "CREATE TABLE IF NOT EXISTS NOTIFICATION_TYPE(STUDENT_ID CHAR(20) NOT NULL PRIMARY KEY, "
					+ "NOTIF_TYPE CHAR(30));";
			st.executeUpdate(sql);
			
			//Mark table
			sql = "CREATE TABLE IF NOT EXISTS Mark(STUDENT_ID CHAR(20) NOT NULL, COURSE_ID CHAR(20) NOT NULL, "
					+ "EVALUATION_TYPE CHAR(30), MARK DOUBLE(4,2));";
			st.executeUpdate(sql);
			
			//Instructor_Course table
			sql = "CREATE TABLE IF NOT EXISTS INSTRUCTOR_COURSE(INSTRUCTOR_ID CHAR(20) NOT NULL, COURSE_ID CHAR(20) NOT NULL);";
			st.executeUpdate(sql);
			
			//Course table
			sql = "CREATE TABLE IF NOT EXISTS COURSE(ID CHAR(20) NOT NULL PRIMARY KEY, NAME CHAR(50), SEMESTER INT(1));";
			st.executeUpdate(sql);
			
			//Course_Weight table
			sql = "CREATE TABLE IF NOT EXISTS COURSE_WEIGHT(COURSE_ID CHAR(20) NOT NULL, EVA_TYPE CHAR(20) NOT NULL, EVA_TITLE CHAR(20),"
					+ "WEIGHT DOUBLE(4,2));";
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
	
	//Insert a new user into database
	public void insert_user(String id, String password, String first_name, String surname, String type)
	{
		try
		{
			//Check if the user id is in database
			String sql = "SELECT * FROM USER WHERE ID = '" + id + "';";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				System.out.println("User ID already exists");
				return;
			}
			
			//Insert into database
			sql = "INSERT INTO USER(ID,PASSWORD,FIRST_NAME,SURNAME,TYPE)"
					+ "VALUES(?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, first_name);
			ps.setString(4, surname);
			ps.setString(5, type);
			ps.executeUpdate();
			
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Delete a user from database
	public void delete_user(String id)
	{
		try
		{
			st = conn.createStatement();
			String sql = "DELETE FROM USER WHERE ID = '" + id + "';";
			st.executeUpdate(sql);
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Authenticate the user's id and password
	public boolean authenticate(String id, String password)
	{
		try
		{
			st = conn.createStatement();
			String sql = "SELECT * FROM USER WHERE ID = '" + id + "' AND PASSWORD = '" + password + "';";
			rs = st.executeQuery(sql);
			
			if(rs.next())
				return true;
			else
				return false;
					
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//Insert a new course into database
	public void insert_course(String id, String name, int semester)
	{
		try
		{
			//Check if the course id is in database
			String sql = "SELECT * FROM COURSE WHERE ID = '" + id + "';";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				System.out.println("Course ID already exists");
				return;
			}
			
			//Insert into database
			sql = "INSERT INTO COURSE(ID,NAME,SEMESTER)"
					+ "VALUES(?,?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, semester);
			ps.executeUpdate();
			
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Add tutors to the course
	public void add_tutor(String instructor_id, String course_id)
	{
		try
		{
			//Check if the course id and instructor id are in database
			String sql = "SELECT * FROM INSTRUCTOR_COURSE WHERE INSTRUCTOR_ID = '" + instructor_id + "' AND COURSE_ID = '"
					+ course_id + "';";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				System.out.println("Tutor already assigned to this course");
				return;
			}
			
			System.out.println("Check");
			
			//Insert into database
			sql = "INSERT INTO INSTRUCTOR_COURSE(INSTRUCTOR_ID,COURSE_ID)"
					+ "VALUES(?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, instructor_id);
			ps.setString(2, course_id);
			ps.executeUpdate();
			
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Add student to allowed list
	public void add_student_allowed(String student_id, String course_id)
	{
		try
		{
			//Check if the student is already in the list
			String sql = "SELECT * FROM COURSE_ALLOWED WHERE STUDENT_ID = '" + student_id + "' AND COURSE_ID = '"
					+ course_id + "';";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				System.out.println("Student already in the allowed list for this course");
				return;
			}
			
			//Insert into database
			sql = "INSERT INTO COURSE_ALLOWED(STUDENT_ID,COURSE_ID)"
					+ "VALUES(?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student_id);
			ps.setString(2, course_id);
			ps.executeUpdate();
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Add student to enrolled list
	public void add_student_enrolled(String student_id, String course_id)
	{
		try
		{
			//Check if the student is already in the list
			String sql = "SELECT * FROM COURSE_ENROLLED WHERE STUDENT_ID = '" + student_id + "' AND COURSE_ID = '"
					+ course_id + "';";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				System.out.println("Student already in the enrolled list for this course");
				return;
			}
			
			//Insert into database
			sql = "INSERT INTO COURSE_ENROLLED(STUDENT_ID,COURSE_ID)"
					+ "VALUES(?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student_id);
			ps.setString(2, course_id);
			ps.executeUpdate();
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Return the System User Model
	public SystemUserModel get_user(String id)
	{
		SystemUserModel user = null;
		try
		{
			
			st = conn.createStatement();
			String sql = "SELECT * FROM USER WHERE ID = '" + id + "';";
			rs = st.executeQuery(sql);
			
			//If user not found, return null
			if (!rs.next())
				return null;
			
			String type = rs.getString(5);
			
			switch (type)
			{
				case "Admin":
				{
					user = new AdminModel();
					user.setID(id);
					user.setName(rs.getString(3));
					user.setSurname(rs.getString(4));
				}
				case "Instructor":
				{
					user = new InstructorModel();
					user.setID(id);
					user.setName(rs.getString(3));
					user.setSurname(rs.getString(4));
					
					List<ICourseOffering> is_tutor_of = new ArrayList<ICourseOffering>();
					sql = "SELECT * FROM ";
				}
			}
			
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return user;
	}
}
