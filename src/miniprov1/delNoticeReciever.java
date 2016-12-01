package miniprov1;

import java.io.ObjectInputStream;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class delNoticeReciever 
{
	int tep;
	Socket sock;
	ServerSocket serverSock;

	delNoticeReciever()
	{
		try
		{
			serverSock=new ServerSocket(7006);
			System.out.println("DONE");
			while(true) 
			{ 
				sock=serverSock.accept(); //Listens for a connection to be made to this socket and accepts it
				//.accept inside while loop ensures that the connection is maintained while the thing is still open
				
				ObjectInputStream oisSubject=new ObjectInputStream(sock.getInputStream());//read's client's message on that socket
				
				String strSubject=oisSubject.readObject().toString();
				
				System.out.println(strSubject);

				Class.forName("com.mysql.jdbc.Driver");//loads driver
				String url="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
				Connection cn=DriverManager.getConnection(url); //connection established
				
				String q="DELETE FROM notice WHERE Subject = ?";
				PreparedStatement st=cn.prepareStatement(q);
				st.setString (1, strSubject);
				tep=st.executeUpdate();
				
				System.out.println("LOL");
				
				cn.close();
				System.out.println("HELLO!");
				sock.setReuseAddress(true);
			} 
		} 
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void main(String ar[]) 
	{ 
		delNoticeReciever s=new delNoticeReciever(); 
	} 
}