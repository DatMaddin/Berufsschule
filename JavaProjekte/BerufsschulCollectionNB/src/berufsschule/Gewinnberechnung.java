package berufsschule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mwage
 */
public class Gewinnberechnung
{
    
    //Seite 227 Aufgabe 3.2 
    public static void calculateProfit()
    {
       double fixCost;
       double varCost;
       double proceeds;
       double count;
       
       double profit;
       
       try
       {
           BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
           
           System.out.println("Bitte geben Sie die fixen Kosten ein:");
           fixCost = Double.parseDouble(reader.readLine());
           
           System.out.println("Bitte geben Sie die Kosten für ein Produkt ein:");
           varCost = Double.parseDouble(reader.readLine());
           
           System.out.println("Bitte geben Sie den Erlös für ein Produkt ein:");
           proceeds = Double.parseDouble(reader.readLine());
           
           System.out.println("Bitte geben Sie die Anzahl der Produkte ein:");
           count = Double.parseDouble(reader.readLine());
           
           profit = (count * proceeds) - (count * varCost) - fixCost;
           
           
           System.out.println("\n\n\nIhr Gewinn :" + profit + " €");
           
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
   }
}
