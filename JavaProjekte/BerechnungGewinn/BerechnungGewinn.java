import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author M. Wagenführ
 *
 * Java-Buch Seite 227 Aufgabe 3.2
 */
public class BerechnungGewinn
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        double fixCost;  // Fixkosten
        double varCost;  // variable Kosten
        double proceeds; // Erlös
        double count;    // Anzahl
    
        double profit;   // Profit
    
        // um mögliche Exceptions abzufangen
        try
        {
            // um Abfragen vom Nutzer zu erfassen
            BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        
            System.out.println("Bitte geben Sie die fixen Kosten ein:");
            fixCost = Double.parseDouble(reader.readLine());
        
            System.out.println("Bitte geben Sie die Kosten für ein Produkt ein:");
            varCost = Double.parseDouble(reader.readLine());
        
            System.out.println("Bitte geben Sie den Erlös für ein Produkt ein:");
            proceeds = Double.parseDouble(reader.readLine());
        
            System.out.println("Bitte geben Sie die Anzahl der Produkte ein:");
            count = Double.parseDouble(reader.readLine());
        
            
            // Berechnung des Profits
            profit = (count * proceeds) - (count * varCost) - fixCost;
        
        
            System.out.println("\n\n\nIhr Gewinn :" + profit + " €");
        
        }
        
        // verarbeiten der Exception
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
