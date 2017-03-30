import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author M. Wagenführ
 *
 * Anwendung zur Lösung der Rennsteig Aufgabe von Frau Rammelt.
 */
public class RennsteigAufgabe
{
    // ## wichtige Daten ##
    
    // Gebühr
    private static int[]    data_fee    = new int[]     {60, 50, 50, 45, 13, 15, 5, 15};
    // Startpunkt
    private static String[] data_start  = new String[]  {"Eisenach", "Neuhaus", "Neuhaus", "Oberhof", "Oberhof", "Oberhof", "Schmiedefeld", "Neuhaus"};
    // Endpunkt
    private static String[] data_end    = new String[]  {"Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Neuhaus"};
    // Kurzbezeichnung der Strecke
    private static String[] data_track  = new String[]  {"5M", "M", "N42/W42", "HM", "W17", "N17", "JC", "SC"};
    
    // Listen, um die einzelnen ausgewählten Daten zu speichern -- ist nicht unbedingt nötig, ist aber ganz nett
    // eine Übersicht aller Daten der Buchungen einsehen zu können
    //
    // An dieser Stelle werden Listen verwendeten. Diese könnte man als "erweiterte" Arrays bezeichnen.
    // Arrays sind in Java statisch, das heißt beim anlegen ist die Länge des Arrays FEST und kann nicht verändert werden
    // entsprechend können keine ZUSÄTZLICHEN Elemente hinzugefügt werden.
    //
    // Dies ist mit Listen jedoch möglich (über .add), und es kann ebenfalls ähnlich wie mit Arrays gearbeitet werden
    // es gibt außerdem einige erweiternde nette Funktionen bei Listen. Listen werden wie Objekte behandelt
    // in <> wird die Art der Liste festgelegt (z.B. <String> für eine Liste, welche Strings enthalten soll)
    
    // beinhaltet die Einzelgebühr der Buchung
    private static LinkedList<Integer>  sel_feeSingle   = new LinkedList<>();
    // beinhaltet die Gesamtgebühr (Einzelgebühr * Anzahl Personen) der Buchung
    private static LinkedList<Integer>  sel_feeTotal    = new LinkedList<>();
    // beinhaltet die Anzahl der Personen der Buchung
    private static LinkedList<Integer>  sel_countPers   = new LinkedList<>();
    // beinhaltet die Kurzbezeichnung der ausgewählten Strecke
    private static LinkedList<String>   sel_track       = new LinkedList<>();
    // beinhaltet den Startpunkt
    private static LinkedList<String>   sel_start       = new LinkedList<>();
    // beinhaltet den Endpunkt
    private static LinkedList<String>   sel_end         = new LinkedList<>();
    
    // beinhaltet die Gesamtgebühr
    private static int total_fee = 0;
    
    // beinhaltet den Namen des Nutzers
    private static String name;
    
    
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        // wird verwendet, um Eingaben vom Nutzer abzufragen
        Scanner scanner = new Scanner(System.in);
    
        boolean cont; // Fortfahren? -> weitere Strecken buchen? s.u.
    
        String temp; // wird verwendet, um die Eingaben zwischen zu speichern
    
        // Eingabe des Namen
        System.out.println("Bitte geben Sie Ihren Namen ein:");
        name = scanner.nextLine();
    
        // solange, wie weitere Buchungen vorgenommen werden sollen, diese Aktionen durchführen
        do
        {
            BookRoute(); // Buchung vornehmen
        
            System.out.println("\nWollen Sie weitere Buchungen vornehmen? (Y = Ja / N = Nein)");
            
            // Evaluierung der Eingabe -- Endlosschleife, solange eine Falscheingabe durchgeführt wird
            do
            {
                temp = scanner.nextLine();
                
                // Eingabe "klein machen", um nicht auf N und n prüfen zu müssen
                if (temp.toLowerCase().equals("n"))
                {
                    cont = false;
                    break; // vorzeitiges Verlassen der Schleife (es wird die innere do-Schleife verlassen!)
                }
                
                // Eingabe "klein machen", um nicht auf Y und y prüfen zu müssen
                else if (temp.toLowerCase().equals("y"))
                {
                    cont = true;
                    break; // vorzeitiges Verlassen der Schleife (es wird die innere do-Schleife verlassen!)
                }
                else
                {
                    System.out.println("FALSCHEINGABE!");
                }
            } while (true);
            
        } while (cont);
    
