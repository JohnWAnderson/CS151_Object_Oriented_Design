public class day
{
private int year;
private int month;
private int day;
private String event;

    public day(int year,int month,int day)
    {
        this.year=year;
        this.month=month;
        this.day=day;
        event="";
    }
    
    /**
     * get year
     * @return year
     */
    public int getYear()
        {return year;}
    
    /**
     *get month
     * @return month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     *get date
     * @return date
     */
    public int getDate()
    {
        return day;
    }
    
    /**
     *set event contents
     * @param text event conents
     */
    public void setEvents(String text)
    {
        event+=text;
        
    }
    
    /**
     *get event contents
     * @return string that describe an event
     */
    public String getEvents()
    {
        return event;
    }
    
    /**
     * check if event happens on the same day
     * @param year
     * @param month
     * @param day
     * @return true if same false otherwise
     */
    public boolean sameDay(int year,int month,int day)
    {
        return this.year==year && this.month==month && this.day==day;
    }
}