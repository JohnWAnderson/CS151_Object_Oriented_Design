import java.util.Comparator;
/**
 * Creates Events that the calendar keeps track of.
 * Compares events together to find what one comes first in the day.
 * Compares events to see what events is first by Date.
 * Created 9 October 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class Event
{
	private String name;
	private String date;
	private String time;
	/**
	 * Makes a new Event
	 * @param theName the name of the event
	 * @param theDate the date of the event
	 * @param theTime the time of the event
	 * Precondition : the info for the Event
	 * postcondition : the created event
	 */
	public Event(String theName, String theDate, String theTime)
	{
		name = theName;
		date = theDate;
		time = theTime;
	}
	/**
	 * Gets the name of the event
	 * @return name	The name of the event
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Gets the date of the event
	 * @return date	The date of the event
	 */
	public String getDate()
	{
		return date;
	}
	/**
	 * Gets the time of the event
	 * @return time	The time of the event
	 */
	public String getTime()
	{
		return time;
	}
	/**
	 * Gives the name and the time of the event
	 * @return DayEvent	gives the event info for day
	 */
	public String printDayEvent()
	{
		return (name + " at " +  time);
	}
	/**
	 * Gives the name, date, and time of the event
	 * @return FullDay  The name, date, and time of the event
	 */
	public String printFullDay()
	{
		return (name + "   " + date + "    " + time);
	}
	/**
	 * returns the year of the event
	 * @return year  the year of the event
	 */
	public int getYear()
	{
		return Integer.parseInt(date.substring(6,10));
	}
	/**
	 * gives the month of the event
	 * @return month  month of the event
	 */
	public int getMonth()
	{
		return Integer.parseInt(date.substring(0, 2));
	}
	/**
	 * Gets day of the event
	 * @return day  day of the event
	 */
	public int getDay()
	{
		return Integer.parseInt(date.substring(3, 5));
	}
	/**
	 * Gets the value of the time event
	 * @return StartTime  the time the event starts at
	 */
	public int getStartTime()
	{
		int hold = 60 * Integer.parseInt(time.substring(0,2));
		hold += ((Integer.parseInt(time.substring(3,5))));
		return hold;
	}
	/**
	 * Compares the start time of events to organize them
	 * Precondition: gives events to compare
	 * Postcondition: returns value of compare method
	 */
	public static final Comparator<Event> timeComparator = new Comparator<Event>()
	{
		@Override
		public int compare(Event e1, Event e2)
		{
			return e1.getStartTime() - e2.getStartTime();
		}
	};
	/**
	 * compares the full date of the event to organize them
	 * Precondition: gives events to compare
	 * Postcondition: returns value of compare method
	 */
	public static final Comparator<Event> DateComparator = new Comparator<Event>()
	{
		@Override
		public int compare(Event e1, Event e2)
		{
			if(e1.getYear() == (e2.getYear()))
			{
				if(e1.getMonth() == e2.getMonth())
					return e1.getDay() - e2.getDay();
				else
				return e1.getMonth() - e2.getMonth();	
			}
			else
				return e1.getYear() - e2.getYear();
		}
	};
}