        PrintInformation(); // Ausgabe der Buchungsinformationen
    }
    
    // Vorgang zum Buchen einer Route
    private static void BookRoute()
    {
        Scanner scanner = new Scanner(System.in);
        
        Integer selection;   // Streckenauswahl (selection -1 = Index für die data_ Arrays oben)
        Integer countPerson; // Anzahl Personen
        
        String temp;
        
        // ## STRECKENAUSWAHL ##
    
        // Evaluierung der Eingabe -- Endlosschleife, solange eine Falscheingabe durchgeführt wird
        do
        {
            PrintTracks(); // Ausgabe der verfügbaren Strecken
            
            System.out.println("\nBitte wählen Sie Ihre Strecke aus!");
            temp = scanner.nextLine();
            
            selection = TryParseInt(temp); // versuch den String zu konvertieren
            
            // wenn fehl schlägt --> Fehleingabe!
            if (selection == null)
            {
                System.out.println("FALSCHEINGABE!");
                continue; // Schleife wird nocheinmal von vorne durchgeführt
            }
            
            // wenn Selektion außerhalb des Möglichen --> Fehleingabe!
            if (selection < 0 || selection > 8)
            {
                System.out.println("FALSCHEINGABE!");
            }
            // wenn Auswahl passt --> weiter machen
            else
            {
                break; // vorzeitiges Verlassen der Schleife
            }
        } while (true);
        
        // Hinzufügen der Auswahl zu den verschiedenen Listen
        sel_feeSingle.add(data_fee[selection - 1]);
        sel_start.add(data_start[selection - 1]);
        sel_end.add(data_end[selection - 1]);
        sel_track.add(data_track[selection - 1]);
        
        
        
        // ## ANZAHL PERSONEN ##
    
        // Evaluierung der Eingabe -- Endlosschleife, solange eine Falscheingabe durchgeführt wird
        do
        {
            // Vorgang siehe Streckenauswahl
            System.out.println("\nFür wie viele Personen möchten Sie buchen?");
            temp = scanner.nextLine();
            countPerson = TryParseInt(temp);
            
            if (countPerson == null)
            {
                System.out.println("FALSCHEINGABE!");
                continue;
            }
            
            if (countPerson <= 0)
            {
                System.out.println("FALSCHEINGABE!");
            }
            else
            {
                break;
            }
        } while (true);
        
        sel_countPers.add(countPerson);
        
        Integer tot_fee = data_fee[selection - 1] * countPerson;
        
        sel_feeTotal.add(tot_fee);
        total_fee += tot_fee; // Gesamtgebühr erhöhen
    }
    
    // Ausgabe der Buchungsdaten für den Nutzer
    private static void PrintInformation()
    {
        System.out.println("\n\nSehr geehrter Herr/Frau " + name);
        System.out.println("Anbei erhalten Sie Ihre Buchungsinformationen:");
        
        for (int i = 0; i < sel_feeSingle.size(); i++)
        {
            System.out.println("-----------------------------");
            System.out.println("Strecke         : " + sel_track.get(i));
            System.out.println("Start           : " + sel_start.get(i));
            System.out.println("Ende            : " + sel_end.get(i));
            System.out.println("Betrag p.P.     : " + sel_feeSingle.get(i));
            System.out.println("Personen:       : " + sel_countPers.get(i));
            System.out.println("---> Betrag ges.: " + sel_feeTotal.get(i) + "€");
        }
        System.out.println("##################################");
        System.out.println("TOTAL: " + total_fee + "€");
    }
    
    // Ausgabe der verfügbaren Strecken
    private static void PrintTracks()
    {
        System.out.println("###################################");
        System.out.println("VERFÜGBARE STRECKEN:  \n");
        System.out.println("Zahl für Eingabe | Kurzbezeichnung");
        System.out.println("1                | 5M");
        System.out.println("2                | M");
        System.out.println("3                | N42/W42");
        System.out.println("4                | HM");
        System.out.println("5                | W17");
        System.out.println("6                | N17");
        System.out.println("7                | JC");
        System.out.println("8                | SC");
        System.out.println("###################################");
        
    }
    
    
    
    // try parse Funktion ähnlich C#
    // einfach eine "schönere Art und Weise herauszufinden, ob sich der übergebene Text Parsen lässt
    private static Integer TryParseInt(String someText)
    {
        try
        {
            return Integer.parseInt(someText);
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }
    
}
