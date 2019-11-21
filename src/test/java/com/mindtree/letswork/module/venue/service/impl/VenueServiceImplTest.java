package com.mindtree.letswork.module.venue.service.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.InvalidDateException;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.repository.VenueRepo;

@RunWith(SpringRunner.class)
public class VenueServiceImplTest {
	@InjectMocks
	private VenueServiceImpl venueService;

	@Mock
	private VenueRepo venueRepository;

	@Test
	public void getCitiesTest() {
		List<Venue> cities = new ArrayList<Venue>();
		Venue v = new Venue();
		v.setCity("Bangalore");
		cities.add(v);
		Venue v1 = new Venue();
		v1.setCity("Bhubaneswar");
		cities.add(v1);
		Venue v2 = new Venue();
		v2.setCity("Mumbai");
		cities.add(v2);

		Mockito.when(venueRepository.findAll()).thenReturn(cities);

		Set<String> citiesResult = new HashSet<String>();
		citiesResult.add("Bangalore");
		citiesResult.add("Mumbai");
		citiesResult.add("Bhubaneswar");
		assertEquals(citiesResult, venueService.getCities());

	}

	@Test
	public void checkDateTest() throws InvalidDateException {
		java.sql.Date currentDate = Date.valueOf("2020-09-20");
		assertTrue(venueService.checkDate(currentDate));

		java.sql.Date oldDate = Date.valueOf("2018-09-20");
		assertFalse(venueService.checkDate(oldDate));

	}

	@Test
	public void checkGetVenues() throws VenueException {
		Set<Booking> bookings = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Set<VenueFeatures> features = new HashSet<>();
		java.sql.Date date = Date.valueOf("2019-09-20");
		Booking b=new Booking(1,date,null,new User());
		bookings.add(b);
		Venue venue1 = new Venue("MR-01", "Bangalore", "J.P.Nagar", 1000, 100, "Meeting room with sufficient space", 4,
				5000, "Meeting", bookings, images, features);
		b.setVenue(venue1);
		Venue venue2 = new Venue("CR-01", "Bangalore", "Whitefield", 2000, 100, "Conference room with sufficient space",
				3, 6000, "Conference", bookings, images, features);
		List<Venue> venues = new ArrayList<>();
		venues.add(venue1);
		venues.add(venue2);

		Mockito.when(venueRepository.findAll()).thenReturn(venues);
		java.sql.Date currentDate = Date.valueOf("2020-09-23");
		List<Venue> expected = new ArrayList<>();
		expected.add(venue1);
		assertEquals(expected, venueService.getFinalSearchedVenues("Meeting", currentDate, 100, "Bangalore"));
	}

	@Test(expected = VenueException.class)
	public void checkGetVenues_ExceptionCaseInvalidVenueType() throws VenueException {
		Set<Booking> bookings = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Set<VenueFeatures> features = new HashSet<>();
		Venue venue1 = new Venue("MR-01", "Bangalore", "J.P.Nagar", 1000, 100, "Meeting room with sufficient space", 4,
				5000, "Meeting", bookings, images, features);
		Venue venue2 = new Venue("CR-01", "Bangalore", "Whitefield", 2000, 100, "Conference room with sufficient space",
				3, 6000, "Conference", bookings, images, features);
		List<Venue> venues = new ArrayList<>();
		venues.add(venue1);
		venues.add(venue2);

		Mockito.when(venueRepository.findAll()).thenReturn(venues);
		java.sql.Date currentDate = Date.valueOf("2020-09-23");
		List<Venue> expected = new ArrayList<>();
		expected.add(venue1);
		assertEquals(VenueException.class,
				venueService.getFinalSearchedVenues("Dummy String", currentDate, 100, "Bangalore"));
	}
	
	@Test(expected = VenueException.class)
	public void checkGetVenues_ExceptionCaseInvalidCity() throws VenueException {
		Set<Booking> bookings = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Set<VenueFeatures> features = new HashSet<>();
		Venue venue1 = new Venue("MR-01", "Bangalore", "J.P.Nagar", 1000, 100, "Meeting room with sufficient space", 4,
				5000, "Meeting", bookings, images, features);
		Venue venue2 = new Venue("CR-01", "Bangalore", "Whitefield", 2000, 100, "Conference room with sufficient space",
				3, 6000, "Conference", bookings, images, features);
		List<Venue> venues = new ArrayList<>();
		venues.add(venue1);
		venues.add(venue2);

		Mockito.when(venueRepository.findAll()).thenReturn(venues);
		java.sql.Date currentDate = Date.valueOf("2020-09-23");
		List<Venue> expected = new ArrayList<>();
		expected.add(venue1);
		assertEquals(VenueException.class,
				venueService.getFinalSearchedVenues("Meeting", currentDate, 100, "Wrong City"));
	}

	@Test(expected = VenueException.class)
	public void checkGetVenues_ExceptionCaseInvalidDate() throws VenueException {
		Set<Booking> bookings = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Set<VenueFeatures> features = new HashSet<>();
		Venue venue1 = new Venue("MR-01", "Bangalore", "J.P.Nagar", 1000, 100, "Meeting room with sufficient space", 4,
				5000, "Meeting", bookings, images, features);
		Venue venue2 = new Venue("CR-01", "Bangalore", "Whitefield", 2000, 100, "Conference room with sufficient space",
				3, 6000, "Conference", bookings, images, features);
		List<Venue> venues = new ArrayList<>();
		venues.add(venue1);
		venues.add(venue2);

		Mockito.when(venueRepository.findAll()).thenReturn(venues);
		java.sql.Date date = Date.valueOf("2018-09-23");
		List<Venue> expected = new ArrayList<>();
		expected.add(venue1);
		assertEquals(VenueException.class,
				venueService.getFinalSearchedVenues("Meeting", date, 100, "Bangalore"));
	}


}
