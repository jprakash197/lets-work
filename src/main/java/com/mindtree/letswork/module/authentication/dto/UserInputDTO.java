package com.mindtree.letswork.module.authentication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserInputDTO {

	@NotNull(message = "Username cannot be null")
	private String username;
	@NotNull(message = "Name cannot be null")
	private String realName;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid with the following format email@domain.com")
	private String email;
	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{9,15})", 
	message = "Password must be a mix of lowercase letter, uppercase letter, and numbers and must be 9 - 15 characters.")
	private String password;
	private String referredCode;

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

	public void setReferredCode(String referredCode) {
		this.referredCode = referredCode;
	}

}
