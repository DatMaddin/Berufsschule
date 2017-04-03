package temperaturumrechnen;

/**
 * @author M. Wagenführ
 * 
 * Hauptklasse der Anwendung. Ist nur zum Aufrufen der erstellten Klasse
 * gedacht.
 */
public class TemperaturUmrechnen
{
    // Haupteinstiegspunkt der Anwendung
    public static void main(String[] args)
    {
        Temperatur t = new Temperatur(50);
        
        System.out.println("Celsius: " + t.getTempInC());
        System.out.println("Fahrenheit: " + t.getTempInF());
        System.out.println("Kelvin: " + t.getTempInK());
    }
    
}
