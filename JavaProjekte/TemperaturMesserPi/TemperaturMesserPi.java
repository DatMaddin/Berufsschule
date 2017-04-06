// nötige Imports für die Interaktion mit dem RasPi
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;

import java.io.File;
import java.io.FileReader;

/**
 * @author M. Wagenführ
 *
 *
 */
public class TemperaturMesserPi
{
    // Definierung wichtiger Konstanten (final --> können im Nachhinein nicht mehr geändert werden)
    public static final double MAX_TEMP = 25.0; // maximale Temperatur -- erreicht: Warnung
    public static final double MAX_TEMP_SHUTDOWN = 27.0; // maximale Temperatur, bis der RasPi beendet wird
    
    // Pfad für die Sensordatei (/sys/bus/w1/devices/28-xxxxx/w1_slave)
    // Festlegung der Sensor Datei, um mit dieser interagieren zu können
    public static final File SENSOR_FILE = new File("/sys/bus/w1/devices/28-000009005edb/w1_slave");
    
    // Haupteinstiegspunkt der Anwendung
    public static void main(String[] args) throws Exception
    {
        System.out.println("#### STARTED ####");
    
    
        // holen der Factory, welche die Interaktion mit dem RasPi verwaltet
        final GpioController gpio = GpioFactory.getInstance();
    
        // ### Definierung der Pin-Modes ###
        // # LEDs #
        Gpio.pinMode(6, Gpio.OUTPUT); // grün
        Gpio.pinMode(1, Gpio.OUTPUT); // rot
        // # Taster #
        Gpio.pinMode(4, Gpio.INPUT); // rechts
        Gpio.pinMode(5, Gpio.INPUT); // links
    
    
        int count = 0; // zum zälen, damit nicht immer die Temperatur ausgegeben wird
    
        // Definierung einer Endlosschleife, welche die Aufgaben für den RasPi beinhaltet
        while (true)
        {
            count++;
        
            char[] temp = ReadFile(SENSOR_FILE); // Auslesen des Inhaltes der Datei in ein Char-Array
            double currentTemp = GetTemperature(new String (temp)); // ermitteln der Temperatur aus dem Inhalt der Datei
        
            if  (count >= 10) // wenn Count >= 10 ist, dann wird die Temperatur auf der Konsole ausgegeben
            {
                System.out.println("Current Temp.: " + currentTemp);
                count = 0;
            }
        
            // wenn die Temperatur das Maximum für das Herunterfahren erreicht hat
            // dann werden beide LEDs eingeschalten und der RasPi ausgeschalten + die Schleife verlassen
            if (currentTemp >= MAX_TEMP_SHUTDOWN)
            {
                Gpio.digitalWrite(1, Gpio.HIGH);
                Gpio.digitalWrite(6, Gpio.HIGH);
            
                ShutDown(gpio);
                break;
            }
            
            // wenn die Temperatur nur über der "normalen" max Temperatur ist, dann wird die rote LED eingeschalten
            // und ein Warntext ausgegeben
            else if (currentTemp >= MAX_TEMP)
            {
                // wenn Temperatur über Schwellwert - grün aus rot an
                Gpio.digitalWrite(1, Gpio.HIGH);
                Gpio.digitalWrite(6, Gpio.LOW);
            
                System.out.println("CAUTION! Max Temp reached! (" + currentTemp + ")");
            }
            
            // ansonsten ist alles okay und die grüne LED wird zum leuchten gebracht und die rote ausgeschalten
            else
            {
                Gpio.digitalWrite(6, Gpio.HIGH);
                Gpio.digitalWrite(1, Gpio.LOW);
            }
        
            // wenn einer der beiden Taster gedrückt wird, dann wird das Programm beendet
            if (Gpio.digitalRead(4) == Gpio.HIGH || Gpio.digitalRead(5) == Gpio.HIGH)
            {
                System.out.println("#### EXIT ####");
                BlinkOnExit();
                System.exit(0);
            }
        }
    }
    
    // Einlesen der Datei und zurückgeben der Daten als char Array
    private static char[] ReadFile (File file) throws Exception
    {
        FileReader fr = new FileReader(file);
        char[] ret = new char[(int) file.length()]; // erstellen des Char-Arrays mit der Länge der Datei
        
        fr.read(ret);
        fr.close();
        
        return ret;
    }
    
    // Ermitteln der Temperatur aus der Datei
    private static double GetTemperature (String content) throws Exception
    {
        int startPos, endPos;
        String strTemp;
        
        if (content.contains("t="))
        {
            // mit indexOf wird der index ermittelt, an der zu suchende String das erste Mal auftritt
            // da wir aber alles hinter "t=" haben wollen, muss der index noch um 2 erhöht werden
            startPos = content.indexOf("t=") + 2;
            endPos = content.length();
            
            // substring gibt einen String aus, welcher über zwei Indexe bestimmt wird
            strTemp = content.substring(startPos, endPos);
            
            return Double.parseDouble(strTemp) / 1000;
        }
        else
        {
            return 0;
        }
    }
    
    // blinken, um das Beenden zu signalisieren
    private static void BlinkOnExit () throws Exception
    {
        int delay = 100; // definiert den Abstand, in welchem die LEDs blinken sollen
        
        // 3-Mal kurz hintereinander blinken lassen
        for (int i = 0; i < 3; i++)
        {
            Gpio.digitalWrite(6, Gpio.HIGH);
            Gpio.digitalWrite(1, Gpio.HIGH);
            
            Thread.sleep(delay);
            
            Gpio.digitalWrite(6, Gpio.LOW);
            Gpio.digitalWrite(1, Gpio.LOW);
            
            Thread.sleep(delay);
        }
    }
    
    // Server = Pi herunterfahren
    private static void ShutDown(GpioController gpio) throws Exception
    {
        System.out.println("#### SERVER SHUTDOWN SOON ####");
        System.out.println("The Server will shutdown automatically in 10s");
        System.out.println("You can terminate the Process with Ctrl + C");
        
        // Countdown von 10s
        for (int i = 0; i < 10; i++)
        {
            Thread.sleep(1000);
            System.out.println(i + 1);
        }
        System.out.println("#### SHUTTING DOWN NOW ####");
        
        Process p = Runtime.getRuntime().exec("sudo shutdown -h now"); //herunterfahren
        p.waitFor();
    }
}
