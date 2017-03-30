package Buch_S_234_A_1;

public class Punkt
{
    private double x;
    private double y;

    public Punkt()
    {
        this.x = 0;
        this.y = 0;
    }

    public Punkt(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Punkt(Punkt p)
    {
        this.x = p.x;
        this.y = p.y;
    }


    public double distanceToOrigin()
    {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y,2));
    }



    // GETTER AND SETTER
    public double getX() { return this.x; }
    public void setX(double val) { this.x = val; }

    public double getY() { return this.y; }
    public void setY(double val) { this.y = val; }
}
