import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * The model of the MVC pattern
 * Created 29 October 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class Model
{
private ArrayList<String> strings;
private ArrayList<ChangeListener> Listeners;
	/**
	 * Creates the arraylists
	 */
	public Model()
	{
		strings = new ArrayList<String>();
		Listeners = new ArrayList<ChangeListener>();
	}
	/**
	 * adds the change listener to the arraylist
	 * @param listener change listener added to list
	 */
	public void addChangeListener(ChangeListener listener)
	{
		Listeners.add(listener);
	}
	/**
	 * adds string to the model
	 * @param s string to add to moddel
	 */
	public void addString(String s)
	{
		strings.add(s);
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener listener : Listeners)
			listener.stateChanged(event);
	}
	/**
	 * gives the format for the view
	 * @param f the current text area to paint over
	 * @return f the string to add to the text area
	 */
	public String format(String f)
	{
		for(int i =0; i < strings.size(); i++)
		{
			 f += strings.get(i) + "\n";
		}
		return f;
	}
}