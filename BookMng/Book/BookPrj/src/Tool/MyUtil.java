/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

/**
 *
 * @author Asus
 */
import java.util.Scanner;

public class MyUtil {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static String readString(String message, int minL, int maxL) {
        if (minL > maxL) {
            int t = minL;
            minL = maxL;
            maxL = t;
        }
        String input = "";
        boolean OK = true;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            int L = input.length();
            if (L < minL || L > maxL) {
                OK = false;
            }
        } while (!OK);
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean OK = true;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            OK = validStr(input, pattern);
        } while (!OK);
        return input;
    }

    public static double readDouble(String message, double min) {
        String input;
        double result;
        boolean OK;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            result = Double.parseDouble(input);
            OK = result >= min;
        } while (!OK);
        return result;
    }

    public static int readInt(String message, int min) {
        String input;
        int result;
        boolean OK;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            result = Integer.parseInt(input);
            OK = result >= min;
        } while (!OK);
        return result;
    }
}
