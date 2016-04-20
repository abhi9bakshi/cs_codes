import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
	void insert(String name,String gender,String username,String pwd)
	{
		try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }	
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Connection con;
        String url="jdbc:mysql://localhost/encrypt";
        String user="root";
        String password="pinefresh";
        
        try
        {
            con=DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e)
        {
            return;	
        }
        
        try 
        {
        	PreparedStatement preparedStatement = con.prepareStatement("Insert into information(name,gender,username,password) values(?, ?, ?, ?);");
    		preparedStatement.setString(1,name);	
    		preparedStatement.setString(2,gender);
    		preparedStatement.setString(3,username);
            preparedStatement.setString(4,pwd);
    		preparedStatement.executeUpdate();
    		con.close();
		}
        catch (Exception e)
        {
			e.printStackTrace();
		}
        
	}
	
	public static String retrieve(String username)
	{
		String original=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con;
		    String url="jdbc:mysql://localhost/encrypt";
		    String user="root";
		    String password="pinefresh";
		    
		    con=DriverManager.getConnection(url, user, password);
		    
		    String Query=null;
            Statement stmt = con.createStatement();
            
            Query="select password from information where username=\""+username+"\"";
            ResultSet rs = stmt.executeQuery(Query);
            while(rs.next())
            {
            	original=rs.getString("password");
            	System.out.println(original);
            }
            
		} 
		catch (Exception e) 
		{
			
		}
		
		return original;
	}
}
