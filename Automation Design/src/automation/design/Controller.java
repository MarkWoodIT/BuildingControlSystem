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
public class Controller {
    
    protected static double WaterThresLow;
    protected static double WaterThresHigh;
    protected static double WaterCurrent;
    protected static double WaterSensor;
    protected static double GasThresLow;
    protected static double GasThresHigh;
    protected static double GasCurrent;
    protected static double GasSensor;
    protected static double TempThresLow;
    protected static double TempThresHigh;
    protected static double TempCurrent;
    protected static double TempSensor;
    
    protected static boolean WarningHighWater;
    protected static boolean ShutOffHighWater;
    protected static boolean WarningLowWater;
    protected static boolean OpenLowWater;
    
    protected static boolean WarningHighGas;
    protected static boolean ShutOffHighGas;
    protected static boolean WarningLowGas;
    protected static boolean OpenLowGas;
    
    protected static boolean WarningHighTemp;
    protected static boolean TurningOnAC;
    protected static boolean WarningLowTemp;
    protected static boolean TurningOnHeat;
    
    
    
    Controller(double wLow, double wHigh, double gLow, double gHigh, double tLow, double tHigh){

        this.WaterThresLow = wLow;
        this.WaterThresHigh = wHigh;
        this.WaterCurrent = 142.0;
        this.WaterSensor = 140;
        this.GasThresLow = gLow;
        this.GasThresHigh = gHigh;
        this.GasCurrent = 200.0;
        this.GasSensor = 200.0;
        this.TempThresLow = tLow;
        this.TempThresHigh = tHigh;
        this.TempCurrent = 1.0; 

        this.WarningHighWater = false;
        this.ShutOffHighWater = false;
        this.WarningLowWater = false;
        this.OpenLowWater = false;
        
        this.WarningHighGas = false;
        this.ShutOffHighGas = false;
        this.WarningLowGas = false;
        this.OpenLowGas = false;
        
        this.WarningHighTemp = false;
        this.TurningOnAC = false;
        this.WarningLowTemp = false;
        this.TurningOnHeat = false;
        
    }     

    Controller(){
        this(100.0, 150.0, 150.0, 250.0, 2.0, 10.0);
    }

    private static void waterAlert(){
        
        String warning = "Water level normal";
        if (WarningHighWater) warning = "High water warning, water pressure > 95%!";
        if (ShutOffHighWater) warning = "Water bypass valve opened";
        if (WarningLowWater) warning = "Low water warning, water pressure < 5%";
        if (OpenLowWater) warning = "Secondary valve opened";

        //if (WarningHighWater || ShutOffHighWater || WarningLowWater || OpenLowWater){
            System.out.println(warning);
        //}

    }
    
    private static void gasAlert(){
        
        String warning = "Gas level normal";
        if (WarningHighGas) warning = "High gas warning, gas pressure > 80%!";
        if (ShutOffHighGas) warning = "Gas bypass valve opened";
        if (WarningLowGas) warning = "Low gas warning, gas pressure < 20%";
        if (OpenLowGas) warning = "Additonal gas valve opened";

        //if (WarningHighWater || ShutOffHighWater || WarningLowWater || OpenLowWater){
            System.out.println(warning);
             
        //}        
        
        
    }
        
    public void startController(){
        // do a this(params) call later
        
        Thread waterAlertThread = new Thread() {
            public void run(){
                try{
                    while (true){
                        Controller.waterAlert();
                        Thread.sleep(500);
                    } 
                } catch(InterruptedException e) {
                
                }

                
            }
        };
        
        Thread gasAlertThread = new Thread() {
            public void run(){
                try{
                    while (true){
                        Controller.gasAlert();
                        Thread.sleep(250);
                    }
                } catch(InterruptedException e){
                    
                }
                
                
                
                
            }
        };
        
        waterAlertThread.start();
        gasAlertThread.start();
    }
        
        /*while (WaterOn == true) {
            if(WaterCurrent < WaterThresHigh && WaterCurrent > WaterThresLow){
                WaterCurrent = WaterSensor;
            }
            if(WaterCurrent > WaterThresHigh){
                System.out.println("Water Pressure over threshold, bypass valve opened");
            }
            if(WaterCurrent < WaterThresLow){
                System.out.println("Water Pressure under threshold, second pipe opened"); 
            }
         
        }*/
        
 
        

}
