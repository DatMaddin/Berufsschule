import java.util.Scanner;

/**
 * @author M. Wagenführ
 *
 * Über eine Nutzer-Eingabe werden drei verschiedene Zahlen abgefragt. Im Anschluss wird von diesen Zahlen das Minimum
 * und das Maximum ermittelt.
 *
 * HINWEIS:
 * Bei diesem Anwendungsfall wird die Nutzer-Eingabe nicht auf Korrektheit überprüft. Entsprechend müssen ggf.
 * auftretende Fehler über ein try-catch-finally abgefangen werden
 */
public class MinMax
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        // Definierung der Variablen welche die Daten beinhalten werden
        double zahl1, zahl2, zahl3, min, max;
        
        // der Scanner wird verwendet, um User-Eingaben über die Konsole abzufragen
        Scanner scanner = new Scanner(System.in);
        
        // der try-Block sollte das umgeben, wo Fehler bei der Laufzeit auftreten könnten (Exceptions)
        // Häufig treten solche Fehler bei User-Eingaben, Datei-Interaktion uvm auf. Dort sollte immer ein try-catch
        // verwendet werden (Java weist meist auch schon darauf hin).
        //
        
        try
        {
            // Einlesen der Daten über die Nutzer-Eingabe
            System.out.println("Erste Zahl:");
            zahl1 = Double.parseDouble(scanner.nextLine());
    
            System.out.println("Zweite Zahl:");
            zahl2 = Double.parseDouble(scanner.nextLine());
    
            System.out.println("Dritte Zahl:");
            zahl3 = Double.parseDouble(scanner.nextLine());
    
            min = zahl1;
            max = zahl2;
    
            // ermitteln des Minimums bzw. Maximums
            if (min > max)
            {
                double temp = min;
                min = max;
                max = temp;
            }
    
            if (zahl3 < min)
            {
                min = zahl3;
            }
            if (zahl3 > max)
            {
                max = zahl3;
            }
    
            // Ausgabe des Minimums bzw. Maximums
            System.out.println("Max: " + max + " Min: " + min);
        }
        
        // wird aufgerufen, wenn im try-Block ein Fehler (Exception) auftritt. Hier können dann verschiedene Aktionen
        // ausgeführt werden. Z.B. den Nutzer über seine Missetat benachrichtigen.
        //
        // es können mehrere Exception-Arten abgefragt werden. Entsprechend wird der zugehörige catch-Block aufgerufen
        // so können "maßgeschneiderte" Warnmeldungen ausgegeben werden
        //
        // Die "Hierarchie" ist hier aber zu beachten. Da die Exception "Exception" die allgemeinste Exception ist
        // sollte diese nicht als erster catch-Block aufgeführt werden, da diese IMMER aufgerufen wird (da an erster
        // Stelle in der "Hierarchie"), aber die darauffolgenden Blöcke nicht mehr
        // (die meisten Entwicklungsumgebungen sollten dann auch eine entsprechende Meldung ausgeben)
        catch (NumberFormatException e)
        {
            System.out.println("Falsche Eingabe!");
            e.printStackTrace(); // gibt den StackTrace der Exception in der Konsole auf
        }
        
        catch (Exception e)
        {
            System.out.println("Unbekannter Fehler!");
            e.printStackTrace(); // gibt den StackTrace der Exception in der Konsole auf
        }
        
        // wird immer aufgerufen
        //
        // wird eine Ressource verwendet, welche nach der Verwendung geschlossen werden sollte (z.B. Scanner, Reader...)
        // dann kann diese über den finally-Zweig geschlossen werden (da dieser IMMER aufgerufen wird!)
        finally
        {
            scanner.close();
            // verwendete Ressourcen wie FileReader oder Scanner sollten immer geschlossen werden, wenn diese nicht mehr
            // benötigt werden!
        }
        
    }
}
