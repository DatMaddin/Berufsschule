import javax.swing.*;

/**
 * @author M. Wagenführ (11.05.2017)
 */
public class Program
{
    public static void main(String[] args)
    {
        Serverschrank schrank1 = new Serverschrank("Schrank 1", "AGS Etage 1", 10);
        Serverschrank schrank2 = new Serverschrank("Schrank 2", "AGS Etage 2", 5);

        schrank1.setBezeichnung("Test Schrank 1");
        schrank1.setStandort("Test Etage 12");

        String text1 = schrank1.getBezeichnung() + "\n" + schrank1.getStandort() + "\n" + schrank1.getMoeglHoeheneinheiten();
        String text2 = schrank2.getBezeichnung() + "\n" + schrank2.getStandort() + "\n" + schrank2.getMoeglHoeheneinheiten();

        JOptionPane.showMessageDialog(null, text1);
        JOptionPane.showMessageDialog(null, text2);

        // Belegen Schrank 1 und prüfen der freien Höheneinheiten
        if (schrank1.belegeHoeheneinheit(7))
        {
            JOptionPane.showMessageDialog(null, "Belegen war erfolgreich!");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Belegen nicht möglich!");
        }
        JOptionPane.showMessageDialog(null, "Belegte Höheneinheiten:\n" +  schrank1.getBelegteHoeheneinheiten());
        JOptionPane.showMessageDialog(null, "Freie Höheneinheiten:\n" + schrank1.getFreieHoeheneinheiten());

        // Prüfen der Kontrolle der Belegbarkeit
        if (schrank1.belegeHoeheneinheit(4))
        {
            JOptionPane.showMessageDialog(null, "Belegen war erfolgreich!");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Belegen nicht möglich!");
        }


        // Prüfen der Funktion der Kontrolle auf Belegbarkeit
        if (schrank2.belegeHoeheneinheit(6))
        {
            JOptionPane.showMessageDialog(null, "Belegen war erfolgreich!");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Belegen nicht möglich!");
        }
    }
}
