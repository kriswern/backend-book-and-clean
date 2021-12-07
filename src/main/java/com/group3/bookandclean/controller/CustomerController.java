package com.group3.bookandclean.controller;


import com.group3.bookandclean.request.BookingRequest;
import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CustomerRepository;
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
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/addbooking")
    public boolean addBooking(@RequestBody BookingRequest request) throws ParseException {
        Long userId = parseLong(request.getCustomerId());
        Customer customer = customerRepository.getById(userId);

        Booking booking = Booking.builder()
                .description(request.getName())
                .address(request.getAddress())
                .date(new SimpleDateFormat("yyyy-mm-dd").parse(request.getDate()))
                .time(new SimpleDateFormat("HH:mm").parse(request.getTime()))
                .customer(customer)
                .status("unconfirmed")
                .build();
        bookingRepository.save(booking);
        return true;
    }


    @GetMapping("/bookings")
    public List<Booking> fetchCustomerBookings(@RequestParam String id) {
        log.info(id);
    return customerRepository.fetchBookingsByCustomerId(parseLong(id));
    }








}
