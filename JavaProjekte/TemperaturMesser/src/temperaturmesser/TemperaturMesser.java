package temperaturmesser;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;
import java.io.File;
import java.io.FileReader;

public class TemperaturMesser
{
    public static final double MAX_TEMP = 25.0;
    public static final double MAX_TEMP_SHUTDOWN = 27.0;
    
    // Sensordatei /sys/bus/w1/devices/28-xxxxx/w1_slave
    //File file = new File("D:/temp/ds18b20.txt");  
    public static final File SENSOR_FILE = new File("/sys/bus/w1/devices/28-000009005edb/w1_slave");
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("#### STARTED ####");
        
        
        // Verbindung zum Pi
        final GpioController gpio = GpioFactory.getInstance();
        
        // ### Pin Modes festlegen ###
        // # LED #
        Gpio.pinMode(6, Gpio.OUTPUT); // grün
        Gpio.pinMode(1, Gpio.OUTPUT); // rot
        // # Taster #
        Gpio.pinMode(4, Gpio.INPUT); // rechts
        Gpio.pinMode(5, Gpio.INPUT); // links
        
        
        int count = 0; // zum zälen, damit nicht immer die Temperatur ausgegeben wird
        
        while (true)
        {
            count++;
            
            char[] temp = ReadFile(SENSOR_FILE);
            double currentTemp = GetTemperature(new String (temp));
            
            if  (count >= 10)
            {
                System.out.println("Current Temp.: " + currentTemp); 
                count = 0;
            }
       
            if (currentTemp >= MAX_TEMP_SHUTDOWN)
            {
                Gpio.digitalWrite(1, Gpio.HIGH);
                Gpio.digitalWrite(6, Gpio.HIGH);
                
                ShutDown(gpio);
                break;
            }
            else if (currentTemp >= MAX_TEMP)
            {
                // wenn Temperatur über Schwellwert - grün aus rot an
                Gpio.digitalWrite(1, Gpio.HIGH);
                Gpio.digitalWrite(6, Gpio.LOW);
                
                System.out.println("CAUTION! Max Temp reached! (" + currentTemp + ")");
            }
            else
            {
                // ansonsten (also wenn Temp OK) - grün an und rot aus
                Gpio.digitalWrite(6, Gpio.HIGH);
                Gpio.digitalWrite(1, Gpio.LOW);
            }
            
            // Prüfung ob Taster gedrückt --> Programm beenden
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
        char[] ret = new char[(int) file.length()]; 
        
        fr.read(ret);
        fr.close();
        
        return ret;
    }
    
    // Ermitteln der Temperatur aus der Datei
    private static double GetTemperature (String content) throws Exception
    {
        int startPos, endPos;
        String strTemp;
        double temperature;
        
        if (content.contains("t="))
        {
            startPos = content.indexOf("t=") +2 ;
            endPos = content.length();
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
        for (int i = 0; i < 3; i++)
        {
            Gpio.digitalWrite(6, Gpio.HIGH);
            Gpio.digitalWrite(1, Gpio.HIGH);
            
            Thread.sleep(100);
            
            Gpio.digitalWrite(6, Gpio.LOW);
            Gpio.digitalWrite(1, Gpio.LOW);
            
            Thread.sleep(100);
        }
    }
    
    // Server = Pi herunterfahren
    private static void ShutDown(GpioController gpio) throws Exception
    {
        System.out.println("#### SERVER SHUTDOWN SOON ####");
        System.out.println("The Server will shutdown automatically in 10s");
        System.out.println("You can terminate the Process with Ctrl + C");
        
        for (int i = 0; i < 10; i++)
        {
            Thread.sleep(1000);
            System.out.println(i + 1);
        }
        System.out.println("#### SHUTTING DOWN NOW ####");
        
        Process p = Runtime.getRuntime().exec("sudo shutdown -h now");
        p.waitFor();
    }
}
