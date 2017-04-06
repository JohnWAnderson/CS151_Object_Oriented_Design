import javax.swing.JButton;
import javax.swing.JLabel;

public class dayButton extends JButton
{
 private boolean selected;
 private int year;
 private int month;
 private int date;
 private int event;

    /**
     *constructor that takes dates, and text
     * @param text
     * @param year
     * @param month
     * @param date
     */
    public dayButton(String text,int year,int month,int date)
    {
        super(text);
        this.year=year;
        this.month=month;
        this.date=date;
        selected=false;
    }
    
    /**
     * checks if button is day
     * @return selected either true or false
     */
    public boolean isSelected()
    {
        return selected;
    }

    /**
     *sets selected to either true or false
     * @param select true of false
     */
    public void setSelect(boolean select)
    {
        selected=select;
    }
    
    /**
     *get the year
     * @return year
     */
    public int getYear()
    {
        return this.year;
        
    }

    /**
     *get the month 
     * @return month date of current month
     */
    public int getMonth()
    {
        return this.month;
        
    }
    
    /**
     *get the date
     * @return date date of current day
     */
    public int getDate()
    {
        return this.date;
    }
}