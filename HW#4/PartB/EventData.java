import java.util.ArrayList;

public class EventData
{
   private ArrayList<day> data;
    public EventData()
    {
        data= new ArrayList<>();
    }

    /**
     * search matched date
     * @param year year
     * @param month month
     * @param day day
     * @return a day object
     */
    public day search(int year,int month, int day)
    {
        for (int i =0;i<data.size();i++)
        {
            if ( data.get(i).getYear()==year && data.get(i).getMonth()==month && data.get(i).getDate()==day)
                return data.get(i);
        }
        return null;
    }

    /**
     *add a day element
     * @param aday a day
     */
    public void addevent(day aday)
    {
        data.add(aday);
    }
     
}