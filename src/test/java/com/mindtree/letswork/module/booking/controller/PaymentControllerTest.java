package com.mindtree.letswork.module.booking.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.booking.dto.CardDTO;
import com.mindtree.letswork.module.booking.dto.PaymentDTO;
import com.mindtree.letswork.module.booking.entity.Card;
import com.mindtree.letswork.module.booking.entity.Payment;
import com.mindtree.letswork.module.booking.exception.PaymentApplicationException;
import com.mindtree.letswork.module.booking.repository.CardRepo;
import com.mindtree.letswork.module.booking.repository.PaymentRepo;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest {
	@Autowired
	private PaymentController paymentController;
	@Autowired
	public DTOUtil dToUtil;
	@MockBean
	private PaymentRepo paymentRepository;
	@MockBean
	private CardRepo cardRepository;
	private User user = new User();

	@Test
	public void addPaymentTest() throws PaymentApplicationException {
		PaymentDTO paymentDto = new PaymentDTO(101, "card", new Card(1001, "1010-1010-1010-1010", "Durga", "09/28"), user);
		Payment payment1 = (Payment) dToUtil.convert(paymentDto, Payment.class);
		when(paymentRepository.save(payment1)).thenReturn(payment1);
		assertEquals(true, paymentController.addPayment(paymentDto));
	}

	@Test
	public void getCardDetailsTest() throws PaymentApplicationException {
		String name = "Durga";
		Card card = new Card(10, "1023-4568-3456-8765", "Durga", "09/25");
		when(cardRepository.getCardDetailsByName(name)).thenReturn(card);
		CardDTO cardDto = (CardDTO) dToUtil.convert(card, CardDTO.class);
		assertEquals(cardDto, paymentController.getCardDetails(name));
	}

}
