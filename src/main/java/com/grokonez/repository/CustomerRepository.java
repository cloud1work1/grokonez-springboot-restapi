package com.grokonez.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.grokonez.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByAge(int age);
}
