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

/**
 *
 * @author Asus
 */
public class ProductList extends ArrayList<Product> {

    String fName = "src\\data\\products.txt";

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
                String productID = stk.nextToken().trim().toUpperCase();
                String productName = stk.nextToken().trim().toUpperCase();
                String unit = stk.nextToken().trim();
                String origin = stk.nextToken().trim().toUpperCase();
                double price = Double.parseDouble(stk.nextToken().trim());
                Product p = new Product(productID, productName, unit, origin, price);
                this.add(p);

            }
            bf.close();
            fr.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

     public void writeToFile() throws Exception {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Product p : this) {
                pw.println(p);
            }
            pw.close();
            System.out.println("Writing file: DONE!");
        }
    }
     
    public void print() {
        for (Product p : this) {
            System.out.println(p);
        }
    }
}
