// nötige Imports für die Interaktion mit dem RasPi
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

/**
 * @author M. Wagenführ, M. Ruppert
 *
 * "Simulation" eines Auto-Blinkers für den RasPi. Je nachdem wie die Taster betätigt werden, blinken die LEDs.
 */
public class AutoBlinkerPi
{
    // Definieren eines Delays, in welcher Geschwindigkeit der Blinken blinken soll (in ms)
    public static int DELAY = 50;
    
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args) throws InterruptedException
    {
        System.out.println("#### AUTO BLINKER ####");
    
        // holen der Factory, welche die Interaktion mit dem RasPi verwaltet
        final GpioController gpio = GpioFactory.getInstance();
    
        // ### Definierung der Pin-Modes ###
        // # LEDs #
        Gpio.pinMode(6, Gpio.OUTPUT); // grün
        Gpio.pinMode(1, Gpio.OUTPUT); // rot
        // # Taster #
        Gpio.pinMode(4, Gpio.INPUT); // rechts
        Gpio.pinMode(5, Gpio.INPUT); // links
        
        
        // "Vorarbeit" für die Arbeit der Anwendung
        
        boolean rechtsAN = false;   // definiert ein boolean, welches beinhaltet, ob der rechte Blinke an sein soll/ist
        boolean linksAN = false;    // s.o., nur links
    
        // ausschalten beider LEDs, damit kein ungewolltes Verhalten auftritt (quasi ein "Reset")
        Gpio.digitalWrite(6, Gpio.LOW);
        Gpio.digitalWrite(1, Gpio.LOW);
    
        // Definierung einer Endlosschleife, welche die Aufgaben für den RasPi beinhaltet
        while (true)
        {
            // soll der rechte Blinker blinken, dann lasse diesen blinken
            if (rechtsAN)
            {
                BlinkeRechts();
            }
    
            // soll der rechte Blinker blinken, dann lasse diesen blinken
            if (linksAN)
            {
                BlinkeLinks();
            }
        
        
            // Abfragen des rechten Tasters
            if (Gpio.digitalRead(4) == Gpio.HIGH)
            {
                // wenn rechts bereits blinkt, dann soll keine Lampe blinken
                if (rechtsAN)
                {
                    rechtsAN = false;
                    linksAN = false;
                }
                // ansonsten, rechten Blinker blinken lassen
                else
                {
                    rechtsAN = true;
                    linksAN = false;
                }
            }
        
            // Abfragen des linken Tasters
            if (Gpio.digitalRead(5) == Gpio.HIGH)
            {
                // wenn links bereits blinkt, dann soll keine Lampe blinken
                if (linksAN)
                {
                    linksAN = false;
                    rechtsAN = false;
                }
                // ansonsten, linken Blinker blinken lassen
                else
                {
                    linksAN = true;
                    rechtsAN = false;
                }
            }
        }
    }
    
    // Methode um den rechten Blinker blinken zu lassen
    // ==> schaltet die rechte LED abwechselnd an und wieder aus
    private static void BlinkeRechts() throws InterruptedException
    {
        Gpio.digitalWrite(1, Gpio.HIGH);
        Gpio.delay(DELAY);
        Gpio.digitalWrite(1, Gpio.LOW);
        Gpio.delay(DELAY);
    }
    
    // siehe BlinkeRechts()
    private static void BlinkeLinks() throws InterruptedException
    {
        Gpio.digitalWrite(6, Gpio.HIGH);
        Gpio.delay(DELAY);
        Gpio.digitalWrite(6, Gpio.LOW);
        Gpio.delay(DELAY);
    }
    }
}
