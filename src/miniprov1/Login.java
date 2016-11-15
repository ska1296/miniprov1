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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
	private JTextField user;
	private JPasswordField Password;
	int session;

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
	public Login() {
		getContentPane().setLayout(null);
		
				JButton button_1 = new JButton("Jump to content");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						ReadContentSender RCS=new ReadContentSender();
						ReadContent frame = new ReadContent();
						frame.setVisible(true);
						frame.setBounds(350, 180, 700, 423);
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
	}
	public void actionPerformed(ActionEvent e) {
		String str;   
		if(user.getText().trim().length()==0||Password.getPassword().length==0)
		{
			JOptionPane.showMessageDialog(null, "Field Can't be left blank");
		}
		else
		{
			str = new String(Password.getPassword());
			{    
				try
				{  
					Class.forName("com.mysql.jdbc.Driver");//loads driver
					String url11="jdbc:mysql://localhost/mydb?user=root&password=qwerty";
					Connection cn; 
					cn = DriverManager.getConnection(url11); //connection established
					String sql = "select * from noticefy where email='"+user.getText()+"' and pass='"+str+"'";
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
							Upload h=new Upload();
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
						this.setVisible(false);}
				}
				catch(Exception e12)
				{
					JOptionPane.showMessageDialog(null, e12.getMessage());
				}
			}
		}
	}
}