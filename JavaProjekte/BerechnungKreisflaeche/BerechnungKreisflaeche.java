import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author M. Wagenführ
 *
 * Java-Buch Seite 227 Aufgabe 2.3
 */
public class BerechnungKreisflaeche
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        // final = beschreibt eine Konstante, welche nicht mehr verändert werden kann
        final double pi = 3.14159265;
        double diameter;    // Durchmesser
        double area;        // Fläche
    
        
        // Um Eingaben vom Benutzer abzufragen
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
        // Um ggf. auftretende Fehler abzufangen
        try
        {
            //Eingabe des Durchmessers
            System.out.println("Durchmesser:");
            diameter = Double.parseDouble(reader.readLine());
        
            //Berechnen der Fläche mit A = pi * r²     r = 1/2 d
            area = pi * Math.pow(diameter / 2, 2);
        
            System.out.println("Fläche: " + area);
        
        }
        
        // Verarbeiten des Fehlers
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
