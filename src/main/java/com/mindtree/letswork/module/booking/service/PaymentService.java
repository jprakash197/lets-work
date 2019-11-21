package com.mindtree.letswork.module.booking.service;

import java.text.ParseException;

import com.mindtree.letswork.module.booking.entity.Card;
import com.mindtree.letswork.module.booking.entity.Payment;
import com.mindtree.letswork.module.booking.exception.PaymentApplicationException;



public interface PaymentService {

	
	/**
	 * @param payment
	 * @return true or false
	 * @throws PaymentApplicationException
	 */
	public boolean addPaymentDetails(Payment payment) throws PaymentApplicationException;
	/**
	 * @param name
	 * @return Card
	 * @throws PaymentApplicationException
	 */
	public Card getCardDetail(String name) throws PaymentApplicationException;
	/**
	 * @param date
	 * @return true or false
	 * @throws ParseException
	 * @throws PaymentApplicationException
	 */
	boolean validate(String date) throws ParseException, PaymentApplicationException;
}
