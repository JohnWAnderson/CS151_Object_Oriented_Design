import java.io.Writer;
import java.io.IOException;
/**
 * writes the encryption
 * @author John Anderson
 * @author cay hoursman
 *@version 1.1
 *created 10/29/2015
 */
public class EncryptingWriter extends Writer
{
	private Writer writer;
	/**
	 * makes new writer
	 * @param writer new writer
	 */
   public EncryptingWriter(Writer writer)
   {
      this.writer = writer;
   }
   /**
    * writes message
    * @param list the list holder
    * @param offset the offset
    * @param length the length of the list
    */
   public void write(char[] list, int offset, int length) throws IOException
   {
	   for(int i = offset; i < offset + length; i++)
	      {
	         char current = list[i];
	         if (Character.isLetter(current))
	         {
	        	 current = (char) ((int) current + 3);
	            if (!Character.isLetter(current))
	            	current = (char) ((int) current - 26);
	            list[i] = current; 
	         }
	      }  
      writer.write(list, offset, length);
   }
   /**
    * closes the writer
    */
   public void close() throws IOException
   {
      writer.close();
   }
   /**
    * clears the writer
    */
   public void flush() throws IOException
   {
      writer.flush();
   }
}
