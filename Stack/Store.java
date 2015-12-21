/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 2
    Due Date:           February 14, 2014
    
    Description:       	This store class contains methods which each correspond to a command which will be written in the text file.
    					These methods will be called by an object of this class created in the driver (Assig2.java).
    					
*/

public class Store
{

private int date;
private int moves;
private double moveCost;
private double totalMoveCost;
private int totalMoves;
private int mostRecentBoxCount;
private int totalBoxCount;
private double mostRecentBoxCost;
private double totalBoxCost;
private double mostRecentSum;
private double totalSum;
private StackInterface<Spud> stack;
private StackInterface<Spud> tempStack;

public Store()
{
    date = 0;
    mostRecentBoxCount = 0;
    totalBoxCount = 0;
    mostRecentBoxCost = 0;
    totalBoxCost = 0;
    moveCost = 0;
    totalMoveCost = 0;
    mostRecentSum = 0;
    totalSum = 0;
    stack = new ArrayStack<Spud>(10);
    
}


public void preReceive(int n)
{
    System.out.println("Processing shipment of " + n + " boxes of potatoes\n");
    tempStack = new ArrayStack<Spud>(10);
    mostRecentBoxCount = 0;
    mostRecentBoxCost = 0;
    moves = 0;
}

public void receive(String s, int e, double c)
{
    
    Spud tempSpud = new Spud(s,e,c);
    Spud tempSpud2;
        
    if(tempSpud.getExpirationDate() >= date)
        {
            if(!stack.isEmpty())
            {
            	if(stack.peek().compareTo(tempSpud) >= 0)
            	{
            	stack.push(tempSpud);
                moves++;
            	}
            	
            	else if(stack.peek().compareTo(tempSpud) < 0)
            	{
            	
            	while(!stack.isEmpty() && stack.peek().compareTo(tempSpud) < 0)
            	{
            	tempSpud2 = stack.pop();
            	tempStack.push(tempSpud2);
            	moves++;
            	}
            	
            	stack.push(tempSpud);
                moves++;
                
                /*
                while(!tempStack.isEmpty())
            	{
            	tempSpud2 = tempStack.pop();
            	stack.push(tempSpud2);
            	moves++;
            	}
            	*/
            	
            	}
            
            }
            else
            {
                stack.push(tempSpud);
                moves++;
            }
            
            mostRecentBoxCount++;
            mostRecentBoxCost = mostRecentBoxCost + c;
        }
    else
        {
            System.out.println("The box: " + tempSpud.toString() + " is expired!\n");
        }
    
            
}

public void postReceive()
{
    System.out.println("Added " + mostRecentBoxCount + " boxes to the stack\n");
    totalBoxCost = totalBoxCost + mostRecentBoxCost;
    totalBoxCount = totalBoxCount + mostRecentBoxCount;
    
    Spud tempSpud;
    Spud tempSpud2;
    
    while(!tempStack.isEmpty())
    {
        tempSpud = tempStack.pop();
            	
        if(stack.peek().compareTo(tempSpud) >= 0)
        {
            stack.push(tempSpud);
            moves++;
        }
            	
        else if(stack.peek().compareTo(tempSpud) < 0)
        {
            	
    	  	while(!stack.isEmpty() && stack.peek().compareTo(tempSpud) < 0)
            {
            	tempSpud2 = stack.pop();
            	tempStack.push(tempSpud2);
            	moves++;
            }
            	
        	stack.push(tempSpud);
        	moves++;
            	
        }
            	
    }
    
    totalMoves = totalMoves + moves;
    
}


public void use(int n)
{
    System.out.println(n + " boxes of potatoes are needed\n");
    
    int i = 0;
    Spud tempSpud;
    
    if(!stack.isEmpty())
    {
        
    do
    {
    
    tempSpud = stack.pop();
    
    if(tempSpud.getExpirationDate() >= date)
    {
        System.out.println("Getting box: " + tempSpud.toString() + " from the stack\n");
        i++;
    }
    
    else
    {
            System.out.println("The box: " + tempSpud.toString() + " is expired!, Thrown out!\n");
    }
    
    }while(i < n);
    
    }
    
    else
        System.out.println("No potatoes left -- cannot complete order!\n");
        
}

public void display()
{
    if(!stack.isEmpty())
    {
    System.out.println("Here are your boxes (top to bottom):\n");
    System.out.println(stack.toString());
    }
    else
    System.out.println("No boxes in the stack - please reorder!\n");
}

public void skip()
{
    date++;
    System.out.println("The current day is now Day " + date + "\n");
    
    Spud tempSpud;
    tempStack = new ArrayStack<Spud>(10);
    
    while(!stack.isEmpty())
    {
        tempSpud = stack.pop();
        
        if(tempSpud.getExpirationDate() >= date)
        {
            tempStack.push(tempSpud);
        }
        
        else
        {
            System.out.println("Top box: " + tempSpud.toString() + " is expired!\n");
        }
    }
   
    while(!tempStack.isEmpty())
    {
        tempSpud = tempStack.pop();
        stack.push(tempSpud);
    }
    
    
}

public void report()
{
    moveCost = moves;
    totalMoveCost = totalMoves;
    mostRecentSum = mostRecentBoxCost + moveCost;
    totalSum = totalBoxCost + totalMoveCost;
    
    System.out.println("\nSpuds Spuds and Spuds Semi-Irregular Report: \n");
    System.out.println("\tMost Recent Shipment:\n");
    System.out.println("\t\tBoxes: " + mostRecentBoxCount + "\n");
    System.out.println("\t\tPotato cost: " + mostRecentBoxCost + " \n");
    System.out.println("\t\tLabor (moves): " + moves + " \n");
    System.out.println("\t\tLabor cost: " + moveCost + " \n");
    System.out.println("\t\t------------------------");
    System.out.println("\t\tTotal: " + mostRecentSum + " \n\n");
    System.out.println("\n\tOverall Expenses:\n");
    System.out.println("\t\tBoxes: " + totalBoxCount + "\n");
    System.out.println("\t\tPotato cost: " + totalBoxCost + " \n");
    System.out.println("\t\tLabor (moves): " + totalMoves + " \n");
    System.out.println("\t\tLabor cost: " + totalMoveCost + " \n");
    System.out.println("\t\t------------------------");
    System.out.println("\t\tTotal: " + totalSum + " \n\n");

}

public void quit()
{
    System.out.println("End of the simulation \n");
}

}