import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mwage on 19.12.2016.
 */
public class MinMax
{
    public static void berechnen()
    {
        double zahl1, zahl2, zahl3, min, max;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            System.out.println("Erste Zahl:");
            zahl1 = Double.parseDouble(reader.readLine());

            System.out.println("Zweite Zahl:");
            zahl2 = Double.parseDouble(reader.readLine());

            System.out.println("Dritte Zahl:");
            zahl3 = Double.parseDouble(reader.readLine());


            min = zahl1;
            max = zahl2;

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

            System.out.println("Max: " + max + " Min: " + min);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {reader.close(); } catch(Exception e) { e.printStackTrace(); }
        }
    }
}
