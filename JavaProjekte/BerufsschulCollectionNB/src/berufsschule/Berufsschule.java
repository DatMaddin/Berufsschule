package berufsschule;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;

/**
 *
 * @author Maddin
 */
public class Berufsschule
{

    public static void main(String[] args)
    {   
        //KreisflaecheBerechnen.calculateArea();
        //EndkapitalBerechnen.calculateFinalCapital();
        //Gewinnberechnung.calculateProfit();
        //LineareGleichung.calculateEquation(); 
        //Iteration.doWhile();
        
        Sensor sensor = new Sensor();
        
        sensor.temperaturMessen();
        sensor.temperaturAnzeigen();
        if (sensor.pruefen())
        {
            System.out.println("Alles klar");
        }
        else
        {
            System.out.println("ALARM");
        }
    }
}