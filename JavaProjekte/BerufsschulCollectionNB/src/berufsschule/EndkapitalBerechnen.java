package berufsschule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mwage
 */
public class EndkapitalBerechnen
{
    //Java Buch Seite 227 Aufgabe 3.1
    public static void calculateFinalCapital()
    {
        final double years;
        double capital;
        double interest;
        
        double finalCapital;
        double q;
        
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        
        try
        {
            //Eingaben
            System.out.println("Geben Sie Ihr Startkapital an:");
            capital = Double.parseDouble(reader.readLine());
        
            System.out.println("Geben Sie Ihren Zinssatz an:");
            interest = Double.parseDouble(reader.readLine());
            
            System.out.println("Geben Sie die Laufzeit an:");
            years = Double.parseDouble(reader.readLine());
        
            //Berechnungen
            q = 1 + (interest / 100);
            
            finalCapital = capital * Math.pow(q, years);
            
            System.out.println("Ihr Kapital nach " + years + " Jahren: " + finalCapital);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        
    }
}
