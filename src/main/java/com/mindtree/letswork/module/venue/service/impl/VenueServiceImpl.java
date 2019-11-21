package com.mindtree.letswork.module.venue.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.CityNotFoundException;
import com.mindtree.letswork.module.venue.exception.InvalidDateException;
import com.mindtree.letswork.module.venue.exception.InvalidVenueTypeException;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.exception.VenueNotFoundException;
import com.mindtree.letswork.module.venue.repository.VenueRepo;
import com.mindtree.letswork.module.venue.service.VenueService;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepo venueRepo;

	@Override
	public List<Venue> getFinalSearchedVenues(String type, Date date, int capacity, String city) throws VenueException {
		List<Venue> venues = new ArrayList<>();
		venues = venueRepo.findAll();

		// check venue type
		if (type.equalsIgnoreCase("Meeting") || type.equalsIgnoreCase("Conference") || type.equalsIgnoreCase("Training")
				|| type.equalsIgnoreCase("Workshop"))
			venues = venues.stream().filter(venue -> type.equalsIgnoreCase(venue.getVenueType()))
					.collect(Collectors.toList());
		else
			throw new InvalidVenueTypeException("Invalid venue type");

		// check date
		if (checkDate(date))
			venues = venues.stream().filter(venue -> checkAvailability(venue, date)).collect(Collectors.toList());
		else
			throw new InvalidDateException("Invalid date");

		// check if city exists in database
		if (!getCities().contains(city))
			throw new CityNotFoundException("No venues for this city");
		else
			venues = venues.stream().filter(venue -> city.equalsIgnoreCase(venue.getCity()))
					.collect(Collectors.toList());

		// filter according to capacity
		venues = venues.stream().filter(venue -> venue.getCapacity() >= capacity).collect(Collectors.toList());

		return venues;
	}

	@SuppressWarnings("deprecation")
	public boolean checkDate(Date date) {
		java.util.Date currentDate = new java.util.Date();
		Date formattedDate = new Date(currentDate.getYear(), currentDate.getMonth(), currentDate.getDate());
		if (formattedDate.compareTo(date) > 0)
			return false;
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean checkAvailability(Venue venue, java.sql.Date date) {
		boolean booked = true;
		for (Booking b : venue.getBookings()) {
			if (date.getMonth() == b.getDate().getMonth() && date.getDay() == b.getDate().getDay()
					&& date.getYear() == b.getDate().getYear()) {
				booked = false;
			}
		}
		return booked;
	}

	@Override
	public Set<String> getCities() {
		Set<String> cities = new HashSet<String>();
		venueRepo.findAll().forEach(venue -> cities.add(venue.getCity()));
		return cities;
	}

	@Override
	public Venue getVenueDetails(int id) throws VenueException {
		try {
		Venue venue = venueRepo.findById(id).get();
		return venue;
		}
		catch(Exception ex){
			throw new VenueNotFoundException("Venue not found");
		
		}
	}

	public List<Venue> getAllVenues() {
		return this.venueRepo.findAll();
	}

	public boolean updateVenue(Venue venue) throws VenueException {
		Optional<Venue> v = this.venueRepo.findById(venue.getVenueId());
		if (v.isPresent()) {
			v = Optional.of(this.venueRepo.save(venue));
			if (v.isPresent()) {
				return true;
			} else {
				throw new VenueException("Venue id: " + venue.getVenueId() + " could not get updated");
			}
		} else {
			throw new VenueException("Venue id: " + venue.getVenueId() + " is invalid");
		}
	}

	public boolean deleteVenue(int venueId) {
		try {
			this.venueRepo.deleteById(venueId);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("Service operation \'delete\' failed: venueId: " + venueId);
		}
		return true;
	}

	public Venue insertVenue(Venue venue) {
		Venue venueSave = null;
		try {
			venueSave = this.venueRepo.save(venue);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("Service operation \'delete\' failed: venueId: " + venue);
		}
		return venueSave;
	}
}
