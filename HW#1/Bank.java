/**
 * Makes the Bank and is used by the subclasses to get the Bankaccount information
 * gets command form the ATM to make a withdraw and will withdraw if it's conditions are met
 * Created 16 September 2015 
 * @author John Anderson
 * @version 1.0
 * 
 */

public class Bank
{
	private  String BankType;
	private  String AccountNumber;
	private String expires;
	private  String password;
	private static int currentAmount = 40;
	
	/** 
	 *  Holds the Bank Information
	 * @param type    The Type of 1 of the 2 banks
	 * @param number	The ID number on the card
	 * @param exp		The expiration date on the card
	 * @param pass		The password on the Bank
	 * 
	*/
	public Bank(String type, String number, String exp, String pass)
	{
		BankType = type;
		AccountNumber = number;
		expires = exp;
		password = pass;
	}
	/**  Gets the input of from the ATM on the amount to withdraw and if the input valid then it will withdraw the amount form the bank.
	 * @param amount  The amount to take out of the current amount
	 * Precondition : Gives the amount that is requested to take out of the Bank from the ATM
	 * 	PostCondition : Takes out the requested amount form the Bank if it meets the condition
	*/

	public static void withdraw(int amount)
	{
		if(currentAmount >= amount){
			currentAmount -= amount;
			System.out.println("you withdrew $" + amount + " from your account.  You have $" + currentAmount + " remaining balance for today.");
			System.out.println("Enter another amount to withdraw or type done");}
		else
			System.out.println("This exceeds your limit. Please enter lower amount or type done");
	}
	/**
	 * give you the account ID number
	 * @return AccountNumber   The ID number on the account
	 */
	public String getID(){return AccountNumber;}
	/**
	 * give you the Bank type
	 * @return BankType  What Bank is the bank saved to
	 */
	public String getType(){return BankType;}
	/**
	 * give you the expiration date
	 * @return expires  The expiration date
	 */
	public String getExp(){return expires;}
	/**
	 * give you the account password
	 * @return password    The password of the account
	 */
	public String getPassword(){return password;}

	
}