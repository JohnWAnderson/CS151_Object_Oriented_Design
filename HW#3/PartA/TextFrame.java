import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;

/**
 * @author cay horstmann
 * @author John Anderson
*/
public class TextFrame extends JFrame implements ChangeListener
{
   /**
      Constructs a JFrame that contains the textfields containing the data
      in the model.
      @param d the model to display
   */
   public TextFrame(DataModel d)
   {
      dataModel = d;

      final Container contentPane = this.getContentPane();
      setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

      a = dataModel.getData();
      fieldList = new JTextField[a.size()];

      // A listener for action events in the text fields
      ActionListener l = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            // Figure out which field generated the event
            JTextField c = (JTextField) e.getSource();
            int i = 0;
            int count = fieldList.length;
            while (i < count && fieldList[i] != c)
               i++;

            String text = c.getText().trim();

            try
            {
               double value = Double.parseDouble(text);
               dataModel.update(i, value);
            }
            catch (Exception exc)
            {
               c.setText("Error.  No update");
            }
         }
      };

      final int FIELD_WIDTH = 11;
      for (int i = 0; i < a.size(); i++)
      {
         JTextField textField = new JTextField(a.get(i).toString(),FIELD_WIDTH);
         textField.addActionListener(l);
         add(textField);
         fieldList[i] = textField;
      }

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   public static void stateChanged(int l, double v)
   {
	  DecimalFormat df = new DecimalFormat("##.##");
	  for(int j = 0; j < a.size(); j++)
	  {
		  if(j == l)
		  {
			  df.format(v);
			  fieldList[j].setText(String.valueOf(v));
		  }
	   }
   }
   
   static DataModel dataModel;
   static JTextField[] fieldList;
   static ArrayList<Double> a;
   
//@Override
   public void stateChanged(ChangeEvent e) 
   {
		a = dataModel.getData();
		
		System.out.println("state change for textFrame ");
		for(int j = 0; j < a.size(); j++)
		{
	       fieldList[j].setText(String.valueOf(10)); 
		}
   }
}
