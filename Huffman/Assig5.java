import java.util.*;
import java.io.*;

public class Assig5
{

	BinaryNode<Character> Tree;
	BufferedReader Reader;
	String[] Table;
	int NumberofLeafs;
	StringBuilder Decoder;
	
	public Assig5(String f)
	{
		
		Table = new String[25];
		NumberofLeafs = 0;
		
		try
		{
			Reader = new BufferedReader(new FileReader(f));
			Tree = buildTree(Tree, Reader);
		}
			
		catch(IOException e)
		{}
	
		
		System.out.println("The Huffman Tree has been restored");
		
	}
	
	public BinaryNode buildTree(BinaryNode Node, BufferedReader R)
	{
		
		String whichNode;
		
		try
		{
			whichNode = R.readLine();
			char nodeType = whichNode.charAt(0);
			
			if(nodeType == 'I')
			{
				BinaryNode newNode = new BinaryNode<Character>('\0');
				newNode.setLeftChild(buildTree(Node,R));
				newNode.setRightChild(buildTree(Node,R));
				return newNode;
			}
		
			else
			{
				char nodeData = whichNode.charAt(2);
				BinaryNode newNode = new BinaryNode(nodeData);
				NumberofLeafs++;
				return newNode;	
			}
			
		}
		
		catch(EOFException e)
		{
			return null;
		}
		
		catch(IOException e)
		{
			return null;
		}

			
	}
	
	public void preGetHuffmanTable()
	{	
		StringBuilder address = new StringBuilder();
		getHuffmanTable(address,Tree);
	}
	
	public void getHuffmanTable(StringBuilder theAddress, BinaryNodeInterface theTree)
	{	
		if(theTree.isLeaf())
		{
			Character C = (Character)theTree.getData();
			char position = C.charValue();
			int index = ((int)position-65);
			Table[index] = theAddress.toString();	
		}
		
		else if(theTree != null)
		{
			if(theTree.hasLeftChild())
			{
				theAddress.append("0");
				getHuffmanTable(theAddress, theTree.getLeftChild());
				theAddress.deleteCharAt((theAddress.length()-1));
			}
			
			if(theTree.hasRightChild())
			{
				theAddress.append("1");
				getHuffmanTable(theAddress, theTree.getRightChild());
				theAddress.deleteCharAt((theAddress.length()-1));
			}
		
		}
		
	}
	
	public String toStringHuffmanTable()
	{
		StringBuilder s = new StringBuilder("Here is the encoding table: \n");
		
		int i = 0;
		int letter = 65;
		
		while(i < NumberofLeafs)
			{
				s.append((char)letter + ": ");
				s.append(Table[i] + "\n");
				letter++;
				i++;
			}
			
		return s.toString();
	
	}
	
	public String getHuffmanEncoding()
	{
		Scanner Scanner = new Scanner(System.in);
		int letter = 65;
		int i = 0;
		String Encode;
		StringBuilder Encoding = new StringBuilder("Huffman String: \n");
		
		System.out.println("Enter a string from the following characters: ");
		
		while(i < NumberofLeafs)
		{
			System.out.print((char)letter);
			letter++;
			i++;
		}
		
		System.out.println();

		Encode = Scanner.nextLine().toUpperCase();
		
		i = 0;
		char data;
		int index = 0;
		
		while(i < Encode.length())
		{
			data = Encode.charAt(i);
			index = ((int)data - 65);
			
			Encoding.append(Table[index] + "\n");
			
			i++;
		}
		
		return Encoding.toString();
		
	}
	
	public String getHuffmanDecoding()
	{
		System.out.println(toStringHuffmanTable());
		
		Scanner Scanner = new Scanner(System.in);
		
		String Decode;
		StringBuilder Decoding = new StringBuilder("Text String: \n");
		
		System.out.println("Please enter a Huffman string (one line, no spaces): ");
		
		Decode = Scanner.nextLine();
		
		Decoder = new StringBuilder(Decode);
		
		while(Decoder.length() > 0)
		{
			char c = traverseHuffmanDecoding(Tree);
			Decoding.append(c);
		}
		
		return Decoding.toString();
	}
	
	public char traverseHuffmanDecoding(BinaryNodeInterface theTree)
	{
		
		BinaryNodeInterface newNode;
		
		if(theTree.isLeaf() || Decoder.length() == 0)
		{
			Character C = (Character)theTree.getData();
			return C.charValue();
		}
		
		else if(Decoder.charAt(0) == '0' && theTree.hasLeftChild())
		{
			newNode = theTree.getLeftChild();
			Decoder = Decoder.deleteCharAt(0);
			return traverseHuffmanDecoding(newNode);
		}
		
		else if(Decoder.charAt(0) == '1' && theTree.hasRightChild())
		{
			newNode = theTree.getRightChild();
			Decoder = Decoder.deleteCharAt(0);
			return traverseHuffmanDecoding(newNode);
		}
		
		else
			return '\0';
	
	}

}