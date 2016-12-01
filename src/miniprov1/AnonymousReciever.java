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

class AnonymousReciever
{
	ServerSocket serverSocket;
	Socket sock;
	int tep;
	AnonymousReciever() 
	{ 
		try 
		{ 
			serverSocket=new ServerSocket(7004); //Creates a server socket, bound to the specified port
			System.out.println("DONE");
			while(true) 
			{ 
				sock=serverSocket.accept(); //Listens for a connection to be made to this socket and accepts it
				//.accept inside while loop ensures that the connection is maintained while the thing is still open
				
				ObjectInputStream oisName=new ObjectInputStream(sock.getInputStream());
				ObjectInputStream oisContent=new ObjectInputStream(sock.getInputStream()); //read's client's message on that socket
				ObjectInputStream oisSubject=new ObjectInputStream(sock.getInputStream());
				ObjectInputStream oisStartDate=new ObjectInputStream(sock.getInputStream());
				ObjectInputStream oisEndDate=new ObjectInputStream(sock.getInputStream());
				String strName=oisName.readObject().toString(); //converts to string
				String strContent=oisContent.readObject().toString();
				String strSubject=oisSubject.readObject().toString();
				String strStartDate=oisStartDate.readObject().toString();
				String strEndDate=oisEndDate.readObject().toString();
				System.out.println(strName);
				System.out.println(strContent);
				System.out.println(strSubject);
				System.out.println(strStartDate);
				System.out.println(strEndDate);

				Class.forName("com.mysql.jdbc.Driver");//loads driver
				String url="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
				Connection cn=DriverManager.getConnection(url); //connection established
				String q="insert into anonymous (Subject, Content, Start_Date, End_Date, Name) "+"values (? ,?, ?, ?, ?)";
				PreparedStatement st=cn.prepareStatement(q);
				st.setString (1, strSubject);
				st.setString (2, strContent);
				st.setString (3, strStartDate);
				st.setString (4, strEndDate);
				st.setString (5, strName);
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
		AnonymousReciever s=new AnonymousReciever(); 
	} 
} 