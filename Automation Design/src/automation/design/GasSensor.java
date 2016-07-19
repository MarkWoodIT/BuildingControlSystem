/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automation.design;

import java.io.*;
import java.util.Scanner;



public class GasSensor implements Sensor, Runnable  {
    
    private Thread gasThread;
    private double output;
    private String filename;
    
    GasSensor(String s){
        
    this.filename = s;    
        
    }
   
    
    public void run(){
    
        Scanner s;
        try
        {
            s = new Scanner(new FileReader(filename));
            
            try
            {
                System.out.println("reading data: ");
                //output = (double) inputStream.read();
                while(s.hasNext()){
                    if (s.hasNextDouble()){
                        output = s.nextDouble();
                        //System.out.println(output);
                        Thread.sleep(500);
                    } else{
                        s.next();
                    }
                    
                }
                
            } 
            catch(InterruptedException e){

            } finally {
                s.close();
            }
        } catch(IOException e){

        } 

    }
    
    
    public void startSensor(){
        
        //readData(filename);
        gasThread = new Thread(this, "Gas Thread");
        gasThread.start();
        
        
    }
    
    public void stopSensor(){
        
        
    }
    
    public double getSensorOutput(){
        
        return output;
        
    }


}
