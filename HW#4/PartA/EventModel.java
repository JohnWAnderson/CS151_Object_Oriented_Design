import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * holds the hashmap of the events
 * Created 24 November 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class EventModel
{
	private final static String[] SETUP = { "Continue"};
	private static HashMap<String,  ArrayList<Event>> eventsHash = new HashMap<String,  ArrayList<Event>>();
	private ArrayList<ChangeListener> Listeners;
	private Boolean can = true;
	
    public EventModel()
    {
    	eventsHash = new HashMap<String,  ArrayList<Event>>();
    	Listeners = new ArrayList<ChangeListener>();
    }
    /**
     * adds the change listner to the Array
     * @param listener the listner being added
     */
	public void addChangeListener(ChangeListener listener)
	{
		Listeners.add(listener);
	}
    /**
     *  finds the array of events linked to the key
     * @param key the date key
     * @return array the array of events
     */
    public ArrayList<Event> get(String key)
    {
    	ArrayList<Event> array = eventsHash.get(key);
    	if(array == null)
    		return null;
    	else
    		return array;
    }
    /**
     * gives the set of all the dates
     * @return set  returns the set of keys
     */
    public Set<String> getDates()
    {
    	return eventsHash.keySet();
    }
    /**
     * will try to add an event into the hashmap and will add if no time conflicts
     * @param e the event trying to add
     */
	public void addEvent(Event e)
	{
		can = true;
		ArrayList<Event> array = eventsHash.get(e.getDate());
		if(array == null)
		{
			array = new ArrayList<Event>();
			eventsHash.put(e.getDate(), array);
			array.add(e);
		}
		else
		{
			for(int i=0;i < array.size(); i++)
			{
				Event temp = array.get(i);
				if(e.getStartTime() >= temp.getStartTime() && e.getStartTime() <= temp.getEndTime() ||
					e.getEndTime() >= temp.getStartTime() && e.getEndTime() <= temp.getEndTime() ||
					temp.getStartTime() >= e.getStartTime() && temp.getStartTime() <= e.getEndTime())
					can = false;
			}
			if(can == true)
				array.add(e);
			else
				error();
		}
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener listener : Listeners)
			listener.stateChanged(event);
		Collections.sort(array, Event.timeComparator);
	}
	/**
	 * error message that tells if event was not added
	 */
	public void error()
	{
		JOptionPane.showOptionDialog(null,
				"Event Can't be added", "Error",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				SETUP, SETUP[0]);
	}
	/**
	 * gets the events in the format
	 * @param date the date to get the events
	 * @return returning the string of events
	 */
	public String format(String date)	
	{
		String returning = "no events";
		ArrayList<Event> array = eventsHash.get(date);
		if(array != null)
		{
			returning = "";
			for(Event temp: array)
			{
				returning += temp.printDayEvent() + "\n";
			}
		}
		return returning;
	}
	
	
	
}