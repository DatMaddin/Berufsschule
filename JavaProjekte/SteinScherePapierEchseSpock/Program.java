import javax.swing.*;

public class Program
{
    /**
     * @author M. Wagenführ
     *
     * Umsetzung des Spiels Stein Schere Papier Echse Spock als Java-Anwendung
     */
    public static void main (String args[])
    {
        Game game;
        boolean cancel = false;

        int result = JOptionPane.showConfirmDialog(null,"Möchtest du alleine spielen?");

        if (result == JOptionPane.YES_OPTION)
        {
            String name1 = JOptionPane.showInputDialog(null, "Bitte gib deinen Namen ein!");
            game = new Game(name1);
        }
        else
        {
            String name1 = JOptionPane.showInputDialog(null, "Name Spieler 1.");
            String name2 = JOptionPane.showInputDialog(null, "Name Spieler 2.");

            game = new Game(name1, name2);
        }


        while (!cancel)
        {
            game.performRound();

            result = JOptionPane.showConfirmDialog(null,"Möchtest du das Spiel beenden?");
            if (result == JOptionPane.YES_OPTION)
            {
                cancel = true;

                game.gameOver();
            }
            else
            {
                result = JOptionPane.showConfirmDialog(null, "Möchtest du den Score sehen?");
                if (result == JOptionPane.YES_OPTION)
                {
                    game.showScore();
                }
            }
        }
    }
}
