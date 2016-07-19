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
public interface Sensor {
  
    public double getSensorOutput();
    public void startSensor();
    public void stopSensor();
}
