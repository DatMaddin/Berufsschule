import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mwage on 20.02.2017.
 */
public class Marvin
{
    public static void Do() throws Exception
    {

        BufferedReader eingabe = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("Name: ");
        String Name = eingabe.readLine();
        System.out.println("Anzahl der Personen");
        String anzahlPersonen = eingabe.readLine();
        int anzahlPersonenRechnung = Integer.parseInt(anzahlPersonen);
        System.out.println("Streckennummer: ");
        String Streckennummer = eingabe.readLine();
        int StreckennummerRechnung = Integer.parseInt(Streckennummer);
        int StartGebuehr = 0;
        String Startort = "";
        String Zielort = "";

        switch (StreckennummerRechnung){
            case 1: Streckennummer = "1";
                StartGebuehr = 60;
                Startort = "Eisenach";
                Zielort = "Schmiedefeld";
                break;
            case 2: Streckennummer = "2";
                StartGebuehr = 50;
                Startort = "Neuhaus";
                Zielort = "Schmiedefeld";
                break;
            case 3: Streckennummer = "3";
                StartGebuehr = 50;
                Startort = "Neuhaus";
                Zielort = "Schmiedefeld";
                break;
            case 4: Streckennummer = "4";
                StartGebuehr = 45;
                Startort = "Oberhof";
                Zielort = "Schmiedefeld";
                break;
            case 5: Streckennummer = "5";
                StartGebuehr = 13;
                Startort = "Oberhof";
                Zielort = "Schmiedefeld";
                break;
            case 6: Streckennummer = "6";
                StartGebuehr = 15;
                Startort = "Oberhof";
                Zielort = "Schmiedefeld";
                break;
            case 7: Streckennummer = "7";
                StartGebuehr = 5;
                Startort = "Schmiedefeld";
                Zielort = "Schmiedefeld";
                break;
            case 8: Streckennummer = "8";
                StartGebuehr = 15;
                Startort = "Neuhaus";
                Zielort = "Neuhaus";
                break;
            default: Streckennummer = "Fehlerhafte Strecke";
                break;

        }
        System.out.println("Startort:" + Startort);
        System.out.println("Zielort: " + Zielort);
        System.out.println("Gebühren pro Teilnehmer: " + StartGebuehr + "€");
        int Gesamtbetrag = StartGebuehr * anzahlPersonenRechnung;
        System.out.println("Gesamtbetrag: " + Gesamtbetrag + "€");
        System.out.println("Anzahl der Teilnehmer: " + anzahlPersonenRechnung);
        System.out.println("Wollen Sie die Bestellung bestätigen? Ja(J)/Nein(N)");
        char bestaetigung;
        bestaetigung = eingabe.readLine().charAt(0);
        if(bestaetigung == 'J')
        {
            System.out.println("Bestellungvorgang erfolgreich abgeschlossen.");
        }
        else
        {
            System.out.println("Bestellvorgang abgebrochen.");
        }

    }
}
