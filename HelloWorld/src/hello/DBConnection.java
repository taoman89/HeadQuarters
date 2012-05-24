package hello;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
	// class variables
	private String url;
	private String db;
	private String driver;
	private String user;
	private String pw;
	
	private ArrayList DBArray;
	
	public DBConnection()
	{
		url = "jdbc:sap://localhost:30015/";
		db = "SYSTEM";
		driver = "com.sap.db.jdbc.Driver";
		user = "SYSTEM";
		pw = "OODdb1234";
		
		java.sql.Connection con;
		
		try
		{
			DBArray = new ArrayList();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url+db, user, pw);
			
			System.out.println("Database Connected.");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"SYSTEM\" .PRODUCT");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int numOfCols = rsmd.getColumnCount();
			
			while(rs.next()) // there is a next row existing
			{
				ArrayList<String> DBrowArray = new ArrayList<String>();
				
				for(int i = 1; i <= numOfCols; i++)
				{
					DBrowArray.add(rs.getString(i));
					System.out.print(rs.getString(i) + " ");
				}
				
				DBArray.add(DBrowArray);
				System.out.println();
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			System.out.println("Database Disconnected.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList getDBArray()
	{
		return DBArray;
	}
	
	/*public static void main(String args[])
	{
		DBConnection dbc = new DBConnection();
	}*/
}
