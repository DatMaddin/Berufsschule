package autoblinker;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

/**
 *
 * @author mwagenfuehr, mruppert
 */
public class AutoBlinker
{
    public static final int DELAY = 500;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("#### BLINKER #####");
        
        final GpioController gpio = GpioFactory.getInstance();
        
        // ### pin Modes ### 
        // LEDs
        Gpio.pinMode(6, Gpio.OUTPUT); // grün
        Gpio.pinMode(1, Gpio.OUTPUT); // rot
        // Taster
        Gpio.pinMode(4, Gpio.INPUT); // rechts
        Gpio.pinMode(5, Gpio.INPUT); // links
        
        
        boolean rechtsAN = false;
        boolean linksAN = false;
        
        Gpio.digitalWrite(6, Gpio.LOW);
        Gpio.digitalWrite(1, Gpio.LOW);
        
        while (true)
        {
            if (rechtsAN)
            {
                BlinkeRechts();
            }
            
            if (linksAN)
            {
                BlinkeLinks();
            }
            
            
            // rechts 
            if (Gpio.digitalRead(4) == Gpio.HIGH)
            {
                if (rechtsAN)
                {
                    rechtsAN = false;
                    linksAN = false;
                }
                else
                {
                    rechtsAN = true;
                    linksAN = false;
                }
            }
            
            // links
            if (Gpio.digitalRead(5) == Gpio.HIGH)
            {
                if (linksAN)
                {
                    linksAN = false;
                    rechtsAN = false;
                }
                else
                {
                    linksAN = true;
                    rechtsAN = false;
                }
            }          
        }
    }
    
    private static void BlinkeRechts() throws InterruptedException
    {
        Gpio.digitalWrite(1, Gpio.HIGH);
        Gpio.delay(DELAY);
        Gpio.digitalWrite(1, Gpio.LOW);
        Gpio.delay(DELAY);    
    }
    
    private static void BlinkeLinks() throws InterruptedException
    {
        Gpio.digitalWrite(6, Gpio.HIGH);
        Gpio.delay(DELAY);
        Gpio.digitalWrite(6, Gpio.LOW);
        Gpio.delay(DELAY); 
    }
}
