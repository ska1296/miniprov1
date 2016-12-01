package date_a_base;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.*;
import java.util.*;

public class readDate extends JFrame implements ActionListener
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					readDate frame = new readDate();
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
	JTextArea textArea;
	JDateChooser startDate;
	private JButton btnNewButton;
	public readDate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		startDate = new JDateChooser();
		contentPane.add(startDate, BorderLayout.CENTER);

		textArea = new JTextArea();
		startDate.add(textArea, BorderLayout.SOUTH);

		JButton btnCheck = new JButton("check");
		btnCheck.addActionListener(this);
		startDate.add(btnCheck, BorderLayout.NORTH);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		startDate.add(btnNewButton, BorderLayout.WEST);
	}
	public void actionPerformed(ActionEvent e) 
	{
		// Instantiate a Date object
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");//specify format
			Date dateToday = new Date();//to read today's date
			String today=dateFormat.format(dateToday);//store date in a string
			
			String SelectDateFormat= "dd/MM/YYYY";//specify format
			String strSelectDate = "";//string to store converted date
			DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");// old date's format
			String strReadDate = ((JTextField)startDate.getDateEditor().getUiComponent()).getText();//read date from program
			Date readDate = (Date) formatter.parse(strReadDate);//convert date
			SimpleDateFormat newFormat = new SimpleDateFormat(SelectDateFormat);//convert to new format
			strSelectDate= newFormat.format(readDate);//store date in a string
			
			
			textArea.setText(strSelectDate);//put date in text fiels. LOL.
			System.out.println(today);//print date. LOL.
			
			//comparing the dates
			
			if (dateToday.compareTo(readDate) > 0) {
				JOptionPane.showMessageDialog(null, "date be");
            } else if (dateToday.compareTo(readDate) < 0) {
                System.out.println("read date is after today");
            } else if (dateToday.compareTo(readDate) == 0) {
                System.out.println("read date is equal to today");
            } else {
                System.out.println("Something weird happened...");
            }
		}
		catch(Exception ev)
		{
			System.out.println(ev.getMessage());
		}
	}
}