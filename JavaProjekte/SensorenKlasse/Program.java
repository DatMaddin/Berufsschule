/**
 * @author M. Wagenführ
 *
 * Hauptklasse, in welcher sich die Main Methode befindet, um mit der Klasse zu kommunizieren
 */
public class Program
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        Sensor s1 = new Sensor("12345");
        Sensor s2 = new Sensor("Schrank 1", "Maddin Inc.", "54321");

        for (int i = 0; i < 5; i++)
        {
            s1.readTemp();
            s2.readTemp();
        }
    }
}
