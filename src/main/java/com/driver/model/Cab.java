package com.driver.model;

import javax.persistence.*;

//A Cab entity that has information about the per-km rate and the availability of the cab.

@Entity
public class Cab{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int perKmRate;

    private boolean available;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Cab() {
    }
    public Cab(int perKmRate, boolean available) {
        this.perKmRate = perKmRate;
        this.available = available;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}