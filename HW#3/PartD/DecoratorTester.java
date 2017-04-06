import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 * tests the encrypting and decrypting
 * @author John Anderson
 * @author cay hoursman
 *@version 1.1
 *created 10/29/2015
 */
public class DecoratorTester
{
   public static void main(String[] args) throws IOException
   {
      EncryptingWriter list = new EncryptingWriter(new FileWriter("tester.out"));
      list.write("ABCDEFGHIJKLMNOPQRSTUVWXYZ_ABCDEFGHIJKLMNOPQRSTUVWXYZ", 3, 27);
      PrintWriter test = new PrintWriter(list, true);
      test.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      test.close();
      char letters[] = "---------------------------".toCharArray();
      DecryptingReader read = new DecryptingReader(new FileReader("tester.out"));
      read.read(letters, 0, 27);
      System.out.println(letters);
      BufferedReader decode = new BufferedReader(read);
      String line = decode.readLine();
      System.out.println(line);
      decode.close();
   } 
}
