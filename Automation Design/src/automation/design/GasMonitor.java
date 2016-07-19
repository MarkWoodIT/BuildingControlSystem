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


public class GasMonitor extends Controller implements Runnable {
    
    private Thread gMonitorThread;
    private double gasCurrent;
    private String filename;

    GasMonitor(String s){
        super();
        this.filename = s;
    }
    public void run(){
        
       GasSensor gasSensor = new GasSensor (filename);
       gasSensor.startSensor();
       

       //Calculations
       //95% pressure alert
       double highGasAlert = (((Controller.GasThresHigh - Controller.GasThresLow)* (0.80)) + (Controller.GasThresLow));
       //5% pressure alert
       double lowGasAlert =  (((Controller.GasThresHigh - Controller.GasThresLow)* (0.20)) + (Controller.GasThresLow));
       
       while(true){
           
           gasCurrent = gasSensor.getSensorOutput();
           System.out.println(gasCurrent);
           
           try{
               Thread.sleep(250);
           }catch(InterruptedException e){
               
           }
           Controller.WarningLowGas = (Controller.GasThresLow < GasCurrent && lowGasAlert > gasCurrent) ? true : false;

           Controller.OpenLowGas = (Controller.GasThresLow >= gasCurrent) ? true : false;
           
           Controller.WarningHighGas = (Controller.GasThresHigh > gasCurrent && highGasAlert < GasCurrent)? true : false;
           
           Controller.ShutOffHighGas = (Controller.GasThresHigh <= gasCurrent)? true : false;
            

            // later do an interrupt() to exit
           
       }
       
       
       
       //System.out.println("HEY FUCK YOU " + Controller.WaterThresLow);
    }
    
    public void startMonitor(){
        this.gMonitorThread = new Thread (this, "Gas Monitor Thread");
        gMonitorThread.start();
        
        
    } 
}


