package com.mindtree.letswork.module.booking.dto;

import java.sql.Date;

import com.mindtree.letswork.module.authentication.dto.UserDTO;
import com.mindtree.letswork.module.venue.dto.VenueDTO;

public class BookingDTO {

	private int id;
	private Date date;
	private VenueDTO room;
	private UserDTO user;
	public BookingDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public VenueDTO getRoom() {
		return room;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public void setRoom(VenueDTO room) {
		this.room = room;
	}
	
}
