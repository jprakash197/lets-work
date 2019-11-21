package com.mindtree.letswork.module.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.letswork.module.booking.entity.Payment;

public interface PaymentRepo extends CrudRepository<Payment, Integer> {

}
