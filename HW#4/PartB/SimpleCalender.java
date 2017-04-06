import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.*;

/*
 * A simple Calendar that can save events
 */

/**
 *
 * @author Leung Wai Chan
 * ID 007836868
 */
public class SimpleCalender
{
    
    public static void main(String[] args)
    {
        

        final JFrame frame=new JFrame();
        
        JPanel top= new JPanel();
        JButton back= new JButton("<");
        JButton forward= new JButton(">");
        JLabel spaceing = new JLabel("          ");
        JButton quit = new JButton("Quit");
        top.add(back);
        top.add(forward);
        top.add(spaceing);
        top.add(quit);
        GregorianCalendar cal=new GregorianCalendar(Locale.US);
        EventData data= new EventData();
         final Monthview left=new Monthview(cal,data);
        final DayView right= new DayView(left,data);
      
         back.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                left.previousDate();
              
                left.refresh(left.c.get(Calendar.MONTH), left.c.get(Calendar.DATE), left.c.get(Calendar.YEAR));
              
             
               right.refreshDay(left.c.get(Calendar.MONTH),left.c.get(Calendar.DATE));
               
            }
        });
         forward.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                left.nextDate();
                right.refreshDay(left.c.get(Calendar.MONTH),left.c.get(Calendar.DATE));
                left.refresh(left.c.get(Calendar.DATE), left.c.get(Calendar.DATE), left.c.get(Calendar.YEAR));
               
            }
        });
        
         
        frame.setLayout(new BorderLayout());
        frame.add(top,BorderLayout.NORTH);
        frame.add(left,BorderLayout.WEST);
        frame.add(right,BorderLayout.EAST);
         frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}