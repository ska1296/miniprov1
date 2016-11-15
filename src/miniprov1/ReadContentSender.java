package miniprov1;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

/**
*
* @author Shubh Ketan
*/

public class ReadContentSender
{
	public static void main(String args[])throws IOException
	{
		int tep;
		Socket sock;
		ServerSocket ServerSock=new ServerSocket(6005);
		try
		{

			//code to read data from database database and to transfer data to 'ReadContent'
			Class.forName("com.mysql.jdbc.Driver");//loads driver
			String url="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
			Connection cn=DriverManager.getConnection(url); //connection established

			String SelectDateFormat= "dd/MM/YYYY";//specify format
			String strStart_Date = "";//string to store converted date
			String strEnd_Date = "";//string to store converted date
			DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");// old date's format
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");//specify format
			Date dateToday = new Date();//to read today's date
			String today=dateFormat.format(dateToday);//store date in a string

			while(true)
			{
				sock=ServerSock.accept();
				ObjectOutputStream oosMatter=new ObjectOutputStream(sock.getOutputStream());
				String query="select * from noticebase";
				// create the java statement
				java.sql.Statement st = cn.createStatement();

				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query);


				// iterate through the java resultset
				while (rs.next())
				{

					String strStartDate = rs.getString("Start_Date");
					String strEndDate = rs.getString("End_Date");
					Date StartDate = (Date) formatter.parse(strStartDate);//convert date
					Date EndDate = (Date) formatter.parse(strEndDate);//convert date
					SimpleDateFormat newFormat = new SimpleDateFormat(SelectDateFormat);//convert to new format
					strStart_Date= newFormat.format(StartDate);//store date in a string
					strEnd_Date= newFormat.format(EndDate);//store date in a string



					if (((dateToday.compareTo(StartDate) > 0) ||(dateToday.compareTo(StartDate) < 0)) &&  (dateToday.compareTo(EndDate) < 0))
					{
						String strSubject =rs.getString("Subject");
						//System.out.println("In Sender "+strSubject);
						oosMatter.writeObject(strSubject);//send data to 'ReadContent'
						String strContent = rs.getString("Content");
						//System.out.println("In Sender "+strContent);
						oosMatter.writeObject(strContent);
						System.out.println("In Sender SENT");//send data to 'ReadContent'
					}
				}
				oosMatter.close();
				st.close();
			}
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
			//sock.close();
			ServerSock.close();
		}
	}
}
