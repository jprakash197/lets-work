package com.mindtree.letswork.module.venue.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.venue.entity.Venue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VenueRepoTest {
	@Autowired
	TestEntityManager entityManager;
	@Autowired
	VenueRepo venueRepo;

	@Test
	public void testSaveRoom() {
		Venue rm = getRoom();
		Venue savedInDb = entityManager.persist(rm);
		Venue fromDb = venueRepo.findById(savedInDb.getVenueId()).get();
		assertEquals(savedInDb, fromDb);

	}

	private Venue getRoom() {
		Venue room = new Venue();

		room.setVenueName("abc");
		room.setVenueType("training");
		room.setAddress("qwert");
		room.setCity("Bangalore");
		return room;
	}
}
