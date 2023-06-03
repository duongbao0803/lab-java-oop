/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public int getUserChoice() {
        int choice;
        int n = this.size();
        for (int i = 1; i <= n; i++) {
            System.out.println(i + " -" + this.get(i - 1));
        }
        System.out.println((n + 1) + " - Quit");
        System.out.println("--------------------------");
        System.out.println("Your Choice: (1 -> " + (n + 1) + "): ");
        choice = Integer.parseInt(myUtils.sc.nextLine());
        return choice;

    }

    public static int int_getChoice(ArrayList options) {
        int response;
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "-" + options.get(i));
        }
        System.out.println("Choose 1 -> " + options.size() + ":");
        Scanner sc = new Scanner(System.in);
        response = Integer.parseInt(sc.nextLine());
        return response;
    }

    public static <T> T ref_getChoice(ArrayList<T> options) {
        int response;
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > options.size());
        return options.get(response - 1);

    }
}
