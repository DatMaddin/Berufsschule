/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berufsschule;

import java.util.Scanner;

/**
 *
 * @author mwage
 */
public class Iteration
{
    public static double MAX = 50;
    public static double MIN = 0;
    
    
    public static void doWhile ()
    {
        Scanner s = new Scanner (System.in);
        
        double temp;
        do
        {
            temp = s.nextDouble();
        }
        while ((MAX >= temp) && (temp >= MIN));
        System.out.println("Boss, wir haben hier ein großes Problem!");
        System.out.println("ALARM");
    }
}
