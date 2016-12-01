package miniprov1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class AdminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 480);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton delNotice = new JButton("DELETE NOTICE");
		delNotice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delNotice noticeDel=new delNotice();
				noticeDel.setVisible(true);
				noticeDel.setBounds(100, 100, 420, 270);
				noticeDel.setResizable(false);
			}
		});
		
		JButton btnDeleteNoticeanonymous = new JButton("DELETE 'Anonymous'  NOTICE");
		btnDeleteNoticeanonymous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delAnonymNotice dan=new delAnonymNotice();
				dan.setVisible(true);
				dan.setBounds(100, 100, 440, 290);
				dan.setResizable(false);
			}
		});
		btnDeleteNoticeanonymous.setOpaque(true);
		btnDeleteNoticeanonymous.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnDeleteNoticeanonymous.setBackground(new Color(153, 51, 255));
		btnDeleteNoticeanonymous.setBounds(90, 315, 280, 50);
		contentPane.add(btnDeleteNoticeanonymous);
		delNotice.setFont(new Font("Arial Black", Font.BOLD, 13));
		delNotice.setOpaque(true);
		delNotice.setBackground(new Color(153, 51, 255));
		delNotice.setBounds(90, 243, 165, 50);
		contentPane.add(delNotice);
		
		JButton AddUser = new JButton("ADD USER");
		AddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser au = new AddUser();
				au.setVisible(true);
				au.setBounds(100,100,625,400);
				au.setResizable(false);
			}
		});
		AddUser.setForeground(Color.BLACK);
		AddUser.setFont(new Font("Arial Black", Font.BOLD, 13));
		AddUser.setOpaque(true);
		AddUser.setBackground(new Color(0, 153, 255));
		AddUser.setBounds(90, 40, 165, 50);
		contentPane.add(AddUser);
		
		JButton delUser = new JButton("DELETE USER");
		delUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveUser ru=new RemoveUser();
				ru.setVisible(true);
				ru.setBounds(100, 100, 410, 290);
				ru.setResizable(false);
			}
		});
		delUser.setFont(new Font("Arial Black", Font.BOLD, 13));
		delUser.setOpaque(true);
		delUser.setBackground(new Color(102, 153, 255));
		delUser.setBounds(90, 107, 165, 50);
		contentPane.add(delUser);
		
		JButton addNotice = new JButton("ADD NOTICE");
		addNotice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upload u=new Upload();
				u.setVisible(true);
				u.setBounds(100, 100, 680, 364);
				u.setResizable(false);
			}
		});
		addNotice.setFont(new Font("Arial Black", Font.BOLD, 13));
		addNotice.setOpaque(true);
		addNotice.setBackground(new Color(153, 153, 255));
		addNotice.setBounds(90, 174, 165, 50);
		contentPane.add(addNotice);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 784, 461);
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\admin panel.jpg"));
		contentPane.add(label);
		
		
	}
}
