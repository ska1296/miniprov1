package miniprov1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
*
* @author Shubh Ketan
*/

public class AdminLogin extends JFrame implements ActionListener {
	private JTextField user;
	private JPasswordField Password;
	int session;
	Socket sock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setBounds(400,180, 684, 398);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		getContentPane().setLayout(null);
		
				JButton button_1 = new JButton("Jump to content");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						ReadContentSender RCS=new ReadContentSender();
						ReadContent frame = new ReadContent();
						frame.setVisible(true);
						frame.setBounds(90, 30, 1120, 700);
						frame.setResizable(false);
					}
				});
				button_1.setBounds(233, 320, 212, 28);
				getContentPane().add(button_1);

		JLabel label = new JLabel("Username");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(233, 110, 188, 16);
		getContentPane().add(label);

		user = new JTextField();
		user.setColumns(10);
		user.setBounds(233, 138, 212, 28);
		getContentPane().add(user);

		JButton button = new JButton("Login");
		button.addActionListener(this);
		button.setBounds(233, 242, 212, 28);
		getContentPane().add(button);

		Password = new JPasswordField();
		Password.setBounds(233, 202, 212, 28);
		getContentPane().add(Password);

		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(233, 174, 188, 16);
		getContentPane().add(label_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 684, 398);
		lblNewLabel.setIcon(new ImageIcon("E:\\prog\\eclipse1\\minipro\\src\\login.jpg"));
		getContentPane().add(lblNewLabel);
		
		try
		{
			sock=new Socket("localhost",4989);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void actionPerformed(ActionEvent e) {
		
		try 
		{ 
			ObjectOutputStream oosUser=new ObjectOutputStream(sock.getOutputStream());//Creates an ObjectOutputStream that writes to the specified OutputStream
			ObjectOutputStream oosPass=new ObjectOutputStream(sock.getOutputStream());
			String strUser=user.getText(); //use appropriate text field as per requirement
			String strPass=Password.getText();
			
			oosUser.writeObject(strUser);//sends Content's data to sever read above
			oosPass.writeObject(strPass);//sends data to sever read above
			//Home h=new Home();
			this.setVisible(true);
			
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}