package com.mindtree.letswork.module.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.letswork.module.authentication.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	/**
	 * find using username
	 * @param username
	 * @return User
	 */
	@Query("from User where username=?1")
	User findUserByUserName(String username);

	/**
	 * find using token
	 * @param token
	 * @return Optional
	 */
	@Query("from User where token=?1")
	Optional<User> findByToken(String token);
	
	/**
	 * find using referral code
	 * @param referralCode
	 * @return Optional
	 */
	@Query("from User where referral_code=?1")
	Optional<User> findByStringID(String referralCode);
	
	/**
	 * Update existing code to add token
	 * @param token
	 * @param referralCode
	 * @return number of rows edited
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User SET token=?1 WHERE referral_code =?2")
	int updateToken(String token, String referralCode);
}
