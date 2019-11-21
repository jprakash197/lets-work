package com.mindtree.letswork.module.venue.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;

@Entity
@Table(name = "VENUES")
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venue_id")
	private int venueId;

	@Column(nullable = false)
	private String venueName;

	@Column(nullable = false)
	private String city;

	@Column(nullable = true)
	private String address;

	@Column
	private double size;

	@Column(nullable = false)
	private int capacity;

	@Column
	private String description;

	@Column
	private int rating;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private String venueType;

	@OneToMany(mappedBy = "venue")
	private Set<Booking> bookings;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Image> images;

	@ElementCollection
	@CollectionTable(name = "VENUE_FEATURES", joinColumns = @JoinColumn(name = "venue_id"))
	@Column(name = "feature", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<VenueFeatures> feature;

	public Venue() {
		super();
	}

	public Venue(String venueName, String city, String address, double size, int capacity, String description,
			int rating, double price, String venueType, Set<Booking> bookings, Set<Image> images,
			Set<VenueFeatures> feature) {
		super();
		this.venueName = venueName;
		this.city = city;
		this.address = address;
		this.size = size;
		this.capacity = capacity;
		this.description = description;
		this.rating = rating;
		this.price = price;
		this.venueType = venueType;
		this.bookings = bookings;
		this.images = images;
		this.feature = feature;
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

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<VenueFeatures> getFeature() {
		return feature;
	}

	public void setFeature(Set<VenueFeatures> feature) {
		this.feature = feature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + capacity;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rating;
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + venueId;
		result = prime * result + ((venueName == null) ? 0 : venueName.hashCode());
		result = prime * result + ((venueType == null) ? 0 : venueType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venue other = (Venue) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		if (capacity != other.capacity)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (rating != other.rating)
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		if (venueId != other.venueId)
			return false;
		if (venueName == null) {
			if (other.venueName != null)
				return false;
		} else if (!venueName.equals(other.venueName))
			return false;
		if (venueType == null) {
			if (other.venueType != null)
				return false;
		} else if (!venueType.equals(other.venueType))
			return false;
		return true;
	}

}
