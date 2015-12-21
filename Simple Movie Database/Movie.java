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
import java.text.*;
import java.io.*;
import java.text.*;

public class Movie implements Comparable, Serializable
{
    private String title;
    private Date releasedate;
    private int min;
    private double gross;
    
    public Movie(String t, String d, int m, double g)
    {
        title = new String(t);
        
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		try 
		{
			releasedate = df.parse(d);
		}
		catch (ParseException e)
		{
			releasedate = null;
		}
                
                min = m;
                gross = g;
        
    }
    
    public Movie(String t)
    {
        title = new String(t);
    }
    
    public String toString()
    {
        NumberFormat f = NumberFormat.getCurrencyInstance();
        String gr = f.format(gross);
        
        StringBuilder s = new StringBuilder("\nMovie Title: ");
        s.append(title);
        s.append(" Release Date: ");
        s.append(releasedate);
        s.append(" Running Time: ");
        s.append(min);
        s.append(" minutes ");
        s.append("Gross: ");
        s.append(gr);
        
        return(s.toString());
        
    }
    
    public boolean equals(Object O)
    {
        Movie M = (Movie)O;
        if(this.title.toLowerCase().equals(M.getTitle().toLowerCase()))
            return true;
        else
            return false;
        
    }
    
    
    public String getTitle()
    {
        return title;
    }

    @Override
    public int compareTo(Object O)
    {
    Movie M = (Movie)O;
    int i = 0;
    int l = title.length();
    boolean flag = false;

    if(this.equals(M))
    {
      return 0;    
    }

    else if(title.charAt(0) < M.getTitle().charAt(0))
    {
        return -1;
    }

    else
        return 1;

    }
 

}
