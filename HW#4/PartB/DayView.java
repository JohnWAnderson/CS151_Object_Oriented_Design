import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.*;


/*
 * A panel the shows daily view
 */
/**
 *
 * @author Ken
 */
public class DayView extends JPanel

{

    private Monthview a;
    private day aday;
    private JLabel day;
    private JTextArea events;
    private EventData data;
private JPanel container;

    /**
     *this construct takes month panel and event data
     * @param month month   
     * @param e event data
     */
    public DayView(Monthview month, EventData e)
    {
        data = e;
        a = month;
        aday = data.search(a.c.get(Calendar.YEAR), a.c.get(Calendar.MONTH)+1, a.c.get(Calendar.DATE));

        setLayout(new FlowLayout());
       container=new JPanel();
       container.setLayout(new BorderLayout());
        
        day = new JLabel(a.getMonthInEng(a.c.get(Calendar.MONTH)) + "  " + a.c.get(Calendar.DATE));
        if (aday != null)
        {
            events = new JTextArea(aday.getEvents(),5,40);
        } else
        {
            events = new JTextArea(5, 40);
        }
        container.add(day,BorderLayout.NORTH);
        container.add(events,BorderLayout.EAST);
        add(container);
    }

    /**
     *update panel
     * @param month month 
     * @param date date
     */
    public void refreshDay(int month, int date)
    {
        removeAll();
        this.setLayout(new FlowLayout());
        container.removeAll();
        
        aday = data.search(a.c.get(Calendar.YEAR), month+1, date);
        if (aday != null)
        {
            
            events = new JTextArea(aday.getEvents(),5, 40);
        } else
        {
           
            events = new JTextArea(5, 40);
        }
        container.add(new JLabel(a.getMonthInEng(month) + " " + date),BorderLayout.NORTH);
        container.add(events, BorderLayout.EAST);
        add(container);
        revalidate();
    }

    

}