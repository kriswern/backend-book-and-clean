package com.group3.bookandclean.controller;


import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.request.BookingRequest;
import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.services.BookingService;
import com.group3.bookandclean.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.lang.Long.parseLong;

@Slf4j
@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {


    @Autowired
    BookingService bookingService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/addbooking")
    public void addBooking(@RequestBody BookingRequest request) throws ParseException {
        bookingService.registerBooking(request);
    }

    @DeleteMapping(value = "/deletebookings{id}")
    public boolean removeBooking(@RequestParam String id) {
        log.info("id: {}", id);
        return bookingService.deleteBooking(id);
    }

    @GetMapping("/bookings")
    public List<Booking> fetchCustomerBookings(@RequestParam String name) {
        User user = userService.getUser(name);
        Long id = user.getId();
        log.info(String.valueOf(id));
    return customerRepository.fetchBookingsByCustomerId(id);
    }








}
