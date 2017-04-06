import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 *  this class controll the calendar interface.
 */
/**
 *
 * @author Ken
 */
public class Monthview extends JPanel
{

    public static int WEEKDAYS = 7;
    public static int NUMBEROFWEEK = 6;
    public static int II = 0;
    public GregorianCalendar c;
    private JPanel container;
    private JPanel calendar;
    private JButton create;
    private JLabel month_year;
    private static int[] buttondate = new int[3];
    private EventData data;

    /**
     *this constructor takes a calender object and data object
     * @param cal gregorian calender
     * @param e data tat saves events
     */
    public Monthview(GregorianCalendar cal,EventData e)
    {
        
        data = e;

        c = cal;
        buttondate[0] = c.get(Calendar.YEAR);
        buttondate[1] = c.get(Calendar.MONTH) + 1;
        buttondate[2] = c.get(Calendar.DATE);
        container = new JPanel();
        create = new JButton("Create");
        create.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Popout pop = new Popout(buttondate[0], buttondate[1], buttondate[2],data);
            }
        });
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        calendar = new JPanel();

        month_year = new JLabel(getMonthInEng(c.get(GregorianCalendar.MONTH)) + "  " + c.get(GregorianCalendar.YEAR));
        calendar.setLayout(new GridLayout(0, 7));

        String[] weekheaders =
        {
            "  S  ", "  M  ", " T ", "  W  ", "  T  ", "  F  ", "  S  "
        };
        for (String w : weekheaders)
        {
            calendar.add(new JLabel(w));

        }

        int skipDays = 0;
        Date x = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        switch (temp.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY:
                skipDays = 1;
                break;
            case Calendar.TUESDAY:
                skipDays = 2;
                break;
            case Calendar.WEDNESDAY:
                skipDays = 3;
                break;
            case Calendar.THURSDAY:
                skipDays = 4;
                break;
            case Calendar.FRIDAY:
                skipDays = 5;
                break;
            case Calendar.SATURDAY:
                skipDays = 6;
                break;
            default:
                break;
        }
        for (int i = 0; i < skipDays; i++)
        {
            calendar.add(new JLabel(""));
        }
        int maxDay = c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        for (int i = 1; i <= maxDay; i++)
        {
            II = i;
            final dayButton date = new dayButton(String.valueOf(i), c.get(Calendar.YEAR), c.get(Calendar.MONTH), i);
            if (i == c.get(Calendar.DATE))
            {

                date.setOpaque(true);
                date.setBackground(Color.GRAY);

            }
            date.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)

                {
                    if (date.isSelected() == false)
                    {

                        date.setSelect(true);
                        date.setOpaque(true);
                        date.setBackground(Color.WHITE);
                        buttondate[0] = date.getYear();
                        buttondate[1] = date.getMonth() + 1;
                        buttondate[2] = date.getDate();
                    } else
                    {
                        date.setSelect(false);
                        date.setOpaque(false);
                        date.setBackground(Color.red);
                    }
                }
            });
            calendar.add(date);
        }
        container.add(calendar);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(create);
        add(month_year);

        add(container);

    }

    /**
     *this changes the data to one day ago
     */
    public void previousDate()
    {
        int currentdate = c.get(Calendar.DATE);
        int currentmonth = c.get(Calendar.MONTH);

        if (currentdate - 1 == 0 && currentmonth - 1 >= Calendar.JANUARY) // ex.  4/1 to 3/31
        {

            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1, 1); // 2014/3/19
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH)); //2014/3/31

        } else if (currentdate - 1 == 0 && currentmonth - 1 < Calendar.JANUARY)
        {
            c.set(c.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 1);
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else
        {
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE) - 1);

        }
    }

    /**
     *move a day by one day
     */
    public void nextDate()
    {
        int currentdate = c.get(Calendar.DATE);
        int currentmonth = c.get(Calendar.MONTH);

        if (currentdate + 1 > c.getActualMaximum(Calendar.DAY_OF_MONTH) && currentmonth + 1 <= Calendar.DECEMBER) // ex.  1/30 to 2/1
        {

            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1);

        } else if (currentdate + 1 > c.getActualMaximum(Calendar.DAY_OF_MONTH) && currentmonth + 1 > Calendar.DECEMBER)
        {

            c.set(c.get(Calendar.YEAR) + 1, Calendar.JANUARY, 1);
        } else
        {
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE) + 1);
        }
    }

    /**
     *this update the inteface component
     * @param month month
     * @param date date 
     * @param year year
     */
    public void refresh(int month, int date, int year)
    {

        this.removeAll();
        container.removeAll();
        calendar.removeAll();
        calendar.setLayout(new GridLayout(0, 7));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(create);
        month_year.setText(getMonthInEng(c.get(Calendar.MONTH)) + "  " + c.get(Calendar.YEAR));
        container.add(month_year);
        add(container);
        String[] weekheader =
        {
            "  S  ", "  M  ", " T ", "  W  ", "  T  ", "  F  ", "  S  "
        };
        for (String w : weekheader)
        {
            calendar.add(new JLabel(w));

        }

        int skipDays = 0;
        Date x = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        switch (temp.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY:
                skipDays = 1;
                break;
            case Calendar.TUESDAY:
                skipDays = 2;
                break;
            case Calendar.WEDNESDAY:
                skipDays = 3;
                break;
            case Calendar.THURSDAY:
                skipDays = 4;
                break;
            case Calendar.FRIDAY:
                skipDays = 5;
                break;
            case Calendar.SATURDAY:
                skipDays = 6;
                break;
            default:
                break;
        }
        for (int i = 0; i < skipDays; i++)
        {
            calendar.add(new JLabel(""));
        }
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= maxDay; i++)
        {
            II = i;
            final dayButton d = new dayButton(String.valueOf(i), c.get(Calendar.YEAR), c.get(Calendar.MONTH), i);
            if (i == c.get(Calendar.DATE))
            {

                d.setOpaque(true);
                d.setBackground(Color.GRAY);

            }
            d.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)

                {
                    if (d.isSelected() == false)
                    {
                        buttondate[0] = d.getYear();
                        buttondate[1] = d.getMonth() + 1;
                        buttondate[2] = d.getDate();
                        d.setSelect(true);
                        d.setOpaque(true);
                        d.setBackground(Color.WHITE);
                    } else
                    {
                        d.setSelect(false);
                        d.setOpaque(false);
                        d.setBackground(Color.red);
                    }
                }
            });
            calendar.add(d);
        }
        for (int i = 0; i < (36 - skipDays - maxDay - 2); i++)
        {
            calendar.add(new JLabel(""));
        }
        container.add(calendar);
        revalidate();
    }

    public String getMonthInEng(int currentmonth)
    {
        currentmonth = c.get(Calendar.MONTH);
        String month = "";
        switch (currentmonth)
        {
            case 0:
                month = "January";
                break;
            case 1:
                month = "February";
                break;
            case 2:
                month = "March";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "Argust";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "October";
                break;
            case 10:
                month = "November";
                break;
            case 11:
                month = "December";
                break;
        }
        return month;
    }

   
}