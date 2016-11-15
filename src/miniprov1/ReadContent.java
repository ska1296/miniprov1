package miniprov1;

import java.awt.*;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReadContent extends JFrame {

	ServerSocket serverSocket;
	Socket sock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadContent frame = new ReadContent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReadContent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		List stuffList = new List();
		getContentPane().add(stuffList, BorderLayout.CENTER);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Home frame = new Home();
				frame.setVisible(true);
				frame.setBounds(350, 180, 700, 423);
			}
		});
		getContentPane().add(btnHome, BorderLayout.SOUTH);
		try
        {
			int count=0;
            sock=new Socket("localhost",6005); //Creates a stream socket and connects it to the specified port number on the named host
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());//reads from the specified InputStream
            System.out.println("reading obj");
            while(true)
            {
                String str=ois.readObject().toString(); //object's stuff to string
                System.out.println(str);
                stuffList.add(str); //add server's messages to client's window, most probably
                if(count%2!=0)//just marking what part to mount where and decide format :D
                {
                	System.out.println("-----------------------------");
                    stuffList.add("==================================="); 
                }
                count++;
            }
        }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}
}
