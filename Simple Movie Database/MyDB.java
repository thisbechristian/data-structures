/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 1
    Due Date:           January 29, 2014
    
    Description:        Write a program (Assig1B.java& Assig1C.java *extra credit*)that acts as a driver for the database class (MyDB.java) while using the class
    					Movie (Movie.java) as the database type. Furthermore, the database class (MyDB.java) should implement the SimpleDataBase Interface,
    					and the rest of the interfaces given. The database class should also have the functionality to add,remove,find,sort,reverse sort,
    					and restore/save the database to a file. This functionality should be executed in the driver program(s).  
    					Finally, it is required for the movie class (Movie.java) to implement the interfaces "Comparable" and "Serializable".
*/

import java.util.*;
import java.io.*;

public class MyDB<T> implements SimpleDB<T>,Reverser,Sorter,SaveRestore
{
    private int concreteLength;
    private int logicalLength;
    private int foundItemLocation;
    private T[] data;
    
    public MyDB(int l)
    {
        logicalLength = 0;
        concreteLength = l;
        data = (T[])new Object[l];
    }
    
    public boolean addItem(T item)
    {
        if(logicalLength < concreteLength)
        {
        data[logicalLength] = item;
        logicalLength++;
        
        }
        
        else
        {
            T [] temp = (T[]) new Object[concreteLength*2];
            
            System.out.println("\nResizing the array to " + concreteLength*2);
            
            for(int i = 0; i < concreteLength; i++)
            {
                temp[i] = data[i];
            }
            
            temp[logicalLength] = item;
            logicalLength++;
            concreteLength = concreteLength*2;
            data = temp;
            
        }
        
        return true;
        
    }
    
    public T removeItem(T item)
    {
        T temp = findItem(item);
        
        if(temp != null)
        {
            data[foundItemLocation] = null;
            
            for(int i = foundItemLocation; i < logicalLength; i++)
            {
                data[i] = data[i+1];
            }
            
            logicalLength--;
            
            return temp;
        }
        
        else
            return null;
        
    }
    
    public T findItem(T item)
    {
        int i = 0;
        
        while(i < logicalLength)
        {
            if(data[i].equals(item))
            {
                foundItemLocation = i;
                return data[i];
            }
            
            i++;
            
        }
        
        return null;
        
    }
    
    public boolean isFull()
    {
        if(logicalLength == concreteLength)
            return true;
        else
            return false;
    }
    
    public boolean isEmpty()
    {
        if(logicalLength == 0)
            return true;
        else
            return false;
        
    }
    
    public int size()
    {
        return logicalLength;
    }
    
    public void clear()
    {
        logicalLength = 0;
        data = (T[])new Object[concreteLength];
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder("\nHere is the database:");
        
        for(int i = 0; i < logicalLength; i++)
        {
            s.append("\n" + data[i].toString());
        }
        
        return(s.toString());
        
    }
    
    public void sort()
    {
        Arrays.sort(data, 0, logicalLength);
    }
    
    public void reverse()
    {
        T temp;
        int j = 0;
        int i = (logicalLength-1);
        
        while(true)
        {
            if(i != j && j < i)
            {
            temp = data[i];
            data[i] = data[j];
            data[j] = temp;
            
            i--;
            j++;
            }
            
            else
            break;
        }
        
        
    }
    
    public boolean saveToFile(String f)
    {
        String filename = new String(f);
        
        ObjectOutputStream os;
        
        try
        {
        os = new ObjectOutputStream(new FileOutputStream(filename));
        
        for(int i = 0; i < logicalLength; i++)
        {
        os.writeObject(data[i]);
        }
        
        os.close();
        
        return true;
        }
        
        catch(IOException e)
        {
        return false;
        }
        
        catch(Exception ex)
        {
        return false;  
        }
             
    }
    
     public boolean restoreFromFile(String f)
    {
        String filename = new String(f);
        this.clear();
        concreteLength = 20;
        data = (T[])new Object[concreteLength];
        int i = 0;
        
        try
        {
         ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename)); 

         while(true)
         {    
             try
             {
                 Object nextObject = is.readObject();
                 this.addItem((T)nextObject);
                 i++;
             }

             catch(ClassNotFoundException e1)
             {}

             catch (EOFException e2)
             {
                 logicalLength = i;
                 is.close();
                 break;
             }
         }
        
         return true;
         
        }
        
        catch(FileNotFoundException e1)
        {
            return false;
        }
        
        catch(IOException e2)
        {
            return false;
        }
        
                
    }
    
    
}
