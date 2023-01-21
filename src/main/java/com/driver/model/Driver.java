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

    @OneToOne
    @JoinColumn
    private Cab cab;

    public Cab getCab() {
        return cab;
    }

    @OneToMany(mappedBy = "driverId",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList;

    public Driver() {
    }

    public int getDriverId() {
        return driverId;
    }

    public Driver(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
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
}