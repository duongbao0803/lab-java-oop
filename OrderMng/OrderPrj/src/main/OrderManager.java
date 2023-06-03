/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import core.CustomerList;
import core.OrderList;
import core.ProductList;
import tools.Menu;
import tools.myUtils;
import static tools.myUtils.sc;

/**
 *
 * @author Asus
 */
public class OrderManager {

    public static void main(String[] args) throws Exception {
        Menu mnu = new Menu();
        mnu.add(" Product: Print");
        mnu.add(" Customer: Print");
        mnu.add(" Customer: Search");
        mnu.add(" Customer: Add");
        mnu.add(" Customer: Update");
        mnu.add(" Customer: Save to file");
        mnu.add(" Order: Print in ascending order of Customer Name");
        mnu.add(" Order: Print pending Orders");
        mnu.add(" Order: Add");
        mnu.add(" Order: Update an Order (Update/Delete)");
        mnu.add(" Order: Save to file");
        int nChoice = mnu.size();
        ProductList pL = new ProductList();
        pL.readFromFile();
        CustomerList cL = new CustomerList();
        cL.readFromFile();
        OrderList oL = new OrderList(pL, cL);
        oL.readFromFile();
        int userChoice;
        boolean pChange = false;
        boolean cChange = false;
        boolean oChange = false;
        do {
            System.out.println("\t\n---- BOOK MANAGER----");

            userChoice = mnu.getUserChoice();
            switch (userChoice) {
                case 1:
                    System.out.println("\t---- PRODUCT LIST ----");
                    pL.print();
                    break;

                case 2:
                    System.out.println("\t---- CUSTORMER LIST ----");
                    cL.print();
                    break;

                case 3:
                    cL.search();
                    break;
                case 4:
                    cL.addCustomer();
                    cChange = true;
                    break;

                case 5:
                    cL.updateCustomer();
                    cChange = true;
                    break;

                case 6:
                    cL.writeToFile();
                    cChange = false;
                    break;

                case 7:
                    System.out.println("\n\t---- ORDER LIST ----");
                    oL.print();
                    break;

                case 8:
                    System.out.println("\t---- PENDING ORDER LIST ----");
                    oL.pendingOrder();
                    break;

                case 9:
                    oL.addOrder();
                    oChange = true;
                    break;

                case 10:
                    System.out.println("1 - Update \n2 - Delete");
                    String choice = sc.nextLine();
                    if (choice.contains("1")) {
                        oL.updateOrder();
                    } else {
                        oL.delete();
                    }
                    oChange = true;
                    break;

                case 11:
                    oL.writeToFile();
                    oChange = false;
                    break;
            }
        } while (userChoice > 0 && userChoice <= nChoice);
        String response;
        if (pChange == true || cChange == true || oChange == true) {
            System.out.println("Data changed. Save file?- Y/N: ");
            response = myUtils.sc.nextLine().trim().toUpperCase();
            if (response.contains("Y")) {
                if (pChange) {
                    pL.writeToFile();
                }
                if (cChange) {
                    cL.writeToFile();
                }
                if (oChange) {
                    oL.writeToFile();
                }
            }
            System.out.println("Bye!");
        }
    }
}
