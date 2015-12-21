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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Assig1C
{
    
    private MyDB<Movie> DB;
    private Movie temp;
    private JFrame theWindow;
    private JPanel controlPanel,infoPanel;
    private JButton addMovie,removeMovie,restoreMovie,saveMovie,findMovie,sortMovie,rsortMovie,quit;
    private JTextArea info;
    private Container thePane;
    private Listener theListener;
    
    public Assig1C()
    {
        
        DB = new MyDB<Movie>(10);
      
        theWindow = new JFrame("Assigment 1: Movie DataBase");
        theWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        controlPanel = new JPanel();
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        controlPanel.setLayout(new GridLayout(2,4));
        
        restoreMovie = new JButton("Restore Movie DB from File");
        addMovie = new JButton("Add Movie");
        findMovie = new JButton("Find Movie");
        removeMovie = new JButton("Remove Movie");
        sortMovie = new JButton("Sort Movies");
        rsortMovie = new JButton("Reverse Sort Movies");
        saveMovie = new JButton("Save Movie DB to File");
        quit = new JButton("Quit");
        
        theListener = new Listener();
                
        addMovie.addActionListener(theListener);
        removeMovie.addActionListener(theListener); 
        restoreMovie.addActionListener(theListener);
        saveMovie.addActionListener(theListener);
        findMovie.addActionListener(theListener);
        sortMovie.addActionListener(theListener);
        rsortMovie.addActionListener(theListener);
        quit.addActionListener(theListener);
        
        controlPanel.add(restoreMovie);
        controlPanel.add(addMovie);
        controlPanel.add(findMovie);
        controlPanel.add(removeMovie);
        controlPanel.add(sortMovie);
        controlPanel.add(rsortMovie);
        controlPanel.add(saveMovie);
        controlPanel.add(quit);
        
        info = new JTextArea(25,80);
        info.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(info);
        infoPanel.add(scroll);
        
        thePane = theWindow.getContentPane();
        thePane.add(infoPanel, BorderLayout.NORTH);
        thePane.add(controlPanel, BorderLayout.SOUTH);
        
        theWindow.pack();
        theWindow.setVisible(true);
        
    }
    
    private class Listener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent e)
        {
            Component theSource = (Component)e.getSource();
            
            if(theSource == addMovie)
            {
                 String s1 = "Please Enter the title of the movie:";
                 String t = JOptionPane.showInputDialog(theWindow, s1);
                 String s2 = "Please Enter the release date of the movie (MM/DD/YYYY):";
                 String d = JOptionPane.showInputDialog(theWindow, s2);
                 String s3 = "Please Enter the length of the movie in minutes:";
                 String min = JOptionPane.showInputDialog(theWindow, s3);
                 String s4 = "Please Enter the gross of the movie:";
                 String gr = JOptionPane.showInputDialog(theWindow, s4);
                 
                 if(t != null && d != null && min != null && gr != null)
                 {
                     int m = Integer.parseInt(min);
                     double g = Double.parseDouble(gr);

                     Movie M = new Movie(t,d,m,g);

                     if(DB.findItem(M) == null && M != null)
                     {
                         DB.addItem(M);
                         info.append("\n\nMovie added to the database.");
                     }
                     else if(DB.findItem(M) != null)
                     info.append("\n\nError: Movie already exists in database!");
                 }

            }
            
            else if(theSource == findMovie || theSource == removeMovie)
            {
                
                if(!DB.isEmpty())
                {
                    String s1 = "Please Enter the title of the movie:";
                    String t = JOptionPane.showInputDialog(theWindow, s1);
                    
                    if(t != null)
                    {
                    temp = new Movie(t);

                    if(DB.findItem(temp) != null)
                    {
                        Movie M = DB.findItem(temp);
                        info.append("\n\nMovie Found: " + M.toString()); 

                        if(theSource == removeMovie)
                        {
                            DB.removeItem(M);
                            info.append("\n\nMovie Removed!");
                        }

                    }

                    else
                        info.append("\n\nMovie not Found!");
                    
                    }

                 }

                 else
                 info.append("\n\nError: Movie database is empty!");  

            }
            
            else if(theSource == sortMovie)
            {
                if(!DB.isEmpty())
                {
                DB.sort();
                info.append("\n\nMovies Sorted.");
                info.append(DB.toString());
                }
                
                else
                info.append("\n\nError: Movie database is empty!"); 
            }
            
            else if(theSource == rsortMovie)
            {
                if(!DB.isEmpty())
                {
                DB.sort();
                info.append("\n\nMovies Reversed.");
                info.append(DB.toString());
                }
                
                else
                info.append("\n\nError: Movie database is empty!"); 
            }
            
            else if(theSource == restoreMovie)
            {
            
                String s1 = "Please Enter the filename you wish to retrieve the movie database from:\n(Warning: This will clear the current Movie Database!)";
                String fn = JOptionPane.showInputDialog(theWindow, s1);
                
                if(fn != null && DB.restoreFromFile(fn))
                {
                    info.append("\n\nYou have successfully restored the database!");
                }
                
                else if(fn != null)
                    info.append("\n\nError: File does not exist.");
                
            }
            
            else if(theSource == saveMovie)
            {
            
                String s1 = "Please Enter the filename you wish to save the movie database to: ";
                String fn = JOptionPane.showInputDialog(theWindow, s1);
                
                if(fn != null && DB.saveToFile(fn))
                {
                    info.append("\n\nYou have successfully saved the database!");
                }
                
                else if(fn != null)
                    info.append("\n\nError: Could not save the file.");
                
            }
            
            else if(theSource == quit)
            {
                String s1 = "Are you sure you want to quit the program? (yes/no)";
                String ans = JOptionPane.showInputDialog(theWindow, s1);
                
                if(ans != null && ans.toLowerCase().equals("yes"))
                {
                    System.exit(0);
                }
                
            }
            
        }
        
    }
    
    public static void main(String [] arg)
    {
        Assig1C A = new Assig1C();
    }
    
    
}
