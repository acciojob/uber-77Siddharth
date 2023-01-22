package com.driver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

//A Customer entity that can register new customers,
// delete existing customers, and book trips.
// When a customer books a trip,
// the system should assign the nearest available driver and cab to the customer.
// The customer should also be able to cancel or complete the trip.

@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String mobile;

    private String password;

    @OneToMany(mappedBy = "customerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)

//    @JoinColumn
    private List<TripBooking> tripBookingList;

    public Customer(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Customer() {
    }

    public int getCustomer(){
        return getCustomerId();
    }
    public void setCustomer(int customerId){
        setCustomerId(customerId);
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }


    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}