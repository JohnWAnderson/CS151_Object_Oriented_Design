import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Make Cards to save in the Bank.
 * As long as it follows the correct format.
 * Sends user to the ATM
 * Created 16 September 2015 
 * @author John Anderson
 * @version 1.0
 * 
 */
public class ATMSystem

{
	private static boolean match = false;
	private static Scanner in = new Scanner(System.in);
	private static String holder;
	private static Account hold;
	private static int cash;
	private static int current =50;
	public static PrintStream outputFile;
	
	public static void main(String[] args)
	{
		try{
		Card banka1 = new Card("A", "1234", "12/01/2016", "myPassword"); Account.addCard(banka1);// good
		Card banka2 = new Card("A", "123", "23/02/2014", "extrasafe"); Account.addCard(banka2);// expired
		Card banka3 = new Card("A", "12345", "13/12/2020", "qwert"); Account.addCard(banka3);// good
		Card bankb1 = new Card("B", "12", "21/04/2000", "password1234"); Account.addCard(bankb1);//expired
		Card bankb2 = new Card("B", "321", "12/02/2019", "SafeCard"); Account.addCard(bankb2);// good
		Card bankb3 = new Card("B", "21", "17/07/2018", "word"); Account.addCard(bankb3);// good
		PrintStream outputFile = new PrintStream("output.txt");
		System.setOut(outputFile);
		System.out.println("New Card : " + "Bank A,  CardID# 1234, expires on 12/01/2016, Account Password: myPassword");
		System.out.println("New Card : " + "Bank A,  CardID# 123, expires on 23/02/2014, Account Password: extrasafe");
		System.out.println("New Card : " + "Bank A,  CardID# 12345, expires on 13/12/2020, Account Password: qwert");
		System.out.println("New Card : " + "Bank B,  CardID# 12, expires on 21/04/2000, Account Password: password1234");
		System.out.println("New Card : " + "Bank B,  CardID# 321, expires on 12/02/2019, Account Password: SafeCard");
		System.out.println("New Card : " + "Bank B,  CardID# 21, expires on 17/07/2018, Account Password: word");
		System.out.println("-------------------------------------------------------------------------------------");
			holder =in.nextLine();
			System.out.println("What ATM would you like to use; atmA1, atmA2, atmB1, atmB2");
			System.out.println(holder);
		if(holder.equals("atmA1") || holder.equals("atmA2"))
			ATMSystem.checkBankA();
		else if(holder.equals("atmB1") || holder.equals("atmB2"))
			ATMSystem.checkBankB();
		else{
			outputFile.println("error no ATM exists.  Please enter Vaild ATM");
			CheckType();	}
			outputFile.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}
	}	
	/** 
	 * Takes the user through the process of using a BankA card
	 * Precondition : The requested bank type
	 * PostCondition : returns the end of the code
	*/
	public static void checkBankA()
	{
		System.out.println("Enter ID");
		holder = in.nextLine();
		System.out.println(holder);
		hold = Account.findIDA(holder);
		System.out.println("Enter Password");
		while(match == false){
			holder = in.nextLine();
			System.out.println(holder);
			match = Account.checkPass(hold, holder);
			}
		while(in.hasNext())
			{
			holder = in.nextLine();
			System.out.println(holder);
			if(holder.equals("done"))
				System.exit(0);
			cash = Integer.parseInt(holder);
			if(current >= cash)
			{
				current -= cash;
			Bank.withdraw(cash);
			}
			else
				System.out.println("This passes your limit you can withdraw.  Please enter lower limit or enter done");
			}
	}
	/** 
	 *  Takes the user through the process of using a BankB card
	 * Precondition : The requested bank type
	 * PostCondition : returns the end of the code
	 * 
	*/
	public static void checkBankB()
	{
		System.out.println("Enter ID");
		holder = in.nextLine();
		System.out.println(holder);
		hold = Account.findIDB(holder);
		System.out.println("Enter Password");
		while(match == false){
			holder = in.nextLine();
			System.out.println(holder);
			match = Account.checkPass(hold, holder);
			}
		while(in.hasNext())
		{
		holder = in.nextLine();
		System.out.println(holder);
		if(holder.equals("done"))
			System.exit(0);
		cash = Integer.parseInt(holder);
		if(current >= cash)
		{
			current -= cash;
		Bank.withdraw(cash);
		}
		else
			System.out.println("This passes your limit you can withdraw.  Please enter lower limit or enter done");
		}
	}
	/** 
	 *  Takes the users input and picks what ATM that is going to be used
	 * Precondition : node
	 * PostCondition : It sends the user to either bank A or bank B
	 * 
	*/
	public static void CheckType(){
		holder =in.nextLine();
		outputFile.println("What ATM whould you like to use; atmA1, atmA2, atmB1, atmB2");
		outputFile.println(holder);
	if(holder.equals("atmA1") || holder.equals("atmA2"))
		ATMSystem.checkBankA();
	else if(holder.equals("atmB1") || holder.equals("atmB2"))
		ATMSystem.checkBankB();
	else
		outputFile.println("error no ATM exists.  Please enter Vaild ATM");
		CheckType();	
	}
}
