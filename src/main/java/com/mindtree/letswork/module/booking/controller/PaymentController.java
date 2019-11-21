package com.mindtree.letswork.module.booking.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.booking.dto.CardDTO;
import com.mindtree.letswork.module.booking.dto.PaymentDTO;
import com.mindtree.letswork.module.booking.entity.Card;
import com.mindtree.letswork.module.booking.entity.Payment;
import com.mindtree.letswork.module.booking.exception.PaymentApplicationException;
import com.mindtree.letswork.module.booking.service.PaymentService;
import com.mindtree.letswork.module.venue.util.DTOUtil;


@CrossOrigin

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	DTOUtil dToUtil;
	
	@PostMapping("/payment")
	public boolean addPayment(@Valid @RequestBody PaymentDTO paymentDto) throws PaymentApplicationException
	{
		Payment payment= (Payment) dToUtil.convert(paymentDto,  Payment.class);
		paymentService.addPaymentDetails(payment);
		return true;
	}
	
	@GetMapping("/payment/{name}")
	public CardDTO getCardDetails(@PathVariable String name) throws PaymentApplicationException 
	{
		Card card  = paymentService.getCardDetail(name);
		return (CardDTO) dToUtil.convert(card, CardDTO.class);
	}

	
	
}
