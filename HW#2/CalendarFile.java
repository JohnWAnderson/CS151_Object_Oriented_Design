import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *used to clear or load in events form the event.txt file
 * Created 9 October 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class CalendarFile 
{	/**
	 * Will clear all current events in the Event.txt file
	 * Precondition : The contents of the event.txt file
	 * 	PostCondition : A empty event.txt file
	 */
	public static void clearFile()
	{
		try
		{
			PrintWriter writer = new PrintWriter(new File("event.txt"));
			writer.print("");
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}
	}
	/**
	 * Will load all of the events form the event.txt file
	 * Precondition : Loads in event from the event.txt file
	 * 	PostCondition : Added all of the event loaded from the event.txt file
	 */
	public static void loadList()
	{
		ArrayList<String> temp = new ArrayList<String>();
		try
		{
			BufferedReader lineHold = new BufferedReader(new FileReader("event.txt"));
			Scanner inFile = new Scanner(lineHold);
			while(inFile.hasNextLine())
			{
				temp.add(inFile.nextLine());
			}
			for(int i =0; i < temp.size(); i++)
			{
				String hold = temp.get(i);
				int iFirst = hold.indexOf("   ") + 3;
				String name = hold.substring(0, iFirst-3);
				int iSecond = hold.indexOf("    ") + 4;
				String date = hold.substring(iFirst, iSecond-4);
				String time = hold.substring(iSecond);
				Event eHold = new Event(name, date, time);
				MyCalendar.addEvent(eHold);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}
	}
}
