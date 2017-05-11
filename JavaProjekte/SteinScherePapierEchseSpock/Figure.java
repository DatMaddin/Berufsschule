import java.util.ArrayList;

enum figureTypes {SCHERE, STEIN, PAPIER, ECHSE, SPOCK}

public class Figure
{
    public figureTypes type;
    public ArrayList<figureTypes> canBeat;

    public Figure(figureTypes type)
    {
        this.type = type;

        this.canBeat = new ArrayList<>();
        this.initializeCanBeat();
    }

    private void initializeCanBeat()
    {
        if (this.type == figureTypes.SCHERE)
        {
            canBeat.add(figureTypes.PAPIER);
            canBeat.add(figureTypes.ECHSE);
        }
        else if (this.type == figureTypes.STEIN)
        {
            canBeat.add(figureTypes.SCHERE);
            canBeat.add(figureTypes.ECHSE);
        }
        else if (this.type == figureTypes.PAPIER)
        {
            canBeat.add(figureTypes.STEIN);
            canBeat.add(figureTypes.SPOCK);
        }
        else if (this.type == figureTypes.ECHSE)
        {
            canBeat.add(figureTypes.SPOCK);
            canBeat.add(figureTypes.PAPIER);
        }
        else if (this.type == figureTypes.SPOCK)
        {
            canBeat.add(figureTypes.STEIN);
            canBeat.add(figureTypes.SCHERE);
        }
    }
}
