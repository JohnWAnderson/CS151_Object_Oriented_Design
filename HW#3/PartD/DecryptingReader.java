import java.io.Reader;
import java.io.IOException;
/**
 * Decryps the message
 * @author John Anderson 
 * @author cay hoursman
 *@version 1.1
 *created 10/29/2015
 */
public class DecryptingReader extends Reader
{
	   private Reader reader;
	   /**
	    * makes new reader
	    * @param reader new reader
	    */
   public DecryptingReader(Reader reader)
   {
      this.reader = reader;
   }
   /**
    * reads the message
    * @param list the list holder
    * @param offset the offset
    * @param length the length of the list
    */
   public int read(char[] list, int offset, int length) throws IOException
   {
      int r = reader.read(list, offset, length);
      for(int i = offset; i < offset + length; i++)
      {
         char current = list[i];
         if (Character.isLetter(current))
         {
        	 current = (char)(current - 3);
            if (!Character.isLetter(current))
            	current = (char)(current + 26);
            list[i] = current;  
         }
      }  
      return r;
   }
   /**
    * closes the reader
    */
   public void close() throws IOException
   {
      reader.close();
   }
}
