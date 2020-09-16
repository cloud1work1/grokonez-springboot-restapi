package com.grokonez.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.entity.Customer;
import com.grokonez.repository.CustomerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> customers = new ArrayList();
		customerRepository.findAll().forEach(customers::add);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> save(@RequestBody Customer customer){
		try {
			Customer newCustomer = customerRepository.save(customer);
			return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			customerRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/customers")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			customerRepository.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch(Exception ex) {
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/customers/age/{age}")
	public ResponseEntity<List<Customer>> findByAge(@PathVariable("age") int age) {
		try {
			List<Customer> customers = customerRepository.findByAge(age);
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/customers")
	public ResponseEntity<HttpStatus> update(@RequestBody Customer customer){
		try {
			customerRepository.save(customer);
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
		}catch(Exception ex) {
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
