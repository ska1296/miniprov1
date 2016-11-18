package miniprov1;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;

public class AnonymousReader extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnonymousReader frame = new AnonymousReader();
					frame.setVisible(true);
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
	Socket sock;
	int count=0;
	public AnonymousReader() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(90, 30, 1120, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Random rand = new Random();
		int i=rand.nextInt(6);
		JLabel label = new JLabel("");
		
		int R = 50+rand.nextInt(200);
		int G = 120+rand.nextInt(100);
		int B = 120+rand.nextInt(100);
		
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\"+i+".jpg"));
		contentPane.add(label, BorderLayout.CENTER);
		label.setLayout(new GridLayout(0,1));
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 14));
		contentPane.add(btnNewButton, BorderLayout.NORTH);

		try
		{

			sock=new Socket("localhost",6010); //Creates a stream socket and connects it to the specified port number on the named host
			ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());//reads from the specified InputStream
			System.out.println("reading obj");
			while(true)
			{
				String str=ois.readObject().toString(); //object's stuff to string for subject
				String str1=ois.readObject().toString(); //object's stuff to string for content
				System.out.println(str);
				JButton stuffBtn=new JButton(str);
				stuffBtn.setFont(new Font("forte", Font.PLAIN, 30));
				stuffBtn.setForeground(new Color(R,G,B));
				stuffBtn.setOpaque(false);
				stuffBtn.setBackground(Color.ORANGE);
				label.add(stuffBtn);

				stuffBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrame f=new JFrame(str);
						JTextArea stuffList=new JTextArea(str1);
						stuffList.setFont( new Font("Bauhaus 93", Font.PLAIN, 50));
						stuffList.setLineWrap(true);
						stuffList.setWrapStyleWord(true);
						stuffList.setEditable(false);
						stuffList.setOpaque(true);

						int R =50+ rand.nextInt(200);
						int G = 100+rand.nextInt(100);
						int B = 100+rand.nextInt(100);
						
						stuffList.setBackground(new Color(R,G,B));
						//System.out.println("-----------------------------");
						f.getContentPane().add(new JScrollPane(stuffList));
						f.setVisible(true);
						f.setSize(400, 400);
						//stuffBtn.add("+++++++++++++++++++++++++++++"); 

					}
				});
				count++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}
	public void actionPerformed(ActionEvent arg0) {

		this.setVisible(false);
	}
}