package miniprov1;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class AdminLoginReader {

	ServerSocket serverSocket;
	Socket sock;
	int tep;
	int session;

	AdminLoginReader()
	{
		String str;
		
		try 
		{ 			
			serverSocket=new ServerSocket(4989); //Creates a server socket, bound to the specified port
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
				if(strUser.trim().length()==0||strPass.length()==0)
				{
					JOptionPane.showMessageDialog(null, "Field Can't be left blank");
				}

				else
				{    
					try
					{  
						Class.forName("com.mysql.jdbc.Driver");//loads driver
						String url11="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
						Connection cn; 
						cn = DriverManager.getConnection(url11); //connection established
						String sql = "select * from dataadmin where email='"+strUser+"' and pass='"+strPass+"'";
						PreparedStatement ps = cn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();

						int count=0;

						while(rs.next())
						{
							session=rs.getInt(1);
							count++;
							if(rs.getString(2).equals("admin@admin"))
							{

							}
							else 
							{
								AdminPanel h=new AdminPanel();
								h.setVisible(true);
								h.setBounds(100, 100, 700, 384);
							}
						}
						if(count==0)
						{
							JOptionPane.showMessageDialog(null,"Invalid Credentials");
						} 
						else 
						{
							//this.setVisible(false);
						}
					}
					catch(Exception e12)
					{
						JOptionPane.showMessageDialog(null, e12.getMessage());
					}
				}
				System.out.println("LOL");
				System.out.println("HELLO!");
				sock.setReuseAddress(true);
			}
		}  
		catch(Exception e) 
		{ 
			System.out.println(e.getMessage()); 
		}
	}
	
	public static void main(String args[])
	{
		AdminLoginReader ALC=new AdminLoginReader();
	}
}
