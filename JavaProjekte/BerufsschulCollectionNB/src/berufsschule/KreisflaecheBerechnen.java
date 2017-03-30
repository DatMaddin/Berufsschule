package berufsschule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mwage
 */
public class KreisflaecheBerechnen
{
    
    //Java Buch Seite 227 Aufgabe 2.3
    
    public static void calculateArea()
    {
        final double pi = 3.14159265;
        double diameter;
        double area;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try
        {
            //Eingabe des Durchmessers
            System.out.println("Durchmesser:");
            diameter = Double.parseDouble(reader.readLine());
            
            //Berechnen der Fläche mit A = pi * r²     r = 1/2 d
            area = pi * Math.pow(diameter / 2, 2);
            
            System.out.println("Durchmesser: " + area);
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }       
    } 
}
