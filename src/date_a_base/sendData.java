package date_a_base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class sendData 
{
	public static void main(String args[])throws IOException
	{
		int tep;
		String str3="heading", content="content";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//loads driver
			String url="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
			Connection cn=DriverManager.getConnection(url); //connection established
			String q="insert into noticebase (Subject, Content) "+"values (? ,?)";
			PreparedStatement st=cn.prepareStatement(q);
			st.setString (1, str3);
			st.setString (2, content);
			tep=st.executeUpdate();
			cn.close();
			System.out.println("HELLO!");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}