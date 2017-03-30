public class Sensor
{
    public static final double MAX_TEMP = 30;
    public static int count = 0;

    private int sensNumb;
    private double temp;

    public Sensor()
    {
        this.sensNumb = count;
        count ++;
    }

    public void getTemp()
    {
        this.temp = 50;
        evaluateTemp();
    }
    public void getTemp(double temp)
    {
        this.temp = temp;
        evaluateTemp();
    }

    private boolean evaluateTemp()
    {
        if (temp >= MAX_TEMP)
        {
            System.out.println("ALARM! Die Temperatur des Sensors " + sensNumb + " liegt über der Maximaltemperatur! Nämlich bei " + temp);
            return false;
        }
        return true;
    }
}

