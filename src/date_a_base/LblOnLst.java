package date_a_base;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;

public class LblOnLst extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LblOnLst frame = new LblOnLst();
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
	Socket sock;
	int count=0;
	public LblOnLst() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 30, 1120, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Random rand = new Random();
		JLabel label = new JLabel("");
		int i=rand.nextInt(4);
		
		int R = rand.nextInt(200);
		int G = rand.nextInt(100);
		int B = rand.nextInt(100);
		
		label.setIcon(new ImageIcon("E:\\prog\\eclipse1\\miniprov1\\src\\miniprov1\\"+i+".jpg"));
		contentPane.add(label, BorderLayout.CENTER);
		label.setLayout(new GridLayout(0,1));

		try
		{

			sock=new Socket("localhost",6005); //Creates a stream socket and connects it to the specified port number on the named host
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
						JTextArea stuffList=new JTextArea(str);
						stuffList.setFont( new Font("Bauhaus 93", Font.PLAIN, 50));
						stuffList.setLineWrap(true);
						stuffList.setWrapStyleWord(true);
						stuffList.setEditable(false);
						stuffList.setOpaque(true);
						
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
}