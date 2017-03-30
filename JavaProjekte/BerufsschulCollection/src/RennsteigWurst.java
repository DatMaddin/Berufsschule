import java.util.LinkedList;
import java.util.Scanner;

class RennsteigWurst
{
    // wichtige Daten
    private static int[] data_fee = new int[]{60, 50, 50, 45, 13, 15, 5, 15};
    private static String[] data_start = new String[]{"Eisenach", "Neuhaus", "Neuhaus", "Oberhof", "Oberhof", "Oberhof", "Schmiedefeld", "Neuhaus"};
    private static String[] data_end = new String[]{"Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Schmiedefeld", "Neuhaus"};
    private static String[] data_track = new String[]{"5M", "M", "N42/W42", "HM", "W17", "N17", "JC", "SC"};

    private static LinkedList<Integer> sel_feeSingle = new LinkedList<>();
    private static LinkedList<Integer> sel_feeTotal = new LinkedList<>();
    private static LinkedList<Integer> sel_countPers = new LinkedList<>();
    private static LinkedList<String> sel_track = new LinkedList<>();
    private static LinkedList<String> sel_start = new LinkedList<>();
    private static LinkedList<String> sel_end = new LinkedList<>();

    private static int total_fee = 0;

    private static String name;


    // Hauptfunktion
    static void Do()
    {
        Scanner scanner = new Scanner(System.in);

        boolean cont; // Fortfahren? -> weitere Strecken buchen?

        String temp;

        System.out.println("Bitte geben Sie Ihren Namen ein:");
        name = scanner.nextLine();

        do
        {
            BookRoute();

            System.out.println("\nWollen Sie weitere Buchungen vornehmen? (Y = Ja / N = Nein)");
            do
            {
                temp = scanner.nextLine();
                if (temp.toLowerCase().equals("n"))
                {
                    cont = false;
                    break;
                } else if (temp.toLowerCase().equals("y"))
                {
                    cont = true;
                    break;
                } else
                {
                    System.out.println("FALSCHEINGABE!");
                }
            } while (true);
        } while (cont);

        PrintInformation();
    }

    // Vorgang zum Buchen einer Route
    private static void BookRoute()
    {
        Scanner scanner = new Scanner(System.in);

        Integer selection;   // Streckenauswahl
        Integer countPerson; // Anzahl Personen

        String temp;

        // ## STRECKENAUSWAHL ##
        do
        {
            PrintTracks();

            System.out.println("\nBitte wählen Sie Ihre Strecke aus!");
            temp = scanner.nextLine();

            selection = TryParseInt(temp);

            if (selection == null)
            {
                System.out.println("FALSCHEINGABE!");
                continue;
            }

            if (selection < 0 || selection > 8)
            {
                System.out.println("FALSCHEINGABE!");
            } else
            {
                break;
            }
        } while (true);

        sel_feeSingle.add(data_fee[selection - 1]);
        sel_start.add(data_start[selection - 1]);
        sel_end.add(data_end[selection - 1]);
        sel_track.add(data_track[selection - 1]);

        // ## ANZAHL PERSONEN ##
        do
        {
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
            } else
            {
                break;
            }
        } while (true);

        sel_countPers.add(countPerson);

        Integer tot_fee = data_fee[selection - 1] * countPerson;

        sel_feeTotal.add(tot_fee);
        total_fee += tot_fee;
    }

    // gibt die Informationen der Buchungen dem Benutzer aus
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
