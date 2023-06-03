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
public class Order {

    String orderID, customerID, productID, orderDate;
    boolean status;
    int orderQuantity;

    public Order(String orderID, String customerID, String productID, int orderQuantity, String orderDate,  Boolean status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderDate = orderDate;
        this.orderQuantity = orderQuantity;
        this.status = status;
    }

    public Order(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(Object obj) {
        Order o = (Order) obj;
        return this.orderID.equalsIgnoreCase(o.orderID);
    }

    @Override
    public String toString() {
        return this.orderID + " | " + this.customerID + " | " + this.productID
                + " | " + this.orderQuantity + " | " + this.orderDate + " | " + this.status;
    }

   

   

}
