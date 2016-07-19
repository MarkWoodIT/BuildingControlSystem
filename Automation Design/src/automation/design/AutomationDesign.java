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
public class AutomationDesign {

    
    private String filename;
    
    
    public static void main(String[] args) {
       
       Controller controller = new Controller();  
       controller.startController();
       
       WaterMonitor waterMonitor = new WaterMonitor(args [0]);
       waterMonitor.startMonitor();
       
       GasMonitor gasMonitor = new GasMonitor(args[0]);
       gasMonitor.startMonitor();
        


        // TODO code application logic here
    }
    
}
