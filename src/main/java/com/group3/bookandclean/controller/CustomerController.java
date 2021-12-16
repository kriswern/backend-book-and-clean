package com.group3.bookandclean.controller;


import com.group3.bookandclean.entity.Cleaner;
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
import org.springframework.http.ResponseEntity;
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
    private BookingRepository bookingRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping(value = "/addbooking")
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest request) throws ParseException {
        return bookingService.registerBooking(request);
    }

    @DeleteMapping(value = "/deletebookings{id}")
    public boolean removeBooking(@RequestParam String id) {
        log.info("id: {}", id);
        return bookingService.deleteBooking(id);
    }

    @GetMapping("/bookings")
    public List<Booking> fetchCustomerBookings(@RequestParam String name) {
        User user = userService.getUser(name);
        Customer customer = customerRepository.findCustomerByUser(user);
        Long id = customer.getId();
        log.info(String.valueOf(id));
        return bookingRepository.findBookingByCustomerId(id);
    }

    @GetMapping("/customerid")
    public String getCustomerID(@RequestParam String name) {
        User user = userService.getUser(name);
        Customer customer = customerRepository.findCustomerByUser(user);
        String id = String.valueOf(customer.getId());

        return id;

    }








}
