package com.mindtree.letswork.module.booking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.letswork.module.booking.entity.Card;

@Repository
public interface CardRepo extends CrudRepository<Card, Integer> {

	@Query("from Card where cardName=?1")
	Card getCardDetailsByName(String name);
}
