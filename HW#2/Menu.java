import java.util.Scanner;
/**
 * Takes user input and will follow the users input
 * Will send the info gained to the calendar or other file to do the function
 * Created 9 October 2015
 * @author John Anderson
 * @version 1.0
 * 
 */
public class Menu 
{
	private static Scanner in = new Scanner(System.in);
	private static String input;
	/**
	 * Takes user input and sends them to the requested method
	 * Precondition : The request for an input
	 * Postcondition : Sends user down requested path 
	 */
	public static void mainMenu()
	{
		System.out.println("[L]oad   [V]iew by  [C]reate [G]o to [E]vent list [D]elete  [Q]uit");
		String input = in.nextLine();
		System.out.println(input);
		if(input.equals("L"))
			load();
		else if(input.equals("V"))
			ViewBy();
		else if(input.equals("C"))
			Create();
		else if(input.equals("G"))
			GoTo();
		else if(input.equals("E"))
			EventList();
		else if(input.equals("D"))
			Delete();
		else if(input.equals("Q")){
			CalendarFile.clearFile();
			MyCalendar.fillFile();
			System.out.println("GoodBye");
			System.exit(0);
		}
	}
	/**
	 * loads the Event.txt file into the calendar hashmap
	 * Precondition : calls to load form file
	 * Postcondition : loads file into hashmap and clears file
	 */
	public static void load()
	{
		CalendarFile.loadList();
		CalendarFile.clearFile();
		mainMenu();
	}
	/**
	 * takes in the info for a new event and adds it to the calendar
	 * Precondition : User requested input
	 * Postcondition : The created Event is added to calendar 
	 */
	public static void Create()
	{
		System.out.println("Title:");
		input = in.nextLine();
		String name = input;
		System.out.println(input);
		System.out.println("Date MM/DD/YYYY format :");
		input = in.nextLine();
		String date = input;
		System.out.println(input);
		System.out.println("Set start time :  XX:XX  or XX:XX-XX:XX");
		input = in.nextLine();
		String time = input;
		System.out.println(input); 
		Event eHold = new Event(name,date,time);
		addToCal(eHold);
	}
	/**
	 * sends event to the add method for the calendar
	 * @param e  The event to add
	 * Precondition : the event to add
	 * Postcondition : Adds the event to the calendar
	 */
	public static void addToCal(Event e)
	{
		MyCalendar.addEvent(e);
		mainMenu();
	}
	/**
	 * takes user input and sends them to view either the day or month
	 * Precondition : requests the input
	 * Postcondition : sends to the Day or Month view
	 */
	public static void ViewBy()
	{
		MyCalendar.resetCal();
		System.out.println("[D]ay view or [M]view ?");
		input = in.nextLine();
		System.out.println(input);
		if(input.equals("D")){
			MyCalendar.dayCalendar();
			viewDay();
		}
		else if(input.equals("M")){
			MyCalendar.viewCalenderCurrent();
			viewMonth();
			}
	}
	/**
	 * Takes input form user and either puts to the next day or the previous day or sends to main menu
	* Precondition :requests the user input 
	* Postcondition : changes the calendar to the next or previous day
	*/
	public static void viewDay()
	{
		System.out.println("[P]revious or [N]ext or [M]ain menu ?");
		input = in.nextLine();
		System.out.println(input);
		if(input.equals("P"))
			MyCalendar.changeDay(-1);
		else if(input.equals("N"))
			MyCalendar.changeDay(1);
		else if(input.equals("M"))
			mainMenu();
	}
	/**
	 * Takes user input and either sends to next month or previous month or sends to main menu
	 * Precondition : requests the user input
	 * Postcondition : changes the month either to the next or previous month
	 */
	public static void viewMonth()
	{
		System.out.println("[P]revious or [N]ext or [M]ain menu ?");
		input = in.nextLine();
		System.out.println(input);
		if(input.equals("P"))
			MyCalendar.changeMonth(-1);
		else if(input.equals("N"))
			MyCalendar.changeMonth(1);
		else if(input.equals("M"))
			mainMenu();
	}
	/**
	 * takes user input and sends user to the Dayview of the requested date
	 * Precondition :requests user date
	 * Precondition : changes the date and sends to day view of that date
	 */
	public static void GoTo()
	{
		System.out.println("Date MM/DD/YYYY format :");
		input = in.nextLine();
		String date = input;
		System.out.println(input);
		dateChanger(date);
		MyCalendar.dayCalendar();
		viewDay();
	}
	/**
	 * Takes the input date and the calendar into that date
	 * @param date the date to change to
	 * Precondition : the date to change the calendar to 
	 * Postcondition : the calendar changed to that date
	 */
	public static void dateChanger(String date)
	{
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6,10));
		MyCalendar.changeDate(day, month, year);
	}
	/**
	 * calls the calendar to print the event list
	 * Precondition : calls to printlist
	 * Postcondition : prints the event list and sends to menu
	 */
	public static void EventList()
	{
		MyCalendar.printList();
		mainMenu();
	}
	/**
	 * takes the user input and will either delete selected day or delete all events
	 * Precondition : User input
	 * Postcondition : the date deleted or all deleted
	 */
	public static void Delete()
	{
		System.out.println("[S]elected or [A]ll ? ");
		String input = in.nextLine();
		System.out.println(input);
		if(input.equals("S"))
		{
			System.out.println("Enter Date : MM/DD/YYYY ");
			input = in.nextLine();
			System.out.println(input);
			MyCalendar.deleteDay(input);
			mainMenu();
		}
		else if(input.equals("A"))
		{
			MyCalendar.deleteAllEvent();
			CalendarFile.clearFile();
		}
		mainMenu();
	}
}
