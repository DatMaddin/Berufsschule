import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game
{
    private int[] scores = new int[2];
    private String[] names = new String[2];

    private boolean isSingleplayer;

    private Figure[] fig;

    public Game (String nameP1)
    {
        this.names[0] = nameP1;
        this.names[1] = "PC";
        this.isSingleplayer = true;

        this.scores[0] = 0;
        this.scores[1] = 0;

        this.setFigures();
    }

    public Game (String nameP1, String nameP2)
    {
        this.names[0] = nameP1;
        this.names[1] = nameP2;
        this.isSingleplayer = false;

        this.scores[0] = 0;
        this.scores[1] = 0;

        this.setFigures();
    }


    public void gameOver()
    {
        String text = "==SPIELZUSAMMENFASSUNG==\n\n";
        text += "Spieler 1 [" + names[0] + "] = " + scores[0] + "\n";
        text += "Spieler 2 [" + names[1] + "] = " + scores[1] + "\n\n\n";

        if (scores[0] > scores[1])
        {
            text += "==> Spieler 1 [" + names[0] + "] hat gewonnen!!";
        }
        else if (scores[1] > scores[0])
        {
            text += "==> Spieler 2 [" + names[1] + "] hat gewonnen!!";
        }
        else
        {
            text += "==> Unentschieden!";
        }

        JOptionPane.showMessageDialog(null, text);
    }

    public void showScore()
    {
        String text = "==SCORE==\n\n";
        text += "Spieler 1 [" + names[0] + "] = " + scores[0] + "\n";
        text += "Spieler 2 [" + names[1] + "] = " + scores[1];

        JOptionPane.showMessageDialog(null, text);
    }

    public void performRound()
    {
        if (isSingleplayer)
        {
            Figure pickPC = fig[this.pickPC()];

            int pckPl;

            do
            {
                pckPl = this.pickPlayer(1);
            } while (pckPl < 0);

            Figure pickPlayer = fig[pckPl];

            this.checkWhoWins(pickPlayer, pickPC);
        }
        else
        {
            int pckPl1;
            int pckPl2;

            do
            {
                pckPl1 = this.pickPlayer(1);
            } while (pckPl1 < 0);

            do
            {
                pckPl2 = this.pickPlayer(2);
            } while (pckPl2 < 0);

            Figure pickPlayer1 = fig[pckPl1];
            Figure pickPlayer2 = fig[pckPl2];

            this.checkWhoWins(pickPlayer1, pickPlayer2);
        }
    }


    private int pickPlayer(int playerNumb)
    {
        String playerPick = JOptionPane.showInputDialog("SPIELER " + (playerNumb ) +
        " [" + names[playerNumb - 1] + "] \nBitte gib deine Auswahl ein!\n\n" +
                "0 = Schere\n1 = Stein\n2 = Papier\n3 = Echse\n4 = Spock");

        Integer pick = this.tryParseInt(playerPick);


        if (pick == null)
        {
            JOptionPane.showMessageDialog(null, "FALSCHEINGABE!\n\nEingabe war ein Buchstabe.");
            return -1;
        }

        if (pick < 0 || pick > fig.length - 1)
        {
            JOptionPane.showMessageDialog(null, "FALSCHEINGABE!\n\nEingabe lag außerhalb des möglichen.");
            return -1;
        }
        else
        {
            return pick;
        }
    }

    private int pickPC()
    {
        return ThreadLocalRandom.current().nextInt(0, fig.length);
    }

    private void checkWhoWins(Figure pick1, Figure pick2)
    {
        if (pick1.type == pick2.type)
        {
            // Unentschieden --> nichts machen
            JOptionPane.showMessageDialog(null, "Unentschieden! Keiner hat gewonnen!");
            return;
        }

        else
        {
            if (pick1.canBeat.contains(pick2.type))
            {
                // 1 hat gewonnen
                this.scores[0] ++;
                JOptionPane.showMessageDialog(null, "Spieler 1 ["+ names[0] +  "] hat gewonnen!");
            }
            else if (pick2.canBeat.contains(pick1.type))
            {
                // 2 hat gewonnen
                this.scores[1]++;
                JOptionPane.showMessageDialog(null, "Spieler 2 ["+ names[1] +  "] hat gewonnen!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Da ist irgendwas schief gelaufen :/");
            }
        }
    }

    private void setFigures()
    {
        fig = new Figure[5];

        fig[0] = new Figure(figureTypes.SCHERE);
        fig[1] = new Figure(figureTypes.STEIN);
        fig[2] = new Figure(figureTypes.PAPIER);
        fig[3] = new Figure(figureTypes.ECHSE);
        fig[4] = new Figure(figureTypes.SPOCK);
    }

    private Integer tryParseInt(String s)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }


    public int getScoreP1() { return this.scores[0]; }
    public int getScoreP2() { return this.scores[1]; }
    public String getNameP1() { return this.names[0]; }
    public String getNameP2() { return this.names[1]; }

}
