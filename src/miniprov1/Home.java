package miniprov1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

/**
*
* @author Shubh Ketan
*/

public class Home extends JFrame {

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
					frame.setBounds(350, 180, 700, 423);
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
		setBounds(100, 100, 700, 384);
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
			}
		});
		button_1.setBounds(155, 114, 174, 62);
		contentPane.add(button_1);
		
		JButton button = new JButton("Read Content");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ReadContentSender RCS=new ReadContentSender();
				ReadContent RC=new ReadContent();
				RC.setVisible(true);
				//frame.setVisble(false);
			}
		});
		button.setBounds(155, 45, 174, 62);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 700, 384);
		lblNewLabel.setIcon(new ImageIcon("E:\\prog\\eclipse1\\minipro\\src\\unmoderated.jpg"));
		contentPane.add(lblNewLabel);
	}	
}