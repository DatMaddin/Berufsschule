package ampel;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
/**
 *
 * @author mwagenfuehr, mruppert
 */
public class Ampel
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("#### AMPEL #####");
        
        final GpioController gpio = GpioFactory.getInstance();
        
        // ### pin Modes ### 
        // LEDs
        Gpio.pinMode(6, Gpio.OUTPUT); // grün
        Gpio.pinMode(1, Gpio.OUTPUT); // rot
        // Taster
        Gpio.pinMode(4, Gpio.INPUT);
        Gpio.pinMode(5, Gpio.INPUT);
        
        while (true)
        {
            Gpio.digitalWrite(1, Gpio.HIGH);
            
            if (Gpio.digitalRead(4) == Gpio.HIGH)
            {
                Gpio.digitalWrite(1, Gpio.LOW);
                Gpio.digitalWrite(6, Gpio.HIGH);
                
                int delay = 3000;
                Thread.sleep(delay);
                
                Gpio.digitalWrite(6, Gpio.LOW);
            }
        }
    }
    
}
