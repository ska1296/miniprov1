package miniprov1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class RemoveUser extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField User;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveUser frame = new RemoveUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Socket sock;

	/**
	 * Create the frame.
	 */
	public RemoveUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		
		JLabel lblUsernameOf = new JLabel("Username OF");
		lblUsernameOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsernameOf.setFont(new Font("Algerian", Font.PLAIN, 16));
		lblUsernameOf.setBounds(159, 95, 173, 14);
		contentPane.add(lblUsernameOf);
		btnDelete.setBounds(194, 181, 89, 23);
		contentPane.add(btnDelete);

		JLabel lblUSERNAME = new JLabel("User To Be Delted");
		lblUSERNAME.setHorizontalAlignment(SwingConstants.CENTER);
		lblUSERNAME.setForeground(new Color(0, 0, 0));
		lblUSERNAME.setFont(new Font("Algerian", Font.PLAIN, 16));
		lblUSERNAME.setBounds(132, 120, 228, 14);
		contentPane.add(lblUSERNAME);

		User = new JTextField();
		User.setBounds(72, 145, 315, 20);
		contentPane.add(User);
		User.setColumns(10);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 434, 261);
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\manage notice.jpg"));
		contentPane.add(label);
		
		try
		{
			sock=new Socket("localhost",7010);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		try 
		{ 
			ObjectOutputStream oosUser=new ObjectOutputStream(sock.getOutputStream());
			
			String user=User.getText();
			oosUser.writeObject(user);
			//Home h=new Home();
			//h.setVisible(true);
			JOptionPane.showMessageDialog(null,"DELETED!");
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
