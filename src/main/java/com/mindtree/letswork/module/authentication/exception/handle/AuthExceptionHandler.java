/**
 * 
 */
/**
 * @author M1053435
 *
 */
package com.mindtree.letswork.module.authentication.exception.handle;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.letswork.module.authentication.exception.CustomAuthException;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;



@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> globalExceptionHandler(UsernameNotFoundException ex, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<?> globalExceptionHandler(IncorrectPasswordException ex, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJWTToken.class)
	public ResponseEntity<?> globalExceptionHandler(InvalidJWTToken ex, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> globalExceptionHandler(InvalidInputException ex, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException(ex.getLocalizedMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidReferralCodeException.class)
	public ResponseEntity<?> globalExceptionHandler(InvalidReferralCodeException ex, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException(ex.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException(
				ex.getBindingResult().getFieldError().getDefaultMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomAuthException errorDetails = new CustomAuthException("Invalid Input", new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	} 

}