import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author M. Wagenführ
 *
 * Java Buch Seite 227 Aufgabe 3.1
 */
public class BerechnungEndkapital
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        double years;        // Laufzeit des Sparens
        double capital;      // Startkapital
        double interest;     // Zinssatz
    
        double finalCapital; // Endkapital
        double q;            // für die Berechnung
    
        // Um die Eingaben vom Nutzer abzufragen
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
    
        // Abfangen von möglichen Fehlern bei der Eingabe
        try
        {
            //Eingaben vom Benutzer abfragen
            System.out.println("Geben Sie Ihr Startkapital an:");
            capital = Double.parseDouble(reader.readLine());
        
            System.out.println("Geben Sie Ihren Zinssatz an:");
            interest = Double.parseDouble(reader.readLine());
        
            System.out.println("Geben Sie die Laufzeit an:");
            years = Double.parseDouble(reader.readLine());
        
            
            
            //Berechnungen durchführen
            q = 1 + (interest / 100);
        
            finalCapital = capital * Math.pow(q, years);
        
            System.out.println("Ihr Kapital nach " + years + " Jahren: " + finalCapital);
        }
        
        // Abfangen von Exceptions
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
