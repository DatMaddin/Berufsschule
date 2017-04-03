/**
 * @author M. Wagenführ
 *
 * Java-Buch S.236 Aufgabe 6.4
 *
 * Eine öffentliche statische Klasse für die Beinhaltung von verschiedenen Funktionen bzw. Attributen.
 * Solche Klassen werden auch als Hilfsklassen / Toolklassen (Utilityclass) verwendet, da keine Instanz erzeugt werden
 * muss um die Attribute / Methoden zu verwenden.
 */
public class Mathematik
{
    public static final float PI = 3.14159265359f;

    // berechnen einer Potenz mit Basis und Potenz
    public static float pow (float base, int power)
    {
        float ret = base;
        for (int i = 0; i < power -1; i++)
        {
            ret *= base;
        }
        return ret;
    }

    // Berechnen der Fakultät einer eingegebenen Zahl
    public static int fak (int value)
    {
        int ret = value;
        for (int i = 0; i < value - 1; i++)
        {
            ret *= value - (i+1);
        }

        return ret;
    }

    // Berechnen der Quersumme einer eingegebenen Zahl
    public static int quer (int value)
    {
        int sum = 0;

        while (0 != value)
        {
            sum += (value % 10);
            value /= 10;
        }
        return sum;
    }

    // Soll überprüfen, ob das eingegebene Dreieck rechtwinklig ist
    // hatte aber keine Lust über die Berechnung nachzudenken ;)
    public static boolean triangle (int a, int b, int c)
    {
        return true ;
    }
}
