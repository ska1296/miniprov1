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

public class delAnonymNotice extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField Subject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delAnonymNotice frame = new delAnonymNotice();
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
	public delAnonymNotice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 290);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		
		JLabel lblSubjectOf = new JLabel("Subject OF");
		lblSubjectOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectOf.setFont(new Font("Algerian", Font.PLAIN, 16));
		lblSubjectOf.setBounds(159, 95, 173, 14);
		contentPane.add(lblSubjectOf);
		btnDelete.setBounds(194, 181, 89, 23);
		contentPane.add(btnDelete);

		JLabel lblSubjectOfNotice = new JLabel("'Anomonymous' Notice To Be Delted");
		lblSubjectOfNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectOfNotice.setForeground(new Color(0, 0, 0));
		lblSubjectOfNotice.setFont(new Font("Algerian", Font.PLAIN, 16));
		lblSubjectOfNotice.setBounds(72, 120, 315, 14);
		contentPane.add(lblSubjectOfNotice);

		JButton btnReadSubjectFrom = new JButton("Read Subject From List");
		btnReadSubjectFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnonymousReader rc=new AnonymousReader();
				rc.setVisible(true);
			}
		});
		btnReadSubjectFrom.setBounds(159, 59, 173, 23);
		contentPane.add(btnReadSubjectFrom);

		Subject = new JTextField();
		Subject.setBounds(72, 145, 315, 20);
		contentPane.add(Subject);
		Subject.setColumns(10);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 434, 261);
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\manage notice.jpg"));
		contentPane.add(label);
		
		try
		{
			sock=new Socket("localhost",7005);
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
			ObjectOutputStream oosSubject=new ObjectOutputStream(sock.getOutputStream());
			String subject=Subject.getText();
			oosSubject.writeObject(subject);
			//Home h=new Home();
			//h.setVisible(true);
			AnonymousReaderServer RCS=new AnonymousReaderServer();
			
			JOptionPane.showMessageDialog(null,"DELETED!!");
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
