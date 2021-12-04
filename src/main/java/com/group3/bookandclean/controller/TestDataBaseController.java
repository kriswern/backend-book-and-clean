package com.group3.bookandclean.controller;


//TEMPORARY CONTROLLER - TO BE REMOVED

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestDataBaseController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CleanerRepository cleanerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> fetchCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/cleaners")
    public List<Cleaner> fetchCleaners() {
        return cleanerRepository.findAll();
    }




}
