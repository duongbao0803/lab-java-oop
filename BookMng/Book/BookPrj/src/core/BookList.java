/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Tool.MyUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Asus
 */

public class BookList extends ArrayList<Book> {
    List<Book> list = new ArrayList<>();
    
    String fName = "src\\data\\books.dat";
    Publist pL;
    
    public BookList(Publist pL) {
        super();
        this.pL = pL;
    }

    public void readFromFile() {
        File f = new File(fName); 
               if (!f.exists()){
        System.out.println("File is not existed");
        System.exit(0);
    }
        try {
            FileReader fr = new FileReader(f); 
            BufferedReader bf = new BufferedReader(fr); 
            String line; 
            while ((line = bf.readLine())!=null){
                if(!line.trim().isEmpty()){
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String bookId = stk.nextToken().trim().toUpperCase();
                    String bookName = stk.nextToken().trim().toUpperCase();
                    double bookPrice = Double.parseDouble(stk.nextToken().trim());
                    int quantity = Integer.parseInt(stk.nextToken().trim());
                    String publisherId = stk.nextToken().trim().toUpperCase();
                    Book b = new Book(bookId, bookName, bookPrice, quantity, publisherId);
                    this.add(b);
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
            PrintWriter pw = new PrintWriter(fName);
            for (Book b: this)
                pw.println(b);
            pw.close();
            System.out.println("Writing file: DONE.");
        }
    }

    public void addBook(){
        String bookId, bookName, publisherId;
        double bookPrice;
        int quantity, pos;
   
        System.out.println("Data of new book:");
        do {
             bookId = MyUtil.readPattern("BookID", "B[\\d]{5}");
             pos = this.indexOf(new Book(bookId));
             if (pos >= 0) 
                 System.out.println("ID is a duplicated");
        }
        while (pos >= 0);
        bookName = MyUtil.readString("Book name", 5, 30);
        bookPrice = MyUtil.readDouble("Price", 0);
        quantity = MyUtil.readInt("Quantity", 0);
        do {
            publisherId = MyUtil.readPattern("Publisher ID", "P[\\d]{5}");
        }
        while (pL.indexOf(new Publisher(publisherId)) < 0);
        Book newBook = new Book(bookId, bookName, bookPrice, quantity, publisherId);
        this.add(newBook);
        System.out.println("Add.");
    }

    public void search(){
        String ID; 
        System.out.println("ID of searched book:");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Book(ID));
        if (pos < 0)
            System.out.println("Not found!");
        else 
            System.out.println("Found: " + this.get(pos));
    }

    public void removeBook() {
        String ID;
        System.out.println("ID of search book:");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Book(ID));
        if(pos<0) 
            System.out.println("Not found!");
        else {
            this.remove(pos);
            System.out.println("Removed.");
    }
}

    public void updateBook(){
        String bookId;
        String input;
        System.out.println("ID of updated book:");
        bookId = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Book(bookId));
        if (pos < 0)
            System.out.println("Not found!");
        else {
            Book b = this.get(pos);
            System.out.println("New book attributes - Enter for skipping.");
            boolean OK;
            int L;
            do {
                System.out.print("New name (5..30 chars): ");
                input = MyUtil.sc.nextLine().trim().toUpperCase();
                L = input.length();
                OK = L == 0 || (L >= 5 && L <= 30);
            } while (!OK);
            if (L > 0) 
                b.setBookName(input);
            
        double newPrice = -1.0;
            do {
                System.out.print("New price (Enter of number >= 0): ");
                input = MyUtil.sc.nextLine().trim();
                if (!input.isEmpty())
                    newPrice = Double.parseDouble(input);
                OK = input.isEmpty() || newPrice >= 0.0;
            } while (!OK);
            if (newPrice >= 0) 
                b.setPrice(newPrice);       
        
        int newQuantity = -1;  
            do {
                System.out.print("New quantity (Enter of number >= 0): ");
                input = MyUtil.sc.nextLine().trim();
                if (!input.isEmpty())
                    newQuantity = Integer.parseInt(input);
                OK = input.isEmpty() || newQuantity >= 0;
            } while (!OK);
            if (newQuantity >= 0) {
                b.setQuantity(newQuantity);
                b.setStatus(newQuantity > 0 ? "Available" : "Not availble");
            }
            
            do {
                System.out.print("Publisher ID: ");
                OK = true;
                input = MyUtil.sc.nextLine().trim().toUpperCase();
                if (!input.isEmpty()) 
                break;
            else
                OK = pL.indexOf(input) >= 0; 
            }  
            while (!OK);
            if (!input.isEmpty()) 
                b.setPublisherId(input);
                System.out.println("Updated.");
        }
    }

    public void print(){
        for (Book b: this) 
            System.out.println(b);
    }

    public boolean hasPublisher(String pubId) {
        for (Book b: this)
            if ( b.getPublisherId().equalsIgnoreCase(pubId))
                return true;
        return false;
    }   
}