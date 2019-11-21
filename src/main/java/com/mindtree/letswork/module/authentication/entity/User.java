package com.mindtree.letswork.module.authentication.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.booking.entity.Payment;

@Entity
@Table
@JsonIdentityInfo(
		generator = ObjectIdGenerators.IntSequenceGenerator.class, 
		property="id"
		) 
public class User implements UserDetails {

	private static final long serialVersionUID = 8350077531921411529L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "com.mindtree.letswork.constant.SequenceGenerator",
    parameters = @Parameter(name="prefix", value="ref"))
	@Column(name = "referralCode", unique = true, nullable = false)
	private String referralCode;

	@Column(name = "username")
	private String username;
	
	@Column(name = "realName")
	private String realName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "referredCode")
	private String referredCode;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "token")
	private String token;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;
	
	@OneToOne(mappedBy = "user")
	private Payment paymentInfo;

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String refCode) {
		this.referralCode = refCode;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getReferredCode() {
		return referredCode;
	}

	public void setReferredCode(String refferedCode) {
		this.referredCode = refferedCode;
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

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Payment getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (paymentInfo == null) {
			if (other.paymentInfo != null)
				return false;
		} else if (!paymentInfo.equals(other.paymentInfo))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (referralCode != other.referralCode)
			return false;
		if (referredCode == null) {
			if (other.referredCode != null)
				return false;
		} else if (!referredCode.equals(other.referredCode))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
