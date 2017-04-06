
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
;

/*
 * this class is a popout window
 */

/**
 *
 * @author Ken
 */
public class Popout extends JFrame

{
    
    private JPanel container;
    private JTextArea name;
    private JTextField date;
    private JTextField starttime;
    private JTextField endtime;
    private JButton save;
    private EventData data;
    private day aDay;
   
    /**
     * constuct a popwindow with dates, and data
     * @param year year
     * @param month month
     * @param day date
     * @param e eventdata
     */
    public Popout(int year,int month,int day,final EventData e)
    {
        data=e;
        
        setTitle("Add event");
         container= new JPanel(new BorderLayout());
         final int y=year;
         final int m= month;
         final int d=day;
       name=new JTextArea("Untitled Event", 5, 20);
       
       date=new JTextField(month+"/"+day+"/"+year,8);
       starttime=new JTextField("Starting Time",7);
       endtime=new JTextField("end time",7);
       save= new JButton("Save");
      save.addActionListener(new ActionListener()
      {

            @Override
            public void actionPerformed(ActionEvent en)
            {
                String n=name.getText();
                String st=starttime.getText();
                String et=endtime.getText();
              aDay=new day(y, m, d);
              
              
              if (data.search(y, m, d)!=null)
               {
                   data.search(y,m,d).setEvents(st+" - "+et+"           "+n+"\n");
               }
               else
               {
                   aDay.setEvents(st+" - "+et+"           "+n+"\n");
               e.addevent(aDay);
               
               }
              
              JFrame done= new JFrame();
               done.setTitle("Event added");
               done.add(new JLabel("Your event has been successfully saved."));
               done.setVisible(true);
               done.setResizable(false);
               done.setLocationRelativeTo(null);
               done.pack();
               
               
            }
        });
   container.add(name,BorderLayout.NORTH);
   container.add(date,BorderLayout.WEST);
   container.add(starttime, BorderLayout.CENTER);
   container.add(endtime,BorderLayout.EAST);
   container.add(save,BorderLayout.SOUTH);
    add(container);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
       setVisible(true);
     
    }
}