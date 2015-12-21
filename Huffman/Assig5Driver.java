import java.util.*;
import java.io.*;

public class Assig5Driver
{

public static void main(String[] args)
	{
		
		Assig5 A5 = new Assig5(args[0]);
		
		int choice = -1;
		Scanner Scanner = new Scanner(System.in);
		A5.preGetHuffmanTable();
		
		do
		{
		
		System.out.println("Please choose from the following:");
		System.out.println("1) Encode a text string");
		System.out.println("2) Decode a Huffman string");
		System.out.println("3) Quit");
		
		try
		{
		choice = Scanner.nextInt();
		}
		
		catch(InputMismatchException e)
		{
		System.out.println("Invalid input! Quitting the program!");
		choice = 3;
		}
		
		if(choice == 1)
		{
			System.out.println(A5.getHuffmanEncoding());
		}
		
		
		if(choice == 2)
		{
			System.out.println(A5.getHuffmanDecoding());
		}
		
		}while(choice != 3);
		
	}

}