/**
 * @author M. Wagenführ
 *
 * Einführung in die Objekt orientierte Programmierung
 *
 * Lösung der Aufgabe: Java-Buch Seite 234 Aufgabe 6.1
 *
 *
 * HINWEIS: in Klassen verwende ich gerne das Schlüsselwort "this". Dies gibt einfach nur an das das darauf folgende
 * Attribut zu dieser Klasse gehört (z.B. this.x). So kann man schnell auf den ersten Blick sehen, ob man jetzt mit
 * einem Attribut / Methode aus der Klasse oder von irgendwo anders her arbeitet.
 *
 * Bei einem Konstruktor wie Punkt (x,y) ist es hier sogar zwingend notwendig, da ich für die Übergabeparameter und für
 * die Klassenattribute jeweils den gleichen Namen verwendet habe!
 */
public class EinfuehrungOOP
{
    // Haupteinstiegspunkt der Anwendung
    public static void main (String[] args)
    {
        Punkt a = new Punkt(10.0, 20.0);
        Punkt b = new Punkt(a);
        Punkt c = new Punkt();
        
        c.setX(30);
        c.setY(40);
    
        System.out.println("Punkt B<" + b.getX() + "|" + b.getY() + ">");
        System.out.println("Abstand von Punkt b zu <0|0>: " + b.distance());
    }
}
