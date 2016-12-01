package miniprov1;

import java.awt.*; 
import java.awt.event.*; 
import java.net.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.*; 

import javax.swing.JOptionPane;

/**
*
* @author Shubh Ketan
*/

class AddUserReciever
{
	ServerSocket serverSocket;
	Socket sock;
	int tep;
	AddUserReciever() 
	{ 
		try 
		{ 
			serverSocket=new ServerSocket(7001); //Creates a server socket, bound to the specified port
			System.out.println("DONE");
			while(true) 
			{ 
				sock=serverSocket.accept(); //Listens for a connection to be made to this socket and accepts it
				//.accept inside while loop ensures that the connection is maintained while the thing is still open
				ObjectInputStream oisUser=new ObjectInputStream(sock.getInputStream());
				ObjectInputStream oisPass=new ObjectInputStream(sock.getInputStream()); //read's client's message on that socket
				String strUser=oisUser.readObject().toString(); //converts to string
				String strPass=oisPass.readObject().toString();
				
				System.out.println(strUser);
				System.out.println(strPass);

				Class.forName("com.mysql.jdbc.Driver");//loads driver
				String url="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
				Connection cn=DriverManager.getConnection(url); //connection established
				String q="insert into noticefy (email, pass) "+"values (? ,?)";
				PreparedStatement st=cn.prepareStatement(q);
				st.setString (1, strUser);
				st.setString (2, strPass);
				
				System.out.println("LOL");
				tep=st.executeUpdate();
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
		AddUserReciever s=new AddUserReciever(); 
	} 
} 