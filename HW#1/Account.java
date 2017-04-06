import java.util.ArrayList;
/**
 * Stores all of the Accounts for both Bank A and Bank B and adds the new Card into an Account to hold. 
 * Used to look though the banks to see if the input ID matches the one givien by the ATM
 * After ATM gets password this method checks to see if it matches the Account that was picked 
 * Created 16 September 2015 
 * @author John Anderson
 * @version 1.0
 * 
 */
public class Account extends Bank
{
	private static ArrayList<Account> bankA = new ArrayList<>();
	private  static ArrayList<Account> bankB = new ArrayList<>();	
	/** 
	 *  Holds the Card Information and sends it to the Bank
	 * @param type    The Type of 1 of the 2 banks
	 * @param number	The ID number on the card
	 * @param exp		The expiration date on the card
	 * @param pass		The password on the Bank
	 * 
	*/
	public Account(String type, String number, String exp, String pass)
	{
		super(type,number,exp,pass);
	}
	/**  
	 * Is used to add new account cards into a list of accounts to be held
	 * @param newAccount   Is the new account that is added to the list of accounts for bank A or B
	 * Precondition : The account that needs to be added
	 * 	PostCondition : Added the new account into the list of accounts to the bank type
	*/
	public static void addCard(Account newAccount)
	{
		if(newAccount.getType().equals("A"))
			bankA.add(newAccount);
		else
			bankB.add(newAccount);
	}
	/**
	 * Looks to see if the account ID given by the user to scan through the account to see if the account exists
	 * @param ID		The ID given by the user
	 * @return Account	gives null or the found account
	 */
	public static Account findIDA(String ID){
		for(int i =0; i<bankA.size(); i++)
			if(bankA.get(i).getID().equals(ID))
			{
				Card.checkExp(bankA.get(i));
				return bankA.get(i);
			}
		System.out.println("ID doesn't match on ATM. Please try again");
		ATMSystem.checkBankA();
		return null;
	}
	/**
	 *  Looks to see if the account ID given by the user to scan through the account to see if the account exists
	 * @param ID		The ID given by the user
	 * @return Account	gives null or the found account
	 */
	public static Account findIDB(String ID){
		for(int i =0; i<bankB.size(); i++)
			if(bankB.get(i).getID().equals(ID))
			{
				Card.checkExp(bankB.get(i));
				return bankB.get(i);
			}
		System.out.println("ID doesn't match on ATM.  Please Try again");
		ATMSystem.checkBankB();
		return null;
	}
	/**
	 * Checks the password given by the user to the password of the account the user is trying to access
	 * @param check		The account that is having its password checked
	 * @param pass 		The password given by the user
	 * @return boolean  wither the account password is valid or not
	 */
	public static boolean checkPass(Account check, String pass)
	{
		if(check.getPassword().equals(pass)){
			System.out.println("Password Accepted.  Account is unlocked");
			return true;}
		else{
			System.out.println("This password is incorrect.  Please enter new password");
			return false;
		}
	}
}
