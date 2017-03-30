package Buch_S_234_A_1;

public class Main_234_1
{
    public static void Do()
    {
        Punkt a = new Punkt(10.0, 20.0);
        Punkt b = new Punkt(a);
        Punkt c = new Punkt();

        c.setX(30);
        c.setY(40);


        System.out.println("Punkt B<" + b.getX() + "|" + b.getY() + ">");
        System.out.println("Abstand von Punkt b zu <0|0>: " + b.distanceToOrigin());
    }
}
