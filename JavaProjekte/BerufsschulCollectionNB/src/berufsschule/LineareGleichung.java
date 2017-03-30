package berufsschule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mwage
 */
public class LineareGleichung
{
    //Seite 228 Aufgabe 3.3
    public static void calculateEquation()
    {
        double a;
        double b;
        
        double x;
        
        try
        {
            BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
            
            System.out.println("Gleichung: ax + b = 0");
            
            System.out.println("a = ");
            a = Double.parseDouble(reader.readLine());
            System.out.println("b = ");
            b = Double.parseDouble(reader.readLine());
            
            x = -b / a;
            
            System.out.println("x = " + x);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
