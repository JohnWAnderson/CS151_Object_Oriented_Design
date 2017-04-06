	import java.io.FileNotFoundException;
	import java.util.Collections;
	import java.util.GregorianCalendar;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.io.File;
	import java.io.PrintWriter;
	/**
	 *prints the month calendar
	 *will show current date on the calendar if it's the correct month
	 * print the current Day and events in the day
	 *Add events to the event Hashmap
	 *prints the HashMap of events
	 *Will fill the event.txt with all of the current events
	 * Created 9 October 2015
	 * @author John Anderson
	 * @version 1.0
	 * 
	 */
	public class MyCalendar
	{
		private static final String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		private static final String day[] = {"Su ", "Mo ", "Tu ", "We ", "Th ", "Fr ", "Sa "};
		private static final String fullDay[] = {"sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		private static HashMap<String,  ArrayList<Event>> eventsHash = new HashMap<String,  ArrayList<Event>>();
		private static GregorianCalendar cal = new GregorianCalendar();
		private static final int currentMonth = cal.get(cal.MONTH);
		private static final int dayHolder = cal.get(cal.DAY_OF_MONTH);
		private static final int currentYear = cal.get(cal.YEAR);
		private static int spot = 0;
		/**
		 * Prints the map of the month that the date is set too
		 * Will put the current date in [ ] if it's the current month
		 * Precondition :  has info of calendar date
		 * Postcondition : prints calendar onto camand prompt
		 */
		public static void viewCalenderCurrent()
		{
			cal.set(cal.DATE, 1);
			int max = cal.getActualMaximum(cal.DAY_OF_MONTH);
			int firstDay = cal.get(cal.DAY_OF_WEEK);
			System.out.println("  "+ months[cal.get(cal.MONTH)] + " " + cal.get(cal.YEAR));
			for(int i=0; i < day.length; i++)
			{
				System.out.print(day[i]);
			}
			System.out.println(""); 
			spot = 0;
			for(int j=1; j < firstDay; j++)
			{
				spot++;
				System.out.print("   ");
			}
			for(int k =1 ; k <= max; k++)
			{
				if(spot%7 == 0 && spot != 0) 
				{
					System.out.println("");
					spot =+ 1;
				}
				else
					spot++;
				if(k < 10)
				{
					if(	k == dayHolder && cal.get(cal.MONTH) == currentMonth)
						System.out.print("[" + k + "]");
					else
						System.out.print(" " + k + " ");
				}
				else
				{
					if(	k == dayHolder && cal.get(cal.MONTH) == currentMonth)
						System.out.print("[" + k + "]"); 
					else
						System.out.print(k + " ");
				}
			}
			System.out.print("\n");
			System.out.println(" ");
		}
		/**
		 * Prints a view of the day and events that are set to that day.
		 * Precondition : has information of set date
		 * Postcondition : prints the date and events set to the date
		 */
		public static void dayCalendar()
		{
				System.out.print(fullDay[cal.get(cal.DAY_OF_WEEK) -1]);
			    System.out.print(", ");
				System.out.print(months[cal.get(cal.MONTH)]);
				System.out.print(" ");
				System.out.print(cal.get(cal.DAY_OF_MONTH));
				System.out.print(", ");
				System.out.print(cal.get(cal.YEAR));
				System.out.println("");
				String hold = getDayFormat();
				printDayEvents(hold);
		}
		/**
		 * Takes a date and prints all of the events that are set for that date
		 * @param c the date that the events will print for
		 * Precondition : the date to check
		 * Postcondition : prints all of the events saved to the date
		 */
		public static void printDayEvents(String c)
		{	
			ArrayList<Event> holder = eventsHash.get(c);
			if(holder != null)
			{
			for(int q = 0; q < holder.size(); q++){
				Event h = holder.get(q);
				String msg = h.printDayEvent();
				System.out.println(msg);
				}
			}
		}
		/**
		 * Takes the given event and adds it to the event list for the date it has
		 * @param e the event to add
		 * Precondition : gives an event
		 * Postcondition : event added to the arraylist in hashmap
		 */
		public static void addEvent(Event e)
		{
			ArrayList<Event> temp = eventsHash.get(e.getDate());
			if(temp == null)
			{
				temp = new ArrayList<Event>();
				eventsHash.put(e.getDate(), temp);
			}
			temp.add(e);
			Collections.sort(temp, Event.timeComparator);
		}
		/**
		 * Either move the day the amount that is given
		 * @param i the amount to change the day
		 * Precondition : an amount to change the day
		 * Postcondition : the date is changed for the amount of days
		 */
		public static void changeDay(int i)
		{	
				cal.add(cal.DATE, i);
				dayCalendar();
				Menu.viewDay();
		}
		/**
		 * Changes the month by the amount given
		 * @param i the amount to change month
		 * Precondition : an amount to change the month
		 * Postcondition : the month is changed for the amount
		 */
		public static void changeMonth(int i)
		{
				cal.add(cal.MONTH, i);
				viewCalenderCurrent();
				Menu.viewMonth();
		}
		/**
		 * sets the calendar equal to the current date
		 * Precondition : the calendar set day
		 * Postcondition : sets calendar to the current day
		 */
		public static void resetCal()
		{
			cal.set(currentYear, currentMonth, dayHolder);
		}
		/**
		 * changes the date to the requested date
		 * @param d the day to change to
		 * @param m the month to change to
		 * @param y the year to change to
		 * Precondition : gives input amounts 
		 * Postcondition : changes date to the amounts
		 */
		public static void changeDate(int d, int m, int y)
		{
			cal.set(y, (m-1), d);
		}
		/**
		 * used to get the format of the day into the mm/dd/yyyy format
		 * @return h  the format of the day
		 */
		public static String getDayFormat()
		{
			String day = "";
			String month = "";
			if(cal.get(cal.DAY_OF_MONTH) < 10)
				day = ("0"+Integer.toString(cal.get(cal.DAY_OF_MONTH)));
			else
				day = Integer.toString(cal.get(cal.DAY_OF_MONTH));
			if((cal.get(cal.MONTH) + 1) < 10)
				month = ("0"+(Integer.toString((cal.get(cal.MONTH) + 1))));
			else
				month = Integer.toString(cal.get(cal.MONTH) +1);
			
			String year = Integer.toString(cal.get(cal.YEAR));
			String h = (month+"/"+day+"/"+year);
			return h;
		}
		/**
		 * clears the hashmap
		 * Precondition: gives event hashmap
		 * Postcondition: hashmap is Empty 
		 */
		public static void deleteAllEvent()
		{
			eventsHash.clear();
		}
		/**
		 * takes a input date and deletes all events on that day
		 * @param key the date to clear
		 * Precondition: the date that needs to have events delete
		 * Postcondition: all events with that date are deleted
		 * 
		 */
		public static void deleteDay(String key)
		{
			ArrayList<Event> temp = eventsHash.get(key);
			temp.clear();
		}
		/**
		 * goes though an prints all of the current events in order
		 * Precondition: give the full hashmap of events
		 * Postcondition: prints all events in order
		 */
		public static void printList()
		{
			ArrayList<Event> holder = new ArrayList<Event>();
			ArrayList<String> keyhold = new ArrayList<String>();
			for(HashMap.Entry<String, ArrayList<Event>> enter: eventsHash.entrySet())
			{
				 keyhold.add(enter.getKey());
			}
			for(int i =0; i < keyhold.size(); i++)
			{
				ArrayList<Event> hold = eventsHash.get(keyhold.get(i));
				for(int j = 0; j < hold.size(); j++)
				{
					holder.add(hold.get(j));
				}
			}
			Collections.sort(holder, Event.DateComparator);
			for(int j = 0; j < holder.size(); j++)
			{
				Event hold = holder.get(j);
				Menu.dateChanger(hold.getDate());
				System.out.println(cal.get(cal.YEAR)+ " : " + months[cal.get(cal.MONTH)] + ", " + fullDay[cal.get(cal.DAY_OF_WEEK)-1] + " " +cal.get(cal.DATE) + ".  " + hold.getTime() + " " + hold.getName());
			}
		}
		/**
		 * puts all events in the event.txt file
		 * Precondition: give the full hashmap of events
		 * Postcondition: all events printed on event.txt
		 */
		public static void fillFile()
		{
			try
			{
				PrintWriter writer = new PrintWriter(new File("event.txt"));
				ArrayList<String> keyhold = new ArrayList<String>();
				for(HashMap.Entry<String, ArrayList<Event>> enter: eventsHash.entrySet())
				{
					 keyhold.add(enter.getKey());
				}
				for(int i =0; i < keyhold.size(); i++)
				{
					ArrayList<Event> hold = eventsHash.get(keyhold.get(i));
					for(int j = 0; j < hold.size(); j++)
					{
						writer.println(hold.get(j).printFullDay());
					}
				}
				writer.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.print("No file");
			}
		}
	}