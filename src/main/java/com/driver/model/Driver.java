package com.driver.model;

import javax.persistence.*;
import java.util.List;

//A Driver entity that can register new drivers,
// delete existing drivers and update the status of their cab (available or unavailable).
@Entity
public class Driver{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    private String mobile;

    private String password;

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Cab cab;


    @OneToMany(mappedBy = "driverId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn
    private List<TripBooking> tripBookingList;

    public Driver() {
    }

    public Driver(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public void setDriver(int driverId) {
        setDriverId(driverId);
    }

        public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }
}