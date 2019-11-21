package com.mindtree.letswork.module.booking.dto;

import javax.validation.Valid;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.booking.entity.Card;


public class PaymentDTO {

	private int paymentId;
	private String paymentMode;

	@Valid
	private Card card;
	
	private User user;

	public PaymentDTO() {

	}

	public PaymentDTO(int paymentId, String paymentMode, Card card, User user) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.card = card;
		this.user=user;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
