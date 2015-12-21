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


import java.io.*;
import java.util.*;

public class Assig1B {

    public static void main(String[] args)
    {
        MyDB<Movie> DB = new MyDB<Movie>(10);
        Scanner s = new Scanner(System.in);
        boolean run = true;
        int operation = 0;
        
        System.out.println("\nWelcome to the Movie database!");
        
        while(run)
        {
            System.out.println("\nWhat would you like to do?\n1) Restore Movie from file\n2) Add new Movie\n3) Find a Movie\n4) Delete a Movie\n5) Sort Movies\n6) Reverse Sort Movies\n7) Save Movies to File\n8) Quit");
            operation = s.nextInt();
            s.nextLine();
            
            if(operation == 1)
            {
                String filename;
                System.out.println("\nPlease Enter the filename you wish to retrieve the movie database from: ");
                filename = s.nextLine();
                boolean result = DB.restoreFromFile(filename);
                
                if(result)
                {
                   System.out.println("\nYou have successfully restored the database!");
                }
                
                else if(!result)
                {
                   System.out.println("\nError: File does not exist.");
                }
                
            }
            
            else if(operation == 2)
            {
                String title;
                String date;
                int min;
                double gross;
                
                System.out.println("\nPlease Enter the title of the movie: ");
                title = s.nextLine();
                System.out.println("\nPlease Enter the release date of the movie (MM/DD/YYYY): ");
                date = s.nextLine();
                System.out.println("\nPlease Enter the length of the movie in minutes:");
                min = s.nextInt();
                s.nextLine();
                System.out.println("\nPlease Enter the gross of the movie: ");
                gross = s.nextDouble();
                s.nextLine();
                
                Movie M = new Movie(title,date,min,gross);
                if(DB.findItem(M) == null)
                {
                    DB.addItem(M);
                    System.out.println("\nMovie added to the database.");
                }
                else
                {
                System.out.println("\nError: Movie already exists in database!"); 
                }
                
            }
            
            else if(operation == 3 || operation == 4)
            {
                String title;
             
                if(!DB.isEmpty())
                {
                
                if(operation == 3)
                {
                System.out.println("\nPlease Enter the title of the movie you wish to search for: "); 
                }
                
                else if(operation == 4) 
                {    
                System.out.println("\nPlease Enter the title of the movie you wish to remove: ");
                }
                
                title = s.nextLine();
                Movie temp = new Movie(title);
                
                if(DB.findItem(temp) != null)
                {
                    Movie M = DB.findItem(temp);
                    System.out.println("\nMovie Found: " + M.toString());
                    
                    if(operation == 4)
                    {
                    DB.removeItem(M);
                    System.out.println("\nMovie Removed!");
                    }
                    
                }
                else
                {
                System.out.println("\nError: Movie was not found!"); 
                }
                
                }
                
                else
                System.out.println("\nError: Movie database is empty!"); 
                
            }
            
            else if(operation == 5)
            {
                if(!DB.isEmpty())
                {
                DB.sort();
                System.out.println("\nMovies Sorted.\n");
                System.out.println(DB.toString());
                }
                
                else
                System.out.println("\nError: Movie database is empty!"); 
            }
            
            else if(operation == 6)
            {
                if(!DB.isEmpty())
                {
                DB.reverse();
                System.out.println("\nMovies Reversed.\n");
                System.out.println(DB.toString());
                }
                
                else
                System.out.println("\nError: Movie database is empty!");
            }
            
            if(operation == 7)
            {
                String filename;
                System.out.println("\nPlease Enter the filename you wish to save the movie database to: ");
                filename = s.nextLine();
                boolean result = DB.saveToFile(filename);
                
                if(result)
                {
                   System.out.println("\nYou have successfully saved the database!");
                }
                
                else if(!result)
                {
                   System.out.println("\nError: Could Not Save File.");
                }
                
            }
            
            else if(operation == 8)
            {
                 String quit;
                 System.out.println("\nAre you sure you want to quit? (yes/no)");
                 quit = s.nextLine();
                 
                 if(quit.toLowerCase().equals("yes"))
                 {
                 run = false;
                 }
                 
            }
            
            
            
        }
        
        
    }
}
