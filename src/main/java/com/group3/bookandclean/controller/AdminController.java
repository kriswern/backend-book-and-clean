package com.group3.bookandclean.controller;


import com.group3.bookandclean.request.BookingRequest;
import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CleanerRepository cleanerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/cleaners")
    public List<Cleaner> getAllCleaners() {
        return cleanerRepository.findAll();
    }

    @GetMapping("/bookings")
    public List<Booking> fetchBookings() {
        return bookingRepository.findAll();
    }



    }


