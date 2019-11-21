package com.mindtree.letswork.module.venue.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.letswork.module.venue.exception.CityNotFoundException;
import com.mindtree.letswork.module.venue.exception.CustomVenueException;
import com.mindtree.letswork.module.venue.exception.InvalidDateException;
import com.mindtree.letswork.module.venue.exception.InvalidVenueTypeException;
import com.mindtree.letswork.module.venue.exception.VenueNotFoundException;

@ControllerAdvice
public class VenueExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<?> globalExceptionHandler(CityNotFoundException ex, WebRequest request) {
		CustomVenueException errorDetails = new CustomVenueException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<?> globalExceptionHandler(InvalidDateException ex, WebRequest request) {
		CustomVenueException errorDetails = new CustomVenueException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidVenueTypeException.class)
	public ResponseEntity<?> globalExceptionHandler(InvalidVenueTypeException ex, WebRequest request) {
		CustomVenueException errorDetails = new CustomVenueException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(VenueNotFoundException.class)
	public ResponseEntity<?> venueExceptionHandler(VenueNotFoundException ex, WebRequest request) {
		CustomVenueException errorDetails = new CustomVenueException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomVenueException errorDetails = new CustomVenueException("Invalid Input", new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomVenueException errorDetails = new CustomVenueException("Invalid Input", new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
