import java.util.List;

public class BubbleSort
{
    public static int[] sort(int[] list)
    {
        int b = list.length - 1;

        while (b != 0)
        {
            for (int i = 0; i < b; i++)
            {
                int j = i + 1;

                try
                {
                    if (list[i] > list[j])
                    {
                        int temp = list[i];
                        list[i] = list[j];
                        list[j] = temp;
                    }
                } catch (IndexOutOfBoundsException e)
                {
                    System.out.println(i + " " + j + " " + b);
                    e.printStackTrace();
                }
            }
            b--;
        }
        return list;
    }
}
