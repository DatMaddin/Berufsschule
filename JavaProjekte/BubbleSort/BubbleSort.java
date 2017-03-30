/**
 * @author M. Wagenführ
 *
 * eine Anwendung, um ein Array an Ganzzahlen nach dem Bubble-Sort verfahren zu sortieren.
 */
public class BubbleSort
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String args[])
    {
        System.out.println("### BUBBLE SORT ###");
        
        // Definieren des Arrays, welches sortiert werden soll
        // zwischen { } können beliebige Ganzzahlen geschrieben werden - müssen durch ein Komma getrennt werden
        int arrayToSort[] = {12, 99, 52, 54, 78, 41, 41, 69, 42, 13, 0, 1};
        
        // Kopie vom oben definierten Array erstellen - muss so erstellt werden, ansonsten erstellt man nur eine
        // Referenz ==> wird arraySorted geändert, wird auch arrayToSort geändert (da mit dem selben Speicher
        // gearbeitet wird
        //
        // dieses Array wird sortiert
        int arraySorted[] = new int[arrayToSort.length];
        // Übergabeparameter: Quelle, Quell-Start-Index, Ziel, Ziel-Start-Index, Länge der Kopie
        System.arraycopy(arrayToSort, 0, arraySorted, 0, arrayToSort.length);

    
        // definieren der Grenze, ab wann das Array fertig sortiert wurde (fertig, wenn b = 0)
        int b = arraySorted.length - 1;
    
        // While-Schleife, welche durchlaufen wird, solange das Ende nicht erreicht wurde (b != 0)
        while (b != 0)
        {
            // For-Schleife, um jedes Element des Arrays zu "besuchen"
            for (int i = 0; i < b; i++)
            {
                int j = i + 1; // es wird immer mit der nächsten Stelle verglichen

                // wenn das linke Element größer als das rechte Element ist, dann wird die Position getauscht
                if (arraySorted[i] > arraySorted[j])
                {
                    int temp = arraySorted[i];
                    arraySorted[i] = arraySorted[j];
                    arraySorted[j] = temp;
                }
            }
            b--;
        }
        
        // Ausgabe Ausgangs-Array
        for (int i = 0; i < arrayToSort.length; i++)
        {
            System.out.print(arrayToSort[i] + " ");
        }
        
        System.out.println(); // Leerzeile
        
        // Ausgabe sortiertes Array
        for (int i = 0; i < arraySorted.length; i++)
        {
            System.out.print(arraySorted[i] + " ");
        }
    }
}
