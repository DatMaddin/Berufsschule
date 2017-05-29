public class Serverschrank
{
    private String bezeichnung;
    private String standort;
    private int moeglHoeheneinheiten;
    private int belegteHoeheneinheiten;

    public Serverschrank(String bezeichnung, String standort, int moeglHoeheneinheiten)
    {
        this.bezeichnung = bezeichnung;
        this.standort = standort;
        this.moeglHoeheneinheiten = moeglHoeheneinheiten;
        this.belegteHoeheneinheiten = 0;
    }

    // neue Höheneinheit belegen (false =  nicht möglich, true = möglich + durchgeführt)
    public boolean belegeHoeheneinheit(int anzahl)
    {
        // Kontrolle ob das Belegen möglich ist
        if (this.getFreieHoeheneinheiten() - anzahl < 0)
        {
            return false;
        }
        else
        {
            this.belegteHoeheneinheiten += anzahl;
            return true;
        }
    }

    // ermitteln und zurückgeben der freien Höheneinheiten
    public int  getFreieHoeheneinheiten()
    {
        return this.moeglHoeheneinheiten - this.belegteHoeheneinheiten;
    }


    // GETTER
    public String getBezeichnung() { return this.bezeichnung; }
    public String getStandort() { return this.standort; }
    public int getMoeglHoeheneinheiten() { return this.moeglHoeheneinheiten; }
    public int getBelegteHoeheneinheiten() { return this.belegteHoeheneinheiten; }

    // SETTER
    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }
    public void setStandort(String standort) { this.standort = standort; }

    // ich möchte an dieser Stelle nicht ermöglichen, dass die Höheneinheiten von außerhalb gesetzt werden können,
    // da ein Serverschrank im Normalfall nicht ohne Weiteres einfach so erweitert werden kann
    // ==> Zugriffsschutz
}
