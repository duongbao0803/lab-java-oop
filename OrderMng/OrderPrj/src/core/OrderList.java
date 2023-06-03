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
import tools.*;

/**
 *
 * @author Asus
 */
public class OrderList extends ArrayList<Order> {

    String fName = "src\\data\\orders.txt";
    ProductList pL;
    CustomerList cL;

    public static ArrayList<Order> oL = new ArrayList<Order>();

    public OrderList(ProductList pL, CustomerList cL) {
        super();
        this.pL = pL;
        this.cL = cL;
    }

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
                String orderID = stk.nextToken().trim().toUpperCase();
                String customerID = stk.nextToken().trim().toUpperCase();
                String productID = stk.nextToken().trim().toUpperCase();
                int orderQuantity = Integer.parseInt(stk.nextToken().trim());
                String orderDate = stk.nextToken().trim().toUpperCase();
                Boolean status = Boolean.parseBoolean(stk.nextToken().trim());
                Order o = new Order(orderID, customerID, productID, orderQuantity, orderDate, status);
                this.add(o);
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
            for (Order o : this) {
                pw.println(o);
            }
            pw.close();
            System.out.println("Writing file: DONE!");
        }
    }

    public void addOrder() {
        String ID, customerID, productID, orderDate;
        boolean status;
        int orderQuantity;
        Product p = new Product();
        Customer c = new Customer();
        System.out.println("Data of new Order: ");
        int pos;
        do {
            ID = myUtils.readPattern("ID (Dxxx)", "D[\\d]{3}");
            pos = this.indexOf(new Order(ID));
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        p = Menu.ref_getChoice(pL);
        System.out.println("Your choice: " + p.toString());
        productID = p.getProductID();
        c = Menu.ref_getChoice(cL);
        System.out.println("Your choice: " + c.toString());
        customerID = c.getCustomerID();
        orderQuantity = myUtils.getInt("oderQuantity: ");
        orderDate = myUtils.getDate("Input Date: ", "Non-Empty");
        status = myUtils.readBool("Input status");
        Order o = new Order(ID, productID, customerID, orderQuantity, orderDate, status);
        this.add(o);
        System.out.println("Added!!!");
    }
    
    public void print() {
        if (this.isEmpty())
            System.out.println("Empty List!");
        for (Order o : this) {
            System.out.println(o);
        }
    }
    
    public void pendingOrder() {
        if (this.isEmpty())
            System.out.println("Empty List!");
        for (Order o : this) {
            if (o.isStatus()==false) 
                System.out.println(o.toString());
        }
    }

    public void updateOrder() {
        String ID, input;
        Boolean status;
        Customer c = new Customer();
        Product p = new Product();
        ID = myUtils.readPattern("ID (Dxxx)", "D[\\d]{3}");
        int pos = this.indexOf(new Order(ID));
        if (pos < 0) 
            System.out.println("Order does not exist");
        else {
            Order o = this.get(pos);
            System.out.println("New Order attributes - Enter for skiping");
            
            //New customer
            System.out.println("New customer (Y - YES / Enter for skipping): ");
            input = myUtils.sc.nextLine().trim().toUpperCase();
            if (input.contains("Y")) {
                c = Menu.ref_getChoice(cL);
                o.setCustomerID(c.getCustomerID());              
            }
            
            //New product
            System.out.println("New product (Y - YES / Enter for skipping): ");
            input = myUtils.sc.nextLine().trim().toUpperCase();
            if (input.contains("Y")) {
                p = Menu.ref_getChoice(pL);
                o.setProductID(p.getProductID());
            }      
           
            //Quantity
            System.out.println("New quantity (Y - YES / Enter for skipping): ");
            input = myUtils.sc.nextLine().trim().toUpperCase();
            if (input.contains("Y")) {
                int newQuantity = myUtils.getInt("New orderQuantity: ");
                o.setOrderQuantity(newQuantity);
            }     
            
            //Date
            String date ;
                System.out.println("New date (Y - YES / Enter for skipping: ");
                input = myUtils.sc.nextLine().trim().toUpperCase();
                if (input.contains("Y")) {
                date = myUtils.getDate("Input Date: ", "Non-Empty");
                o.setOrderDate(date);
                }
            
            //Status
                System.out.println("New status (T - TRUE / F - FALSE): ");
                input = myUtils.sc.nextLine().trim().toUpperCase();
                if (input.contains("T")) 
                    status = true;
                else {
                    status = false;
                }
                o.setStatus(status);
                System.out.println("Success!!!");
            }
           
        }
            
    
        
    public void delete() {
        String ID, input;
        ID = myUtils.readPattern("ID (D006)", "D[\\d]{3}");
        int pos = this.indexOf(new Order(ID));
        if (pos < 0) 
            System.out.println("Order does not exist");
        else {
            System.out.println("Remove Order - (Y/N)");
            input = myUtils.sc.nextLine().toUpperCase();
            if (input.contains("Y")) {
                this.remove(pos);
                System.out.println("Remove!!!");
            } else {
                System.out.println("Exist!!!");
            }
        }
    }

  
}
