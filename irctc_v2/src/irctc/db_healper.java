package irctc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class db_healper 
{
	String SQL;
	Statement st;
	ResultSet rs;
	Connection conn;
	
	public void db_connect()
	{
		final String url="jdbc:mysql://localhost:3306/irctc";
		final String uname="root";
		final String pass="";
		
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, uname, pass);
			st = conn.createStatement();
		}
		catch(final ClassNotFoundException e)
		{
			System.out.println("Connection Error");
		}
		catch (final SQLException s) 
		{
			System.out.println("Connection Error");
		}
	}
	
	public void db_retrive(final String SQL) throws SQLException
	{
		rs=st.executeQuery(SQL);
	}
	
	public int update(final String SQL) throws SQLException 
	{
		int res;
		res=st.executeUpdate(SQL);
		return res;
	}
	
	public int db_insert(String SQL) throws SQLException
	{
		int res;
		res=st.executeUpdate(SQL);
		return res;
	}
}
