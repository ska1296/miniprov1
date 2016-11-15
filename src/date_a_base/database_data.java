package date_a_base;

import java.io.*;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class database_data 
{
	public static void main(String ars[])throws IOException
	{
		try
		{

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
					String strSubject = rs.getString("Subject");
					System.out.println("In Sender "+strSubject);
					System.out.println(strSubject);//send data to 'ReadContent'
					String strContent = rs.getString("Content");
					//System.out.println("In Sender "+strContent);
					//System.out.println(strContent);
					System.out.println("start  "+strStart_Date);
					System.out.println("end  "+strEnd_Date);
				}
			}
			st.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}