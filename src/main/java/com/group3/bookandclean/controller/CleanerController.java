package com.group3.bookandclean.controller;


import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/cleaner")
@CrossOrigin(origins = "http://localhost:3000")
public class CleanerController {


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CleanerRepository cleanerRepository;


    @GetMapping("/bookings")
    public List<Booking> fetchCleanerBookings(@RequestParam String id) {
        return cleanerRepository.fetchBookingsByCleanerId(parseLong(id));
    }




}
