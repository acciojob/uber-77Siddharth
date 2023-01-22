package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		customerRepository2.deleteById(customerId);
	}
//Check booking function . giving 2 entries in DB.
	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		List<Driver> listOfAllDrivers = driverRepository2.findAll();
		listOfAllDrivers.sort((a,b)->a.getDriverId()- b.getDriverId());
		Driver foundFreeDriver = null;
		for (Driver driver:listOfAllDrivers){
			if (driver.getCab().getAvailable()==true) {
				foundFreeDriver = driver;
				  TripBooking booked = new TripBooking(fromLocation,toLocation,distanceInKm);
				  foundFreeDriver.getCab().setAvailable(false);
				  booked.setBill(foundFreeDriver.getCab().getPerKmRate() * distanceInKm);

				  Customer foundCustomer = customerRepository2.findById(customerId).get();
				  booked.setCustomerId(foundCustomer);
				  booked.setDriverId(driver);
				  foundCustomer.getTripBookingList().add(booked);
				  foundFreeDriver.getTripBookingList().add(booked);
//				  customerRepository2.save(foundCustomer);
//				  driverRepository2.save(foundFreeDriver);
				  tripBookingRepository2.save(booked);
				  return booked;
			}
		}
		throw new Exception("No cab available!");
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE).
		// If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query

	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking foundTrip = tripBookingRepository2.findById(tripId).get();
		foundTrip.getDriver().getCab().setAvailable(true);
		foundTrip.setStatus(TripStatus.CANCELED);
		tripBookingRepository2.save(foundTrip);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking foundTrip = tripBookingRepository2.findById(tripId).get();
		foundTrip.getDriver().getCab().setAvailable(true);
		foundTrip.setStatus(TripStatus.COMPLETED);
		tripBookingRepository2.save(foundTrip);
	}
}
