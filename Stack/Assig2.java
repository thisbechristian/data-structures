/*
    Name:               Christian Boni
    Course:             CS 0445, T/R 2:30PM
    Project:            Assignment 2
    Due Date:           February 14, 2014
    
    Description:        Create a driver for Assignment 2 that scans in a file. The file contains a command on each line
    					(except a few where data follows) as text which must be scanned into the String variable "Command"
    					which is tested for equality in several different if statements. Where each if statement calls
    					a particular method in the Store class. 
*/


import java.io.*;
import java.util.*;

public class Assig2 {

    public static void main(String[] args)
    {
        Store SpudStore = new Store();
        String fname;
        String Command = "";
        boolean wrongfile = false;
        
        Scanner S = new Scanner(System.in);
        
        do
        {
            System.out.println("Please enter the filename: ");
            fname = S.nextLine();
            
            try
            {
                Scanner read = new Scanner(new File(fname));
                
                System.out.println("Starting simulation on Day 0 \n");
                
                wrongfile = false;

                while(true)
                {

                    Command = read.nextLine();

                    if(Command.toLowerCase().equals("quit"))
                    {
                        SpudStore.quit();
                        break;
                    }
                    
                    else if(Command.toLowerCase().equals("report"))
                    {
                        SpudStore.report();
                    }
                    
                    else if(Command.toLowerCase().equals("skip"))
                    {
                        SpudStore.skip();
                    }
                    
                    else if(Command.toLowerCase().equals("display"))
                    {
                        SpudStore.display();
                    }
                    
                    else if(Command.toLowerCase().equals("use"))
                    {
                        String num = read.nextLine();
                        int n = Integer.parseInt(num);
                        SpudStore.use(n);
                    }
                    
                    else if(Command.toLowerCase().equals("receive"))
                    {
                        String num = read.nextLine();
                        int n = Integer.parseInt(num);
                        
                        SpudStore.preReceive(n);
                        
                        for(int i = 0; i < n; i++)
                        {
                            String info = read.nextLine();
                            
                            String[] information = info.split(":");
                            
                            String sup = information[0];
                            int exp = Integer.parseInt(information[1]);
                            double cost = Double.parseDouble(information[2]);
                            
                            SpudStore.receive(sup,exp,cost);
                        }
                        
                        SpudStore.postReceive();
                    }

                }
            }
            
            catch(FileNotFoundException e)
            {
                System.out.println("Wrong Filename, Try Again");
                wrongfile = true;
            }
            
        }while(wrongfile);
        
        
    }
}
