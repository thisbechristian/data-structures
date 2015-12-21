/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 4
    Due Date:           March 28, 2014
    
    Description:        Create a class called LinkedListPlus by extending the class A4LList and adding new functionality to it such as left/right shifting and rotating, and reversing.
    					These methods will be useful when implementing the next subclass ReallyLongInt. Once the LinkedListPlus is finished being implemented, the class called ReallyLongInt
    					by extending the class LinkedListPlus. This class will add the functionality of adding/comparing two operands, and multiplying/dividing an operand to a 10^n power.
*/

public class LinkedListPlus<T> extends A4LList<T>
{


public LinkedListPlus()
{
	clear();
}

public LinkedListPlus(LinkedListPlus<T> oldList)
{	

	clear();
	
	oldList.reverse();
	
	Node currentNode = oldList.firstNode;
	
	while(currentNode != null)
	{
		add(1,currentNode.getData());
		currentNode = currentNode.getNextNode();
	}
	
	oldList.reverse();
	
}


public void leftShift(int num)
{

	if(num > 0 && num < numberOfEntries)
	{
		firstNode = this.getNodeAt(num+1);
		numberOfEntries = (numberOfEntries-num);
	}
	
	else if(num >= numberOfEntries)
	{
		clear();
	}

}

public void rightShift(int num)
{

	if(num > 0 && num < numberOfEntries)
	{
		int n = (numberOfEntries - num);
		Node newLastNode = getNodeAt(n);
		newLastNode.setNextNode(null);
		numberOfEntries = (numberOfEntries-num);
		
	}
	
	else if(num >= numberOfEntries)
	{
		clear();
	}
	
}

public void leftRotate(int num)
{

	int n = num%numberOfEntries;
	
	if(n < 0)
	{
		rightRotate(n*-1);
	}
	
	else if(n > 0)
	{
		Node newLastNode = getNodeAt(n);
		Node newFirstNode = newLastNode.getNextNode();
		Node oldLastNode = getNodeAt(numberOfEntries);
		
		oldLastNode.setNextNode(firstNode);
		newLastNode.setNextNode(null);		
		firstNode = newFirstNode;
	}

}

public void rightRotate(int num)		
{
	int n = num%numberOfEntries;

	if(n < 0)
	{
		leftRotate(n*-1);
	}
	
	else if(n > 0)
	{
		Node newLastNode = getNodeAt(numberOfEntries-n);
		Node newFirstNode = newLastNode.getNextNode();
		Node oldLastNode = getNodeAt(numberOfEntries);
		
		oldLastNode.setNextNode(firstNode);
		newLastNode.setNextNode(null);
		firstNode = newFirstNode;	
	}
}

public void reverse()
{
	
	Node currentNode = firstNode;
	firstNode = null;
	
	while(currentNode != null)
	{
		Node nextNode = currentNode.getNextNode();
		
		currentNode.setNextNode(firstNode);
		firstNode = currentNode;
		
		currentNode = nextNode;
	}
	
}

private Node getNodeAt(int givenPosition)
{
	Node currentNode = firstNode;
	int i = 1;
	
	while(currentNode != null && i < givenPosition)
	{
		currentNode = currentNode.getNextNode();
		i++;
	}

	return currentNode;
}

public String toString()
{
	StringBuilder S = new StringBuilder();
	Node currentNode = firstNode;
	
	while(currentNode != null)
	{
		S.append(currentNode.getData().toString() + " ");
		currentNode = currentNode.getNextNode();
	}
	
	return S.toString();
}


}