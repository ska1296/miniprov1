package miniprov1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.TextArea;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.toedter.calendar.JDateChooser;

/**
*
* @author Shubh Ketan
*/

public class Upload extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField ImageField;
	TextArea content;
	Socket sock;
	JDateChooser startDate;
	JDateChooser endDate;
	private JTextField Subject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upload frame = new Upload();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Upload() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(this);
		
		JButton readContent = new JButton("Jump To Content");
		readContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ReadContentSender RCS=new ReadContentSender();
				ReadContent frame = new ReadContent();
				frame.setVisible(true);
				frame.setBounds(350, 180, 700, 423);
			}
		});
		readContent.setBounds(299, 306, 207, 28);
		contentPane.add(readContent);
		
		Subject = new JTextField();
		Subject.setColumns(10);
		Subject.setBounds(268, 70, 339, 34);
		contentPane.add(Subject);
		
		JLabel SubjectLbl = new JLabel("Subject");
		SubjectLbl.setBounds(64, 72, 107, 34);
		contentPane.add(SubjectLbl);

		endDate = new JDateChooser();
		endDate.setBounds(269, 266, 200, 28);
		contentPane.add(endDate);

		startDate = new JDateChooser();
		startDate.setBounds(268, 216, 200, 28);
		contentPane.add(startDate);
		btnUpload.setBounds(131, 306, 141, 28);
		contentPane.add(btnUpload);

		JLabel lblContent = new JLabel("Content");
		lblContent.setBounds(64, 143, 107, 34);
		contentPane.add(lblContent);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(64, 270, 107, 34);
		contentPane.add(lblEndDate);

		JLabel lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setBounds(64, 216, 107, 34);
		contentPane.add(lblNewLabel);

		content = new TextArea();
		content.setBounds(269, 121, 338, 76);
		contentPane.add(content);

		JLabel label = new JLabel();
		label.setBounds(0, 0, 684, 345);
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\minipro\\src\\unmoderated.jpg"));
		contentPane.add(label);
		
				JButton btnBrowse = new JButton("Browse");
				btnBrowse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle("choosertitle");
						chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						chooser.setAcceptAllFileFilterUsed(false);
						FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES", "png", "jpg", "image");
						chooser.setFileFilter(filter);
						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

							ImageField.setText(chooser.getSelectedFile().toString());
						} else {

							JOptionPane.showMessageDialog(null, "No Selection ");
						}
					}
				});
				btnBrowse.setBounds(510, 22, 97, 36);
				contentPane.add(btnBrowse);
				
						ImageField = new JTextField();
						ImageField.setBounds(268, 22, 232, 34);
						contentPane.add(ImageField);
						ImageField.setColumns(10);
						
								JLabel lblImage = new JLabel("Image");
								lblImage.setBounds(64, 24, 107, 34);
								contentPane.add(lblImage);
		try
		{
			sock=new Socket("localhost",3030);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void actionPerformed(ActionEvent ev) //send contents of text field to the server 
	{
		{ 
			try 
			{ 
				ObjectOutputStream oosContent=new ObjectOutputStream(sock.getOutputStream());//Creates an ObjectOutputStream that writes to the specified OutputStream
				ObjectOutputStream oosImage=new ObjectOutputStream(sock.getOutputStream());
				ObjectOutputStream oosSubject=new ObjectOutputStream(sock.getOutputStream());
				ObjectOutputStream oosEndDate=new ObjectOutputStream(sock.getOutputStream());
				ObjectOutputStream oosStartDate=new ObjectOutputStream(sock.getOutputStream());
				String Content=content.getText(); //use appropriate text field as per requirement
				String ImagePath=ImageField.getText();
				String StartDate = ((JTextField)startDate.getDateEditor().getUiComponent()).getText();
				String EndDate = ((JTextField)endDate.getDateEditor().getUiComponent()).getText();
				String subject=Subject.getText();
				oosImage.writeObject(ImagePath);//sends Content's data to sever read above
				oosContent.writeObject(Content);//sends data to sever read above
				oosSubject.writeObject(subject);
				oosStartDate.writeObject(StartDate);
				oosEndDate.writeObject(EndDate);
				Home h=new Home();
				h.setVisible(true);
			}

			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			this.setVisible(false);
		} 
	}
}