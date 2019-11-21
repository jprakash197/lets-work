package com.mindtree.letswork.module.venue.dto;

import java.util.List;

public class VenueDTO {

	private int venueId;

	private String venueName;

	private String city;

	private String address;

	private double size;

	private int capacity;

	private String description;

	private int rating;

	private double price;

	private String venueType;

	private List<String> feature;

	private List<String> image;

	public VenueDTO() {
		super();
	}

	public VenueDTO(int venueId, String venueName, String city, String address, double size, int capacity,
			String description, int rating, double price, String venueType, List<String> feature, List<String> image) {
		super();
		this.venueId = venueId;
		this.venueName = venueName;
		this.city = city;
		this.address = address;
		this.size = size;
		this.capacity = capacity;
		this.description = description;
		this.rating = rating;
		this.price = price;
		this.venueType = venueType;
		this.feature = feature;
		this.image = image;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getVenueType() {
		return venueType;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public List<String> getFeature() {
		return feature;
	}

	public void setFeature(List<String> feature) {
		this.feature = feature;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	} 

}
