import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @author cay horstmann
 * @author John Anderson
*/
public class BarFrame extends JFrame implements ChangeListener, MouseListener
{
   /**
      Constructs a BarFrame object
      @param dataModel the data that is displayed in the barchart
   */
   public BarFrame(DataModel dataModel)
   {
      this.dataModel = dataModel;
      a = dataModel.getData();
      max = 0;

      setLocation(0,200);
      setLayout(new BorderLayout());
      
      addMouseListener(this);

      Icon barIcon = new Icon()
      {
         public int getIconWidth() { return ICON_WIDTH; }
         public int getIconHeight() { return ICON_HEIGHT; }
         public void paintIcon(Component c, Graphics g, int x, int y)
         {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.green);

            max =  (a.get(0)).doubleValue();
            for (Double v : a)
            {
               double val = v.doubleValue();
               if (val > max)
                  max = val;
            }

            double barHeight = getIconHeight() / a.size();

            int i = 0;
            for (Double v : a)
            {
               double value = v.doubleValue();

               double barLength = getIconWidth() * value / max;

               Rectangle2D.Double rectangle = new Rectangle2D.Double
                  (0, barHeight * i, barLength, barHeight);
               i++;
               g2.fill(rectangle);
            }
         }
      };

      add(new JLabel(barIcon));

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   /**
      Called when the data in the model is changed.
      @param e the event representing the change
   */
   public void stateChanged(ChangeEvent e)
   {
      a = dataModel.getData();
      repaint();
   }

   public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	   // Do nothing
		
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// do Nothing
	}
	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// do Nothing
	}
	
	public void mousePressed(MouseEvent e) {
		int location = (e.getY() - 30) / (ICON_HEIGHT/a.size());
		double value = max * ((double) e.getX() / ICON_WIDTH);
		dataModel.update(location, value);
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// to nothing
	}
   
   private ArrayList<Double> a;
   private DataModel dataModel;
   private double max;

   private static final int ICON_WIDTH = 200;
   private static final int ICON_HEIGHT = 200;
}
