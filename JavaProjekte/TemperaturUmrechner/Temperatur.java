package temperaturumrechnen;

/**
 * @author M. Wagenführ
 * 
 * Klasse zum arbeiten mit verschiedenen Temperatur-Angaben.
 * Beinhaltet standardmäßig eine Temperatur-Angabe in Celsius.
 * Kann entsprechend in Kelvin und Fahrenheit umgerechnet werden.
 */
public class Temperatur
{
    private float tempInC = 0.0f; // Temperatur in Celsius
    
    // Konstruktor zum Anlegen mit einer Celsius-Temperatur
    public Temperatur (float temp)
    {
        this.tempInC = temp;
    }
    
    // gibt die Temperatur in Kelvin zurück
    public float getTempInK ()
    {
        return this.tempInC + 273.15f;
    }
    
    // gibt die Temperatur in Fahrenheit zurück
    public float getTempInF ()
    {
        return this.tempInC * 1.8f + 32;
    }
    
    // Getter und Setter
    public float getTempInC () { return this.tempInC; }
    public void setTempInC (float val) { this.tempInC = val; }
}
