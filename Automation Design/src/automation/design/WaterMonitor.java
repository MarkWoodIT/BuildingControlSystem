/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automation.design;

/**
 *
 * @author Mark
 */


public class WaterMonitor extends Controller implements Runnable {
    
    private Thread wMonitorThread;
    private double waterCurrent;
    private String filename;

    WaterMonitor(String s){
        super();
        this.filename = s;
    }
    public void run(){
        
       WaterSensor waterSensor = new WaterSensor (filename);
       waterSensor.startSensor();
       

       //Calculations
       //95% pressure alert
       double highWaterAlert = (((Controller.WaterThresHigh - Controller.WaterThresLow)* (0.95)) + (Controller.WaterThresLow));
       //5% pressure alert
       double lowWaterAlert =  (((Controller.WaterThresHigh - Controller.WaterThresLow)* (0.05)) + (Controller.WaterThresLow));
       
       while(true){
           
           waterCurrent = waterSensor.getSensorOutput();
           System.out.println(waterCurrent);
           
           try{
               Thread.sleep(500);
           }catch(InterruptedException e){
               
           }
           Controller.WarningLowWater = (Controller.WaterThresLow < waterCurrent && lowWaterAlert > waterCurrent) ? true : false;

           Controller.OpenLowWater = (Controller.WaterThresLow >= waterCurrent) ? true : false;
           
           Controller.WarningHighWater = (Controller.WaterThresHigh > waterCurrent && highWaterAlert < waterCurrent)? true : false;
           
           Controller.ShutOffHighWater = (Controller.WaterThresHigh <= waterCurrent)? true : false;
            

            // later do an interrupt() to exit
           
       }
       
       
       
       //System.out.println("HEY FUCK YOU " + Controller.WaterThresLow);
    }
    
    public void startMonitor(){
        this.wMonitorThread = new Thread (this, "Water Monitor Thread");
        wMonitorThread.start();
        
        
    } 
}


