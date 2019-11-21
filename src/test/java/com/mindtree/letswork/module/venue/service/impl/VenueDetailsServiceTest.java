package com.mindtree.letswork.module.venue.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.repository.VenueRepo;
import com.mindtree.letswork.module.venue.service.VenueService;

@RunWith(SpringRunner.class)
public class VenueDetailsServiceTest {
	@TestConfiguration
	static class VenueServiceContextConfig {
		@Bean
		public VenueService roomService() {
			return new VenueServiceImpl();
		}
	}

	@Autowired
	private VenueService venueService;

	@MockBean
	private VenueRepo venueRepo;

	@Test(expected=VenueException.class)
	public void getVenueByIdTest() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue room = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		 
		Mockito.when(venueRepo.findById(1)).thenReturn(Optional.of(room));
		Venue venueFound = venueService.getVenueDetails(1);

		assertEquals(venueFound.getVenueName(), room.getVenueName());
		assertNotEquals(venueFound.getVenueName(), "eirwe");
		assertEquals(VenueException.class,venueService.getVenueDetails(41));
	}
	
	
	@Test
	public void getVenueByNameTest() throws VenueException {
		final String cannotBook = "Compton";
		
		Set<String> cities = venueService.getCities();
		String solid = cities.stream().filter(s -> s.equals(cannotBook)).findFirst().orElse("Solid");
		
		assertEquals(solid, "Solid");
		assertEquals(cities.size(), 0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void checkVenueDateDoesNotEqualPrice() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue venue = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		
		Mockito.when(this.venueRepo.findById(1)).thenReturn(Optional.of(venue));
		Venue venueFound = this.venueService.getVenueDetails(1);
		
		Date date = new Date(12, 25, 2019);
		
		assertNotEquals(venueFound.getPrice(), date.getDay());
	}
	
	@Test
	public void checkVenuePriceIsNotAddress() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue venue = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		
		Mockito.when(this.venueRepo.findById(1)).thenReturn(Optional.of(venue));
		Venue venueFound = this.venueService.getVenueDetails(1);
		
		assertNotEquals(venueFound.getPrice(), venueFound.getAddress());
	}



}
