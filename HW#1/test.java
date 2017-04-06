import java.util.*;
import java.io.*;
import java.text.*;
public class test
{
	public static void main(String[] args)
	{
		String name = "Output.txt";
		try{
			PrintWriter output = new PrintWriter(name);
			output.print("Hello There");
			System.out.println("hi there");
			output.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No file");
		}
		
	}
}