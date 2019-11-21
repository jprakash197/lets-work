package com.mindtree.letswork.module.venue.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.dto.VenueRequestDTO;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.exception.VenueNotFoundException;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@CrossOrigin
@RestController
public class VenueController {

	@Autowired
	private VenueServiceImpl venueService;

	@Autowired
	private DTOUtil dtoUtil;

	@GetMapping("/venues/{capacity}/{city}/{venueType}/{date}")
	public ResponseEntity<List<VenueDTO>> getVenues(@Valid @PathVariable int capacity, @PathVariable String city,@PathVariable String venueType, @PathVariable Date date) throws VenueException {
		List<VenueDTO> venuesDto = new ArrayList<>();
		VenueRequestDTO details=new VenueRequestDTO(date,capacity,venueType,city);
		venueService
				.getFinalSearchedVenues(details.getVenueType(), details.getDate(), details.getCapacity(),
						details.getCity())
				.forEach(venue -> venuesDto.add((VenueDTO) dtoUtil.convert(venue, VenueDTO.class)));

		if (venuesDto.size() != 0)
			return ResponseEntity.ok().body(venuesDto);
		else
			throw new VenueNotFoundException("No venues available");

	}

	@PostMapping("/venuesz")
	public ResponseEntity<?> postVenue(@Valid @RequestBody VenueDTO venue) throws VenueException {
		Venue venueSaved = this.venueService.insertVenue((Venue) dtoUtil.convert(venue, Venue.class));

		if (venueSaved != null)
			return ResponseEntity.ok().body((VenueDTO) dtoUtil.convert(venueSaved, VenueDTO.class));
		else
			return ResponseEntity.ok().body("Venue failed to save: " + venue);

	}

	@PutMapping("/venues")
	public ResponseEntity<?> updateVenue(@Valid @RequestBody VenueDTO venue) throws VenueException {
		boolean success = this.venueService.updateVenue((Venue) dtoUtil.convert(venue, Venue.class));

		if (success) {
			return ResponseEntity.ok().body("Venue id: " + venue.getVenueId() + " updated");
		} else {
			return ResponseEntity.ok().body("Venue id: " + venue.getVenueId() + " failed to update");
		}
	}

	@DeleteMapping("/venues/{venueId}")
	public ResponseEntity<?> deleteVenue(@PathVariable int venueId) throws VenueException {
		boolean success = this.venueService.deleteVenue(venueId);

		if (success) {
			return ResponseEntity.ok().body("Venue id: " + venueId + " deleted");
		} else {
			return ResponseEntity.ok().body("Venue id: " + venueId + " was not deleted");
		}
	}

	@GetMapping("/cities")
	public ResponseEntity<?> getCities() {
		return ResponseEntity.ok().body(venueService.getCities());
	}

	@GetMapping("/getDetails/{id}")
	public ResponseEntity<?> getDetails(@PathVariable int id) throws VenueException {
		System.out.println("id is " + id);
		Venue venue = venueService.getVenueDetails(id);
		VenueDTO venueDto = (VenueDTO) dtoUtil.convert(venue, VenueDTO.class);
		List<String> photo = new ArrayList<>();
		Set<Image> image = venue.getImages();
		if (!image.isEmpty()) {
			try {
				for (Image img : image) {
					byte[] encodeBase64 = Base64Utils.encode(img.getImage());
					String base64Encoded;

					base64Encoded = new String(encodeBase64, "UTF-8");
					String file = "data:image/jpg;base64," + base64Encoded;
					photo.add(file);
				}
				venueDto.setImage(photo);
			} catch (UnsupportedEncodingException e) {

				throw new VenueException(e.getMessage());
			}
		}

		return ResponseEntity.ok().body(venueDto);
	}

	@GetMapping("/venues")
	public ResponseEntity<?> getAllVenues() throws VenueException {
		List<VenueDTO> venuesDto = new ArrayList<>();
		
		StringBuilder exceptionMessage = new StringBuilder(); 
		
		this.venueService.getAllVenues().forEach(venue -> {
			List<String> photo = new ArrayList<>();
			Set<Image> image = venue.getImages();
			VenueDTO venueDto = null;
			try {
				for (Image img : image) {
					byte[] encodeBase64 = Base64Utils.encode(img.getImage());
					String base64Encoded;

					base64Encoded = new String(encodeBase64, "UTF-8");
					String file = "data:image/jpg;base64," + base64Encoded;
					photo.add(file);
				}
				venueDto = (VenueDTO) dtoUtil.convert(venue, VenueDTO.class);
				venueDto.setImage(photo);
			} catch (UnsupportedEncodingException e) {
				exceptionMessage.append(e.getMessage());
			}

			venuesDto.add(venueDto);
		});

		if (exceptionMessage.length() != 0) {
			return ResponseEntity.ok().body(exceptionMessage);
		} else {
			return ResponseEntity.ok().body(venuesDto);
		}

	}
}
