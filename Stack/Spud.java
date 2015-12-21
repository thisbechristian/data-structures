/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 2
    Due Date:           February 14, 2014
    
    Description:        This class encapsulates all the data associated with the Potato Box orders, ex: the supplier, expiration date, and cost.
    					It contains the accessors, which give access to the expiration date, and the cost of each box.
    					Furthermore, it implements the Comparable interface and overrides the compareTo() method and does comparison via expiration date.
*/


public class Spud implements Comparable<Spud>
{
 
    private String supplier;
    private int expire;
    private double cost;
    
    public Spud(String s, int e, double c)
    {
        supplier = s;
        expire = e;
        cost = c;
    }
    
    public int getExpirationDate()
    {
        return expire;
    }
    
    public double getCost()
    {
        return cost;
    }
    
    public String toString()
    {
        StringBuilder S = new StringBuilder("Supplier: ");
        S.append(supplier);
        S.append(" Expires: ");
        S.append(expire);
        S.append(" Cost: ");
        S.append(cost);
        
        return S.toString();
    }
    
    public int compareTo(Spud s)
    {
        int n;
        
        if(this.getExpirationDate() < s.getExpirationDate())
        {
            n = -1;
        }
        
        else if(this.getExpirationDate() > s.getExpirationDate())
        {
            n = 1;
        }
        
        else
        {
            n = 0;
        }
        
        return n;
    }
   
}
