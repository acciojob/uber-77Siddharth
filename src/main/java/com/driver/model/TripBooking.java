package com.driver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;

//A TripBooking entity that stores information about the trip such as the from and to locations,
// the distance, the status of the trip, and the bill.
@ComponentScan
@Entity
public class TripBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripBookingId;

    private String fromLocation;

    private String toLocation;

    private int distanceInKm;
    private int bill;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customerId;
//    @ManyToOne
//    @JoinColumn
//    private Customer customerId;

    @ManyToOne(targetEntity = Driver.class)
    private Driver driverId;
//    @ManyToOne
//    @JoinColumn
//    private Driver driverId;

    @Enumerated(EnumType.STRING)
    TripStatus status;

    public TripBooking() {
    }

    public TripBooking(String fromLocation, String toLocation, int distanceInKm) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
    }

    public int getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public Customer getCustomer() {
        return getCustomerId();
    }

    public Customer getCustomerId() {
        return customerId;
    }
    public void setCustomer(Customer customerId) {
         setCustomerId(customerId);
         return;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Driver getDriver() {
        return getDriverId();
    }
    public Driver getDriverId() {
        return driverId;
    }

    public void setDriver(Driver driverId) {
        setDriverId(driverId);
    }

    public void setDriverId(Driver driverId) {
        this.driverId = driverId;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {

        this.status = status;
    }
}