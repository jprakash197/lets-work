package com.mindtree.letswork.module.booking.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.letswork.module.booking.exception.BookingErrorResponse;
import com.mindtree.letswork.module.booking.exception.DateServiceException;
import com.mindtree.letswork.module.booking.exception.InvalidExpiryDateException;
import com.mindtree.letswork.module.booking.exception.RecordNotFoundException;

@RestControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = InvalidExpiryDateException.class)
	public ResponseEntity<Object> exception(InvalidExpiryDateException exception, WebRequest request) {
		BookingErrorResponse errorDetails = new BookingErrorResponse(exception.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<Object> exception(RecordNotFoundException exception, WebRequest request) {
		BookingErrorResponse errorDetails = new BookingErrorResponse(exception.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DateServiceException.class)
	public ResponseEntity<Object> exception(DateServiceException exception, WebRequest request) {
		BookingErrorResponse errorDetails = new BookingErrorResponse(exception.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
