package com.mindtree.letswork.module.authentication.dto;

import java.util.List;

import com.mindtree.letswork.module.booking.dto.BookingDTO;
import com.mindtree.letswork.module.booking.entity.Payment;

public class UserDTO {

	private String referralCode;
	private String username;
	private String realName;
	private String email;
	private String password;
	private String referredCode;
	private String role;
	private String token;
	private List<BookingDTO> bookings;
	private Payment paymentInfo;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getReferredCode() {
		return referredCode;
	}
	public void setReferredCode(String referredCode) {
		this.referredCode = referredCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Payment getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public UserDTO() {
		super();
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<BookingDTO> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}
	
	
	
}
