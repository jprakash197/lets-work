package com.mindtree.letswork.module.booking.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.booking.entity.Card;
import com.mindtree.letswork.module.booking.entity.Payment;
import com.mindtree.letswork.module.booking.exception.DateServiceException;
import com.mindtree.letswork.module.booking.exception.InvalidExpiryDateException;
import com.mindtree.letswork.module.booking.exception.RecordNotFoundException;
import com.mindtree.letswork.module.booking.repository.CardRepo;
import com.mindtree.letswork.module.booking.repository.PaymentRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceImplTest {

	@MockBean
	CardRepo cardRepository;
	
	@MockBean
	PaymentRepo paymentRepository;
	
			
	@Autowired
	private PaymentServiceImpl paymentService;
	private Payment payment;
	private String date;
	private User user = new User();
		
	@Test
	public void getCardDetailSuccessTest() throws RecordNotFoundException
	{
		Card card=new Card(1, "Durga", "1234-9876-4567-3456", "09/23");//expected
		String name = "Durga";
		when(cardRepository.getCardDetailsByName(name)).thenReturn(new Card(1, "Durga", "1234-9876-4567-3456", "09/23"));//actual
			assertEquals(card, paymentService.getCardDetail(name));
		}


		@Test
		public void getCardDetailFailureTest() throws RecordNotFoundException
		{
			Card card=new Card(1, "Durga", "1234-9876-4567-3456", "09/23");//expected
			String name = "Durga";
			when(cardRepository.getCardDetailsByName(name)).thenReturn(new Card(1, "Dura", "1234-9876-4567-3456", "09/23"));//actual
			assertNotEquals(card, paymentService.getCardDetail(name));
		}
		
		@Test
		public void getCardDetailExceptionTest()
		{
			Card card=new Card(1, "Durga", "1234-9876-4567-3456", "09/23");//expected
			String name = "Durga";
			String name1 = "Rohit";
			when(cardRepository.getCardDetailsByName(name)).thenReturn(new Card(1, "Durga", "1234-9876-4567-3456", "09/23"));//actual
			try {
				assertEquals(card,  paymentService.getCardDetail(name1));
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		public void addPaymentDetailsTest() throws DateServiceException, InvalidExpiryDateException
		{   
			Card card = new Card(1, "Durga", "1234-9876-4567-3456", "09/23");
			payment = new Payment(1,"card",card, user);
			when(paymentRepository.save(payment)).thenReturn(payment);
			assertEquals(true, paymentService.addPaymentDetails(payment));
			assertNotEquals(false, paymentService.addPaymentDetails(payment));		
		}
		
	     @Test
	     public void validateTest() throws DateServiceException {
	    	 date = "09/23";
	    	 assertEquals(true, paymentService.validate(date));
	     }
	     
	     @Test
	     public void addPaymentDetailsExceptionTest() throws DateServiceException 
		 {   
	    	Card card  = new Card(1, "Durga", "1234-9876-4567-3456", "09/18");
	 	 	payment = new Payment(1,"card",card, user);
	 	 	when(paymentRepository.save(payment)).thenReturn(payment);
	 	 	try {
	 			assertEquals(true, paymentService.addPaymentDetails(payment));
	 		} catch (InvalidExpiryDateException e) {
	 			e.printStackTrace();
	 		}
	 	 	try {
	 			assertNotEquals(false, paymentService.addPaymentDetails(payment));
	 		} catch (InvalidExpiryDateException e) {
	 			e.printStackTrace();
	 		}		
		 }
}
