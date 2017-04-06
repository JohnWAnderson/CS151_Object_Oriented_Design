import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Used to crate a card that is linked to the bank and account
 * Can Check is the account linked to the card is expired or not
 * Created 16 September 2015 
 * @author John Anderson
 * @version 1.0
 * 
 */
public class Card extends Account
{
	
	/** 
	 *  Sends the Card Information to the account
	 * @param type    The Type of 1 of the 2 banks
	 * @param number	The ID number on the card
	 * @param exp		The expiration date on the card
	 * @param pass		The password on the Bank
	 * 
	*/
	public Card(String Type, String number, String exp, String pass)
	{
		super(Type,number,exp,pass);
	}
	
	/** 
	 *  Looks to see if the card is expired or not expired
	 * @param check		The account/ card that is having its expiration date checked
	 * Precondition : The account that is going to have its date checked
	 * PostCondition : aprroves the card or sends you back to put in new card ID
	*/
	public static void checkExp(Account check)
	{
		Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		try
		{	
		Date checkDate = format.parse(check.getExp());
		if(checkDate.compareTo(currentDate) >= 0 )
			;
		else{
			System.out.println("Card is Expired.  Please try a valid card");
			String holder = check.getType();
			if(holder.equals("A"))
				ATMSystem.checkBankA();
			ATMSystem.checkBankB();
			}
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
			}
	}
}
