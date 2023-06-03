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
public class Publisher {
    String publisherId;
    String publisherName;
    String phone;

    public Publisher(String publisherId, String publisherName, String phone) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.phone = phone;
    }

    public Publisher(String publisherId) {
        this.publisherId = publisherId;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object obj) {
        Publisher p = (Publisher) obj;
        return this.publisherId.equalsIgnoreCase(p.publisherId);
    }

    @Override
    public String toString() {
        return this.publisherId + " | " + this.publisherName + " | " + this.phone;
    }

}
