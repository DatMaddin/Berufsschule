public class Punkt
{
    private double x;
    private double y;
    
    public Punkt ()
    {
        this.x = 0;
        this.y = 0;
    }
    
    public Punkt (double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Punkt (Punkt p)
    {
        this.x = p.x;
        this.y = p.y;
    }
    
    // die Methode distance ohne Parameter berechnet den Abstand zum Koordinatenursprung (0,0)
    public double distance ()
    {
        // "lange" Schreibweise:
        double dis;
        dis = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        return dis;
    }
    
    // ÜBERLADUNG: mit den beiden Parametern x und y wird der Abstand zu diesem Punkt berechnet
    public double distance (double x, double y)
    {
        // verkürzte Schreibweise:
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
    
    // ÜBERLADUNG: mit dem Parameter eines weiteren Punkt-Objektes, wird der Abstand zu diesem berechnet
    public double distance (Punkt p)
    {
        // verkürzte Schreibweise:
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }
    
    
    
    // GETTER AND SETTER
    // zu deutsch: Abfragemethoden / Änderungsmethoden
    
    // "lange" Schreibweise
    public double getX ()
    {
        return this.x;
    }
    public void setX (double val)
    {
        this.x = val;
    }
    
    // "kurze" Schreibweise
    public double getY() { return this.y; }
    public void setY(double val) { this.y = val; }
}

