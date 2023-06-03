/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Asus
 */
import Tool.MyUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Publist extends ArrayList<Publisher> {
    String fname = "src\\data\\publishers.dat";
    
    public void readFromFile () {
        File f = new File(fname); 
        if (!f.exists()){
        System.out.println("File is not existed");
        System.exit(0);
    }
        try {
            FileReader fr = new FileReader(f); 
            BufferedReader bf = new BufferedReader(fr); 
            String line; 
            while ((line = bf.readLine())!= null){
                if(!line.trim().isEmpty()){
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String ID = stk.nextToken().trim().toUpperCase();
                    String name = stk.nextToken().trim().toUpperCase();
                    String phone = stk.nextToken().trim().toUpperCase();
                    Publisher p = new Publisher(ID, name, phone);
                    this.add(p);
                }
            }
            bf.close();
            fr.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
 
    public void writeToFile () throws Exception {
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            PrintWriter pw = new PrintWriter(fname);
            for (Publisher p: this)pw.println(p);
            pw.close();
            System.out.println("Writing file: DONE.");
            
        }
    }
  
    public void addPublisher(){
        String ID, name, phone; 
        System.out.println("Data of new publisher:");
        int pos; 
        do {
            ID = MyUtil.readPattern("ID", "P[\\d]{5}");
            pos = this.indexOf(new Publisher(ID));
            if (pos >= 0)
                System.out.println("ID is duplicated!");
    }
        while (pos >= 0);
        name = MyUtil.readString("Name", 5, 30);
        phone = MyUtil.readPattern("Phone", "[\\d]{10,12}");
        Publisher p = new Publisher(ID, name, phone);
        this.add(p);
        System.out.println("Added.");
    }
 
    public void search(){
        String ID;
        System.out.println("ID of searched publisher:");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos  = this.indexOf(new Publisher(ID));
        if (pos < 0) 
            System.out.println("Not found!");
        else 
            System.out.println("Found: " + this.get(pos));
    }
  
    public void removePublisher(){
        String ID;
        System.out.println("ID of searched publisher:");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Publisher(ID));
        if(pos < 0)
            System.out.println("Not found:");
        else {
            this.remove(pos);
            System.out.println("Removed.");
        }
    }
    
    public void updatePublisher() {
         String ID, name, phone;
         System.out.println("ID of searched publisher: ");
         ID = MyUtil.sc.nextLine().trim().toUpperCase();
         int pos = this.indexOf(new Publisher(ID));
         if  (pos < 0)
             System.out.println("Not found");
         else {
             Publisher p = this.get(pos);
             name = MyUtil.readString("New Name", 5, 30);
             phone = MyUtil.readPattern("New phone", "[\\d]{10,12}");
             p.setPublisherName(name);
             p.setPhone(phone);
             System.out.println("Updated.");
        }
    }
    
    public void print() {
        for (Publisher p: this) 
            System.out.println(p);
    }
}
    

