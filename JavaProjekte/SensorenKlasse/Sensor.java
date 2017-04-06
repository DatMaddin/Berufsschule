import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author M. Wagenführ
 *
 * Klasse zur Abbildung eines Sensores zur Temperaturmessung.
 * Temperatur kann ausgelesen und evaluiert werden.
 * Es wird bei einer zu hohen Temperatur eine Warnung ausgegeben.
 * An der Stelle, können auch Schnittstellen zu anderen Klassen (z.B. das Versenden einer SMS / herunterfahren des
 * Servers) hergestellt werden.
 */
public class Sensor
{
    private static float MAXTEMP_WARN = 25.0f;
    private static float MAXTEMP_SHUTDOWN = 27.0f;

    private String location;        // Abbildung des "Standorts" des Sensors, z.B. Serverschrank
    private String manufacturer;    // Herstellername
    private String sensorID;        // herstellerspezifische Adresse/ID
    private float currentTemp;      // momentane Temperatur
    private File sensorFile;        // Datei, in welcher dann die Temperatur steht

    // ## Konstruktoren ##
    public Sensor (String sensorID)
    {
        this.location = "";
        this.manufacturer = "";
        this.sensorID = sensorID;

        // direktes erstellen der Datei, aus welcher später die Daten ausgelesen werden sollen
        // this.sensorFile = new File("C:\\TestTemp\\test.txt"); //<-- zum testen ob funktioniert
        // "reeller" Pfad für die Interaktion mit dem RasPi
        this.sensorFile = new File ("/sys/bus/w1/devices/" + sensorID + "/w1_slave");
    }

    public Sensor (String location, String manufacturer, String sensorID)
    {
        this.location = location;
        this.manufacturer = manufacturer;
        this.sensorID = sensorID;

        // direktes erstellen der Datei, aus welcher später die Daten ausgelesen werden sollen
        // this.sensorFile = new File("C:\\TestTemp\\test.txt"); //<-- zum testen ob funktioniert
        // "reeller" Pfad für die Interaktion mit dem RasPi
        this.sensorFile = new File ("/sys/bus/w1/devices/" + sensorID + "/w1_slave");
    }
    // ##

    // Auslesen und evaluieren der Temperatur
    // ist zum Abrufen außerhalb innerhalb einer Schleife o.Ä. gedacht
    public void readTemp ()
    {
        char[] temp = this.readFile(this.sensorFile); // lesen der Datei in ein Char-Array

        // liefert die Methode "readFile" ein korrektes Ergebnis, dann lese die Temperatur aus und evaluiere diese
        if (temp != null)
        {
            this.currentTemp = this.getTemperature(new String(temp));   // Auslesen der Temperatur
            this.evaluateTemp(this.currentTemp);                        // Evaluieren der Temperatur
        }
    }

    // evaluieren der momentanen Temperatur -- hier werden dann Warnungen ausgegeben, falls nötig
    // es können Schnittstellen zu anderen Klassen hergestellt werden
    private void evaluateTemp (float temp)
    {
        // einfache Prüfung, ob der Schwellwert erreicht/überschritten wurde
        if (temp >= MAXTEMP_SHUTDOWN)
        {
            System.out.println("[CRITICAL|" + this.location + "] Temp. = " + temp + " | SYSTEM WIRD HERUNTERGEFAHREN!");
            // Shutdown Routine bei der zugehörigen Instanz anstoßen ...
        }
        else if (temp >= MAXTEMP_WARN)
        {
            System.out.println("[WARNING|" + this.location + "] Temp. = " + temp + " | TEMPERATUR IST ZU HOCH!");
            // Routine zur Benachrichtigung des Admins hier anstoßen ...
        }
        else
        {
            System.out.println("Temp. = " + temp);
        }
    }

    // Auslesen der Sensordatei -- stellt einen "Container" zum Auslesen einer Datei dar -- gibt ein Char-Array zurück
    // Liegen Fehler vor, wird eine entsprechende Warnung ausgegeben
    private char[] readFile(File file)
    {
        FileReader fr;

        try
        {
            fr = new FileReader(file);
            char[] ret = new char[(int)file.length()];

            fr.read(ret);
            fr.close();

            return ret;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("[WARN] Sensor " + this.sensorID + " " + this.manufacturer + " :");
            System.out.println("Die Sensordatei " + this.sensorFile.getPath() + " konnte nicht gefunden werden!");

            return null;
        }
        catch (IOException e)
        {
            System.out.println("Fehler beim lesen der Sensordatei!!! \n\n");
            e.printStackTrace();

            return null;
        }
    }

    // Auslesen der Temperatur aus der Sensordatei
    private float getTemperature (String content)
    {
        int startPos, endPos;
        String temp;

        // schauen, ob der Indikator "t=" enthalten ist, wenn nein, wird 0 zurück gegeben, ansonsten wird ausgelesen
        if (content.contains("t="))
        {
            // ermitteln, an welcher Stelle sich der Indikator befindet, +2, da die Angabe der Temperatur hinter dem
            // Indikator stattfindet
            startPos = content.indexOf("t=") + 2;

            // herausfinden der Länge der Temperatur-Angabe -- in diesem Fall genügt es, auf das Ende der Datei zu
            // prüfen, da die TemperaturAngabe, das letzte Argument der Datei ist
            endPos = content.length();

            // den Teil der TemperaturAngabe mit substring aus dem Inhalt der Datei ermitteln
            temp = content.substring(startPos, endPos);

            // einfaches berechnen der Temperatur und zurückgeben dieser
            return Float.parseFloat(temp) / 1000;
        }
        else
        {
            return 0;
        }
    }


    // ### GETTER SETTER ###
    public static float getMaxtempWarn() { return MAXTEMP_WARN; }
    public static void setMaxTempWarn (float value)
    {
        if (value <= 0.0f)
        {
            System.out.println("Sind Sie sich sicher, dass sie die Temperatur auf 0 bzw. kleiner 0 setzen wollen?");
        }
        else
        {
            MAXTEMP_WARN = value;
        }
    }

    public static float getMaxtempShutdown () { return MAXTEMP_SHUTDOWN; }
    public static void setMaxtempShutdown (float value)
    {
        if (value <= 0.0f)
        {
            System.out.println("Sind Sie sich sicher, dass sie die Temperatur auf 0 bzw. kleiner 0 setzen wollen?");
        }
        else
        {
            MAXTEMP_SHUTDOWN = value;
        }
    }


    public String getLocation () { return this.location; }
    public void setLocation (String value) { this.location = value; }

    public String getManufacturer () { return this.manufacturer; }
    public void setManufacturer (String value) { this.manufacturer = value; }

    public String getSensorID () { return this.sensorID; }
    public void setSensorID (String value) { this.sensorID = value; }

    // kein Setter für die momentane Temperatur, da diese ja nur durch den Sensor selbst und nicht von außen
    // gesetzt werden soll
    public float getCurrentTemp () { return this.currentTemp; }


}
