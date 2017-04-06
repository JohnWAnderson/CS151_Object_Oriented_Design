import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * holds the hashmap of the events
 * Created 24 November 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class SimpleCalendar extends JFrame
{
	private EventModel model = new EventModel();
	private  final String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private  final String day[] = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
	private  final String fullDay[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private final static String[] SETUP = { "Continue"};
	private JFrame frame = new JFrame();
	private JFrame menu = new JFrame();
	private JPanel map = new JPanel();
	private JPanel header = new JPanel();
	private JPanel eventpan = new JPanel();
	private JLabel label = new JLabel();
	private int currentM;
	private int currentY;
	private JTextArea events = new JTextArea();
	private JPanel right = new JPanel();
	private JPanel Actions = new JPanel();
	private JButton thedays = new JButton();
	private GregorianCalendar cal = new GregorianCalendar();
	private int viewDay = cal.get(cal.DATE);
	private int size;
	/**
	 * makes the calendar
	 */
	public SimpleCalendar()
	{
		loadList();
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(1000,300));
		frame.add(Actions(), BorderLayout.NORTH);
		frame.add(monthView(), BorderLayout.WEST);
		frame.add(eventView(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Welcome();
	}
	/**
	 * refreshes the calendar
	 */
	public void refresh()
	{	
		header.setVisible(false);
		right.setVisible(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(1000,300));
		frame.add(Actions(), BorderLayout.NORTH);
		frame.add(monthView(), BorderLayout.WEST);
		frame.add(eventView(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/**
	 * makes the JPanel with all of the actions comands 
	 * @return Actions the JPanel with actions
	 */
	public JPanel Actions()
	{
		Actions = new JPanel();
		JButton create = new JButton("CREATE");
		create.addActionListener(new ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {
	        	refresh();
	        	Create();  	
	        }
	     });
		JButton goleft = new JButton("<");
		goleft.addActionListener(new ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {
	        	cal.add(cal.DATE, -1);
	        	viewDay = cal.get(cal.DATE);
	        	refresh();
	        }
	     });
		JButton goright = new JButton(">");
		goright.addActionListener(new ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {
	        	
	        	cal.add(cal.DATE, 1);
	        	viewDay = cal.get(cal.DATE);
	        	refresh();
	        }
	     });
		JButton quit = new JButton("Quit");
		 quit.addActionListener(new ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {
	        	clearFile();
	        	fillfile();
	           	System.exit(0);
	        }
	     });
		Actions.add(create);
		Actions.add(goleft);
		Actions.add(goright);
		Actions.add(quit);
		return Actions;
	}
	/**
	 * makes and returns the month view
	 * @return header the JPabel with the view
	 */
	public JPanel monthView()
	{
		size = 42;
		header = new JPanel();
		map = new JPanel();
		currentM = cal.get(cal.MONTH);
		currentY = cal.get(cal.YEAR);
		cal.set(cal.DATE, 1);
		int firstDay = cal.get(cal.DAY_OF_WEEK);
		int max = cal.getActualMaximum(cal.DAY_OF_MONTH);
		map = new JPanel(new GridLayout(7,8));
		for(int i =0; i < day.length; i++)
		{
			JLabel temp = new JLabel("       "+day[i]);
			map.add(temp);
		}
		for(int i=1; i < firstDay; i++)
		{
			JButton Space = new JButton("");
			map.add(Space);
			size--;
		}
		cal.set(cal.DATE, viewDay);
	//	System.out.println(viewDay);
		//System.out.println(viewDay);
		for(int i =1; i <= max; i++)
		{	
			JButton thedays;
			int temp = i;
			if(temp > 10)
			{
				if(i == viewDay)
				{
					thedays = new JButton("["+i+"]");
				}
			else
				{
					thedays = new JButton("  " +i+"  ");
				}
			}
			else
			{
					if(i == viewDay)
					{
						thedays = new JButton("["+i+"]");
					}
				else
					{
						thedays = new JButton(" " +i+" ");
					}
			}
			 thedays.addActionListener(new ActionListener()
		     {
		        public void actionPerformed(ActionEvent e)
		        {
		             	cal.set(currentY, currentM, temp);
		             	//System.out.println(temp);
			        	viewDay = cal.get(cal.DATE);
			    
			        	refresh();
		        }
		     });
			map.add(thedays);
			size--;
		}
		int j =0;
		while(j != size)
		{
			thedays = new JButton("      ");
			map.add(thedays);
			j++; 
		}
		header = new JPanel(new BorderLayout(1,3));
		JLabel month = new JLabel(months[currentM]+"  "+ cal.get(cal.YEAR));
		header.add(month, BorderLayout.CENTER);
		header.add(map, BorderLayout.SOUTH);
		return header;
	}

	/**
	 * returns the events view
	 * @return right the event view
	 */
	public JPanel eventView()
	{
		right = new JPanel();
		events = new JTextArea();
		eventpan = new JPanel();
		label = new JLabel();
		right.setLayout(new BorderLayout());
		label = new JLabel( fullDay[cal.get(cal.DAY_OF_WEEK) - 1] + " " + (cal.get(cal.MONTH)+1) + "/" + cal.get(cal.DATE));
		right.add(label, BorderLayout.NORTH);
		ChangeListener listener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent e) {
				events = new JTextArea(10,50);
				events.setText(model.format(ToDate()));
			}
		};
		model.addChangeListener(listener);
		events = new JTextArea(10,50);
		events.setText(model.format(ToDate()));
		eventpan.add(events);
		right.add(eventpan, BorderLayout.CENTER);
		return right;
	}
	/**
	 * makes a JFrame to make a new event
	 */
	public void Create()
	{
		String currentDay = ((cal.get(cal.MONTH)+1)+"/"+cal.get(cal.DATE) + "/" +cal.get(cal.YEAR));
		menu = new JFrame("create");
		menu.setSize(new Dimension(300,100));
		JTextField untitled = new JTextField("<Untitled event>",300);
		JButton save = new JButton("SAVE");
		JTextField date = new JTextField(currentDay, 25);
		JTextField start = new JTextField("00:00",25);
		JTextField end = new JTextField("00:00",25);
		JLabel to = new JLabel("To");
		save.addActionListener(new ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {
	        		String name = untitled.getText().toString();
	        		String thedate = date.getText().toString();
	        		String starttime = start.getText().toString();
	        		String endtime = end.getText().toString();
	        		Event temp = new Event(name, thedate, starttime, endtime);
	             	menu.dispose();
	        		model.addEvent(temp);
	        		refresh();
	        }
	     });
		Box bot = Box.createHorizontalBox();
		bot.add(date); bot.add(start); bot.add(to); bot.add(end);
		bot.add(save);
		menu.add(untitled, BorderLayout.NORTH);
		menu.add(bot, BorderLayout.SOUTH);
		menu.setVisible(true);
	}
	/**
	 * welcomes user to program 
	 */
	public void Welcome()
	{
		JOptionPane.showOptionDialog(null,
				"Welcome to the calendar", "Welcome",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				SETUP, SETUP[0]);
	}
	/**
	 * clears the event.txt
	 */
	public void clearFile()
	{
		try
		{
			PrintWriter writer = new PrintWriter(new File("event.txt"));
			writer.print("");
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}
	}
	/**
	 * Will load all of the events form the event.txt file
	 * Precondition : Loads in event from the event.txt file
	 * 	PostCondition : Added all of the event loaded from the event.txt file
	 */
	public void loadList()
	{
		ArrayList<String> temp = new ArrayList<String>();
		try
		{
			BufferedReader lineHold = new BufferedReader(new FileReader("event.txt"));
			Scanner inFile = new Scanner(lineHold);
			while(inFile.hasNextLine())
			{
				temp.add(inFile.nextLine());
			}
			for(int i =0; i < temp.size(); i++)
			{
				String hold = temp.get(i);
				int iFirst = hold.indexOf("   ") + 3;
				String name = hold.substring(0, iFirst-3);
				int iSecond = hold.indexOf("    ") + 4;
				String date = hold.substring(iFirst, iSecond-4);
				int iThird = hold.indexOf("     ") + 5;
				String time = hold.substring(iSecond, iThird -5);
				String endtime = hold.substring(iThird);
				Event eHold = new Event(name, date, time, endtime);
				model.addEvent(eHold);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}
	}
	/**
	 * fils the event.txt with all events
	 */
	public void fillfile()
	{
		try
		{
			PrintWriter writer = new PrintWriter(new File("event.txt"));
			Set<String> keyhold = model.getDates();
			for(String hold : keyhold)	
			{
				String temp = hold;
				ArrayList<Event> key = model.get(hold);
				for(int j = 0; j < key.size(); j++)
				{
					writer.println(key.get(j).printFullDay());
				}
			}
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}

	}
	/**
	 *  puts date in event format
	 * @return Date the date in format
	 */
	public String ToDate()
	{
		return ((cal.get(cal.MONTH)+1) + "/" + cal.get(cal.DATE) + "/" + cal.get(cal.YEAR));
	}
	public static void main(String[] args)
	{
		SimpleCalendar test = new SimpleCalendar();
	}
}
