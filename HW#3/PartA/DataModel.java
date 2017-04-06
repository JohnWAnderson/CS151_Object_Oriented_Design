import java.util.ArrayList;
import javax.swing.event.*;

/**
 * @author cay horstmann
 * @author John Anderson
*/
public class DataModel
{

   public DataModel(ArrayList<Double> d)
   {
      data = d;
      listeners = new ArrayList<ChangeListener>();
   }

   /**
      Constructs a DataModel object
      @return the data in an ArrayList
   */
   public ArrayList<Double> getData()
   {
      return (ArrayList<Double>) (data.clone());
   }

   /**
      Attach a listener to the Model
      @param c the listener
   */
   public void attach(ChangeListener c)
   {
      listeners.add(c);
   }

   /**
      Change the data in the model at a particular location
      @param location the index of the field to change
      @param value the new value
   */
   public void update(int location, double value)
   {      
      data.set(location, new Double(value));
      
      
      for(int i = 0; i < data.size(); i++)
      {
    	  data.get(i).toString();
      }
      
      for (ChangeListener l : listeners)
      {
         l.stateChanged(new ChangeEvent(this));
         TextFrame.stateChanged(location, value);
      }
   }

   ArrayList<Double> data;
   ArrayList<ChangeListener> listeners;
}
