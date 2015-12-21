/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 3
    Due Date:           March 4, 2014
    
    Description:        Re-write the database class (MyDB.java) using recursion to implement the methods that previously used iterative loops.
    					Furthermore, the database class (MyDB.java) should implement the SimpleDataBase Interface,
    					and the rest of the interfaces given. The database class should also have the functionality to add,remove,find,sort,reverse sort,
    					and restore/save the database to a file. This functionality should be executed in the driver program(s).
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
            
            resizeArray(temp, 0);
            
            temp[logicalLength] = item;
            logicalLength++;
            concreteLength = concreteLength*2;
            data = temp;
            
        }
        
        return true;
    }
    
    private void resizeArray(T[] temp, int index)
    {
    
    	if(index == (concreteLength-1))
    	{
    		temp[index] = data[index];	
 	   	}
    
    	else
    	{
    		temp[index] = data[index];
    		index++;
    		resizeArray(temp, index);
   		}
   	 }
    
    public T removeItem(T item)
    {
        T temp = findItem(item);
        
        if(temp != null)
        {
            data[foundItemLocation] = null;
            removeItem(foundItemLocation);
            logicalLength--;
            return temp;
            
        }
        
        else
            return null;
        
    }
    
    private void removeItem(int index)
    {
    
    	if(index == (logicalLength-1))
		{
			data[index] = data[index+1];
		}
		
		else
		{
			data[index] = data[index+1];
			index++;
			removeItem(index);
		}
    
    }
    
    public T findItem(T item)
    {
        
        T search = findItem(item, 0);
		return search;
		
    }
    
    private T findItem(T item, int index)
    {
		
		if(index == logicalLength)
		return null;
		
		else
		{
			if(data[index].equals(item))
			{
				foundItemLocation = index;
				return data[index];
			}
			
			else
			{
			index++;
			return  findItem(item, index);
			
			}
		
		}
	    
    }
    
    public boolean isFull()
    {
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
        return(toString(s, 0));
        
    }
    
    private String toString(StringBuilder str, int index)
    {
    	if(index == (logicalLength-1))
    	{
    		str.append("\n" + data[index].toString());
    		return str.toString();
    	}
    	
    	else
    	{
    		str.append("\n" + data[index].toString());
    		index++;
    		return toString(str, index);
    	}
    
    
    }
    
    public void sort()
    {
        Arrays.sort(data, 0, logicalLength);
    }
    
    public void reverse()
    {
        reverse(logicalLength-1,0);   
    }
    
    private void reverse(int i, int j)
    {
    	if(i != j && j < i)
    	{
    		swap(i,j);
    		i--;
        	j++;
    	
    		reverse(i,j);
    	}
    }
    
    private void swap(int i, int j)
    {
    	T temp;
    	temp = data[i];
    	data[i] = data[j];
        data[j] = temp;
    }
    
    
    public boolean isSorted()
    {
    	return isSorted(false, 0);
    }
    
    private boolean isSorted(boolean sorted, int index)
    {
    
    	if(index < (logicalLength-1))
    	{
    		Comparable C1 = (Comparable)data[index];
    		Comparable C2 = (Comparable)data[index + 1];
    		
    		if(C1.compareTo(C2) > 0)
    		{
    			return false;
    		}
    		
    		else
    		{
    			index++;
    			return isSorted(true, index);
    		}
    	
   	 	}
    
    	else
   		{
    		return sorted;
    	}
    
    }
    
    public void showMode()
    {
    	int[] freq = new int[logicalLength];
    	
    	getFrequencies(freq, 0);
    	
    	int mode = getMax(freq, 0, 0);
    	
    	System.out.println("The mode is:	Data: " + data[mode] +"	Freq: " + freq[mode]);
    	
    }
    
    private void getFrequencies(int[] frequencies, int index)
    {
    	if(index < logicalLength)
    	{
    	
    	int f = getFrequency(data[index], 0, 0);
    	frequencies[index] = f;
    	
    	index++;
    	
    	getFrequencies(frequencies, index);
    	
    	}
    }
    
    private int getFrequency(T item, int index, int freq)
    {
    	if(index < logicalLength)
    	{
    	
    		if(data[index].equals(item))
    		{
    			freq++;
    		}
    	
    		index++;
    		return getFrequency(item,index,freq);
    	
    	}
    	
    	else
    	return freq;
    
    }
    
    private int getMax(int[] A, int index, int max)
    {
    	if(index < (logicalLength-1))
    	{
    		if(A[max] < A[index+1])
    		{
    			max = (index+1); 
    		}
    		
    		index++;
    		return getMax(A, index, max);
    	}
    	
    	else
    	return max;
    }
    
    public boolean saveToFile(String f)
    {
        String filename = new String(f);
        
        ObjectOutputStream os;
        
        try
        {
        os = new ObjectOutputStream(new FileOutputStream(filename));
        
        boolean save = saveToFile(os,0);
        
        os.close();
        
        return save;
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
    
    public boolean saveToFile(ObjectOutputStream os, int index)
    {
    	if(index < logicalLength)
    	{
    		try
    		{
    			os.writeObject(data[index]);
    			index++;
    			return saveToFile(os, index);
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
    	
    	else
    	return true;
    
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
         return restoreFromFile(is, 0, true);

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
    
    public boolean restoreFromFile(ObjectInputStream is, int i, boolean run)
    {
    
    if(run == true)
    {
   		try
        {
            Object nextObject = is.readObject();
            this.addItem((T)nextObject);
            i++;
        	run = true;
    	}

    	catch(ClassNotFoundException e1)
        {}

    	catch (EOFException e2)
        {
	        logicalLength = i;
	        run = false;
	        
	        try
	        {
            is.close();
            }
            
            
            catch(Exception e)
        	{}
        	
        	return true;
	    }
	    
	    catch(Exception e3)
        {}
	    
    	return restoreFromFile(is, i, run);
    		
    }
    
    else
    return false;
    
    }
    
    
}
