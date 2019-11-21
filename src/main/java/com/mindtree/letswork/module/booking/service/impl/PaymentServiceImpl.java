package com.mindtree.letswork.module.booking.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.booking.entity.Card;
import com.mindtree.letswork.module.booking.entity.Payment;
import com.mindtree.letswork.module.booking.exception.DateServiceException;
import com.mindtree.letswork.module.booking.exception.InvalidExpiryDateException;
import com.mindtree.letswork.module.booking.exception.RecordNotFoundException;
import com.mindtree.letswork.module.booking.repository.CardRepo;
import com.mindtree.letswork.module.booking.repository.PaymentRepo;
import com.mindtree.letswork.module.booking.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepo paymentRepository;

	@Autowired
	CardRepo cardRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean addPaymentDetails(Payment payment) throws InvalidExpiryDateException, DateServiceException {
		Card card = payment.getCard();
		boolean check = false;
		check = validate(card.getExpiryDate());
		if (check) {
			paymentRepository.save(payment);
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo("durga050397@gmail.com");
			msg.setSubject("Booking Confirmation");
			msg.setText(
					"We are glad to inform you that your booking is successful. Thanks for choosing us. Please visit us again.");
			javaMailSender.send(msg);
			return true;
		} else
			throw new InvalidExpiryDateException("Your card is expired!");
	}

	@Override
	public Card getCardDetail(String name) throws RecordNotFoundException {
		Card card = null;
		card = cardRepository.getCardDetailsByName(name);
		if (card == null) {
			throw new RecordNotFoundException("User not found!");
		} else {
			return card;
		}
	}

	@Override
	public boolean validate(String date) throws DateServiceException {
		long millis = System.currentTimeMillis();
		java.util.Date currentDate = new java.util.Date(millis);
		java.util.Date expiryDate;
		try {
			expiryDate = (new SimpleDateFormat("MM/yy").parse(date));
			String[] month = date.split("/");
			if (Integer.parseInt(month[0]) > 12) {
				throw new DateServiceException("Please enter a valid date");
			} else if (currentDate.compareTo(expiryDate) > 0)
				return false;
			return true;
		} catch (ParseException e) {
			throw new DateServiceException("Please enter a valid date");
		}
	}
}
