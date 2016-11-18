package miniprov1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Shubh Ketan
 */

public class Home extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					frame.setBounds(90, 30, 1120, 700);
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

	
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button_1 = new JButton("Upload");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login u=new Login();
				u.setVisible(true);
				u.setBounds(350, 180, 700, 423);
				u.setResizable(false);
			}
		});
		
		JButton button_2 = new JButton("Administrator Login");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin adl=new AdminLogin();
				adl.setVisible(true);
				adl.setBounds(100,100,685, 384);
				adl.setResizable(false);
			}
		});
		
		JButton AnonyUpload = new JButton("ANONYMOUS Upload");
		AnonyUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				AnonymousUpload uup=new AnonymousUpload();
				uup.setBounds(100, 100, 680, 370);
				uup.setResizable(false);
				uup.setVisible(true);
			}
		});
		
		JButton btnReadAnonymousContent = new JButton("Read Anonymous Content");
		btnReadAnonymousContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AnonymousReaderServer ars = new AnonymousReaderServer();
				AnonymousReader rre=new AnonymousReader();
				rre.setBounds(90, 30, 1120, 700);
				rre.setResizable(false);
				rre.setVisible(true);
			}
		});
		btnReadAnonymousContent.setBounds(760, 413, 174, 62);
		contentPane.add(btnReadAnonymousContent);
		AnonyUpload.setBounds(138, 113, 174, 62);
		contentPane.add(AnonyUpload);
		button_2.setBounds(471, 254, 174, 62);
		contentPane.add(button_2);
		button_1.setBounds(471, 113, 174, 62);
		contentPane.add(button_1);

		JButton button = new JButton("Read Content");
		button.addActionListener(this);
		button.setBounds(471, 413, 174, 62);
		contentPane.add(button);

		Random rand = new Random();
		int i=rand.nextInt(4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1120, 700);
		lblNewLabel.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\"+i+".jpg"));
		contentPane.add(lblNewLabel);
	}	

	public void actionPerformed(ActionEvent e) 
	{
		ReadContentSender RCS=new ReadContentSender();
		ReadContent RC=new ReadContent();
		RC.setVisible(true);
		RC.setBounds(90, 30, 1120, 700);
		RC.setResizable(false);
		//frame.setVisble(false);
	}
}