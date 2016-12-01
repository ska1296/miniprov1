package miniprov1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddUser extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JLabel lblPassword;
	private JButton btnNewButton;
	Socket sock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
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
	public AddUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton = new JButton("ADD!!");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(189, 306, 249, 23);
		contentPane.add(btnNewButton);

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(102, 204, 153));
		lblPassword.setFont(new Font("Algerian", Font.BOLD, 20));
		lblPassword.setBounds(189, 207, 249, 14);
		contentPane.add(lblPassword);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Algerian", Font.BOLD, 15));
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(189, 100, 249, 14);
		contentPane.add(lblUsername);

		pass = new JPasswordField();
		pass.setBounds(189, 232, 249, 20);
		contentPane.add(pass);

		user = new JTextField();
		user.setBounds(189, 125, 249, 20);
		contentPane.add(user);
		user.setColumns(10);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 623, 391);
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\manage client.jpg"));
		contentPane.add(label);
		
		try
		{
			sock=new Socket("localhost",7001);
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
			String strPass=pass.getText();
			
			oosUser.writeObject(strUser);//sends Content's data to sever read above
			oosPass.writeObject(strPass);//sends data to sever read above
			//Home h=new Home();
			//h.setVisible(true);
			
			JOptionPane.showMessageDialog(null,"ADDED!!");
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setVisible(false);
	}
	
}
