/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 4
    Due Date:           March 28, 2014
    
    Description:        Create a class called LinkedListPlus by extending the class A4LList and adding new functionality to it such as left/right shifting and rotating, and reversing.
    					These methods will be useful when implementing the next subclass ReallyLongInt. Once the LinkedListPlus is finished being implemented, the class called ReallyLongInt
    					by extending the class LinkedListPlus. This class will add the functionality of adding/comparing two operands, and multiplying/dividing an operand to a 10^n power.
*/

// CS 0445 Spring 2014 Assignment 4
// This is a partial implementation of the ReallyLongInt class.  You need to
// complete the implementations of the remaining methods.  Also, for this class
// to work, you must complete the implementation of the LinkedListPlus class.
// See additional comments below.

import java.util.*;

public class ReallyLongInt 	extends LinkedListPlus<Integer> 
							implements Comparable<ReallyLongInt>
{

	private ReallyLongInt Result;
	// Default constructor
	public ReallyLongInt()
	{
		super();
	}

	// Note that we are adding the digits here in the FRONT. This is more efficient
	// (no traversal is necessary) and results in the LEAST significant digit first
	// in the list.
	public ReallyLongInt(String s)
	{
		super();
		char c;
		int digit;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9'))
			{
				digit = c - '0';
				this.add(1, new Integer(digit));
			}
		}
	}

	// Simple call to super to copy the nodes from the argument ReallyLongInt into a
	// new one.
	public ReallyLongInt(ReallyLongInt rightOp)
	{
		super(rightOp);
	}
	
	// Method to put digits of number into a String.  Since the numbers are
	// stored "backward" (least significant digit first) we first reverse the
	// number, then traverse it to add the digits to a StringBuilder, then
	// reverse it again.  This seems like a lot of work, but accessing the list
	// from back to front using repeated calls to the getEntry() method is actually
	// much more work -- we will discuss this in lecture after break.
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (numberOfEntries > 0)
		{
			this.reverse();
			for (Node curr = firstNode; curr != null; curr = curr.next)
			{
				sb.append(curr.data);
			}
			this.reverse();
		}
		return sb.toString();
	}

	// You must implement the methods below.  See the descriptions in the assignment
	// sheet.
	
	public ReallyLongInt add(ReallyLongInt rightOp)
	{
		ReallyLongInt Result = new ReallyLongInt();
		
		Integer lOp;
		Integer rOp;
		Integer sum = new Integer(0);
		Integer carry = new Integer(0);
		int i;
		int j = 1;
		
		if(this.numberOfEntries > rightOp.numberOfEntries)
			i = this.numberOfEntries;
		
		else
			i = rightOp.numberOfEntries;
			
		while(i >= 0)
		{
			lOp = this.getEntry(j);
			rOp = rightOp.getEntry(j);
			
			if(lOp == null)
			lOp = 0;
			
			if(rOp == null)
			rOp = 0;
			
			Integer temp = lOp + rOp + carry;
			
			sum = temp%10;
			Result.add(sum);
			
			if(temp >= 10)
			carry = 1;
			
			else
			carry = 0;
			
			i--;
			j++;
		
		}
		
		return Result;
	}

	public int compareTo(ReallyLongInt rOp)
	{
		
		if(this.numberOfEntries > rOp.numberOfEntries)
		{
			return 1;
		}
		
		else if(this.numberOfEntries < rOp.numberOfEntries)
		{
			return -1;
		}
		
		else
		{
			
			int i = numberOfEntries;
			int j = 1;
			this.reverse();
			rOp.reverse();
			
			while(i > 0)
			{
			
				if(this.getEntry(j).compareTo(rOp.getEntry(j)) > 0)
				{
					this.reverse();
					rOp.reverse();
					return 1;
				}
				
				else if(this.getEntry(j).compareTo(rOp.getEntry(j)) < 0)
				{
					this.reverse();
					rOp.reverse();
					return -1;
				}
				
				else
				j++;
				i--;
			
			}
			
			this.reverse();
			rOp.reverse();
			return 0;
			
		}
		
		
	}

	public boolean equals(Object rightOp)
	{
		return (this.compareTo((ReallyLongInt)rightOp) == 0);
	}

	public void multTenToThe(int num)
	{
		if(num > 0)
		{
		
			while(num > 0)
			{
				Integer zero = new Integer(0);
				add(1,zero);
				num--;
			}
		
		}
	}

	public void divTenToThe(int num)
	{
	
		if(num > 0)
		{
		leftShift(num);			
		}
		
	}
	
}
