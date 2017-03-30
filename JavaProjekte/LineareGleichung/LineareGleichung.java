import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author M. Wagenführ
 *
 * Java-Buch Seite 228 Aufgabe 3.3
 */
public class LineareGleichung
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        double a;
        double b;
    
        double x; // Ergebnis
    
        // um ggf. auftretende Exceptions abzufangen
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
            System.out.println("Gleichung: ax + b = 0");
        
            System.out.println("a = ");
            a = Double.parseDouble(reader.readLine());
            System.out.println("b = ");
            b = Double.parseDouble(reader.readLine());
        
            x = -b / a;
        
            System.out.println("x = " + x);
        }
        
        // Exception verarbeiten
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
