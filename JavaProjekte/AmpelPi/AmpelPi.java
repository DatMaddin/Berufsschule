// nötige Imports für die Interaktion mit dem RasPi
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;


/**
 * @author M. Wagenführ, M. Ruppert
 *
 * Eine Anwendung, welche auf Knopfdruck eine grüne LED an, eine rot LED aus schaltet. Nach einem bestimmten Delay
 * wird die rote Lampe wieder leuchten aber die grüne nicht mehr.
 *
 * ==> Simulation einer Ampel
 */
public class Ampel
{
    // Definieren eines Delays, nach welcher Zeit das grüne LED ausgeschalten wird (in ms)
    public static int DELAY = 3000;
    
    // Haupteinstiegspunkt der Anwendung
    public static void main (String [] args) throws InterruptedException
    {
        System.out.println("#### AMPEL ####");
        
        // holen der Factory, welche die Interaktion mit dem RasPi verwaltet
        final GpioController gpio = GpioFactory.getInstance();
        
        // ### Definierung der Pin-Modes ###
        // # LEDs #
        Gpio.pinMode(6, Gpio.OUTPUT); // grün
        Gpio.pinMode(1, Gpio.OUTPUT); // rot
        // # Taster #
        Gpio.pinMode(4, Gpio.INPUT);
        Gpio.pinMode(5, Gpio.INPUT);
		
		
		// ausschalten beider LEDs, damit kein ungewolltes Verhalten auftritt (quasi ein "Reset")
        Gpio.digitalWrite(6, Gpio.LOW);
        Gpio.digitalWrite(1, Gpio.LOW);
		
    
        // Definierung einer Endlosschleife, welche die Aufgaben für den RasPi beinhaltet
        while (true)
        {
            // die rote LED immer eingeschaltet lassen
            Gpio.digitalWrite(1, Gpio.HIGH);
        
            // wird der Taster gedrückt
            if (Gpio.digitalRead(4) == Gpio.HIGH)
            {
                Gpio.digitalWrite(1, Gpio.LOW);     // ausschalten der roten LED
                Gpio.digitalWrite(6, Gpio.HIGH);    // einschalten der grünen LED
                
                Thread.sleep(DELAY); // warten des definierten Delays (s.o.)
            
                Gpio.digitalWrite(6, Gpio.LOW); // ausschalten der grünen LED
                // an dieser Stelle muss die rote LED nicht noch einmal eingeschalten werden,
                // da diese ja am Anfang der Schleife immer angeschalten wird
            }
        }
    }
}
