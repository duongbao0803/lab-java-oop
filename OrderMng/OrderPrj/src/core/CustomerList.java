/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import tools.myUtils;

/**
 *
 * @author Asus
 */
public class CustomerList extends ArrayList<Customer> {

    String fName = "src\\data\\customers.txt";

    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String customerID = stk.nextToken().trim().toUpperCase();
                String customerName = stk.nextToken().trim().toUpperCase();
                String customerAddress = stk.nextToken().trim().toUpperCase();
                String customerPhone = stk.nextToken().trim().toUpperCase();
                Customer c = new Customer(customerID, customerName, customerAddress, customerPhone);
                this.add(c);
            }
            bf.close();
            fr.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeToFile() throws Exception {
        if (this.isEmpty()) {
            System.out.println("Empty List");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Customer c : this) {
                pw.println(c);
            }
            pw.close();
            System.out.println("Writing file: DONE!");
        }
    }

    public void print() {
        if (this.isEmpty())
            System.out.println("Empty List!");
        for (Customer c : this) {
            System.out.println(c);
        }
    }

    public void search() {
        String ID;
        ID = myUtils.readPattern("ID of searched customer (Cxxx)", "C[\\d]{3}");
        int pos = this.indexOf(new Customer(ID));
        if (pos < 0) {
            System.out.println("This customer does not exist!");
        } else {
            System.out.println("Found: " + this.get(pos));
        }
    }

    public void addCustomer() {
        String ID, name, phone, address;
        System.out.println("Data of new customer");
        int pos;
        do {
            ID = myUtils.readPattern("Input ID (Cxxx)", "C[\\d]{3}");
            pos = this.indexOf(new Customer(ID));
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = myUtils.getString("Input Name: ", "Non-Empty");
        address = myUtils.getString("Input Address: ", "Non-Empty");
        phone = myUtils.readPattern("Input phone (10-12 digits)", "^[0-9]{10,12}$");
        Customer c = new Customer(ID, name, address, phone);
        this.add(c);
        System.out.println("Success Add!!!");

    }

    public void updateCustomer() {
        String ID, input;
        ID = myUtils.readPattern("Input ID (Cxxx)", "C[\\d]{3}");
        int pos = this.indexOf(new Customer(ID));
        if (pos < 0) {
            System.out.println("Customer is not found!");
        } else {
            Customer c = this.get(pos);
            System.out.println("New Customer attributes - Enter for skipping.");
            
            //customer name
            System.out.println("New customer name (Y - YES / Enter for skipping): ");
            input = myUtils.sc.nextLine().trim().toUpperCase();
            if (input.contains("Y")) {
                String newcustomerName = myUtils.getString("Input new customer name: ", "Non-empty");
                c.setCustomerName(newcustomerName);
            }
            
            //customer address
            System.out.println("New customer address (Y - YES / Enter for skipping): ");
            input = myUtils.sc.nextLine().trim().toUpperCase();
            if (input.contains("Y")) {
                String newcustomerAddress = myUtils.getString("Input new customer address: ", "Non-empty");
                c.setCustomerAddress(newcustomerAddress);
            }
            
            //customer phone
            System.out.println("New customer phone (Y - YES / Enter for skipping): ");
            input = myUtils.sc.nextLine().trim().toUpperCase();
            if (input.contains("Y")) {
                String newcustomerPhone = myUtils.readPattern("Input new customerPhone (10-12 digits)", "^[0-9]{10,12}$");
                c.setCustomerPhone(newcustomerPhone);
            }
            System.out.println("Success Update!!!");
        }
          
    }
}
