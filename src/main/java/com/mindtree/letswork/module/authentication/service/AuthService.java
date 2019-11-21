package com.mindtree.letswork.module.authentication.service;

import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;

/**
 * @author M1053435
 *
 */
@Service
public interface AuthService {
	/**
	 * Get user via username
	 * @param username
	 * @return
	 */
	public User login(String username);

	/**
	 * save user in database
	 * @param user
	 * @return
	 * @throws InvalidReferralCodeException
	 * @throws InvalidInputException
	 */
	public User signup(User user) throws InvalidReferralCodeException, InvalidInputException;

	/**
	 * check given password is correct
	 * @param password
	 * @param user
	 * @return
	 * @throws IncorrectPasswordException
	 */
	public User authenticatePassword(String password, User user) throws IncorrectPasswordException;

	/**
	 * check given referral code
	 * @param referralCode
	 * @return
	 * @throws InvalidReferralCodeException
	 */
	public boolean validateReferralCode(String referralCode) throws InvalidReferralCodeException;

	/**
	 * check if given username is available
	 * @param username
	 * @return
	 */
	public boolean isUsernameAvailable(String username);

	/**
	 * update user with new token
	 * @param token
	 * @param user
	 * @return
	 * @throws InvalidJWTToken
	 */
	public boolean updateToken(String token, User user) throws InvalidJWTToken;
}
