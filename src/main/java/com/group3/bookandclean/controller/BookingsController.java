package com.group3.bookandclean.controller;

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.lang.Long.parseLong;

/*
@RestController
@RequestMapping("/api/addBooking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingsController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;


    //NOT USED ANYMORE, JUST THE BookingRequest. Maybe put in seperate
    @PostMapping(value = "/addbookings")
    public boolean addBooking(@RequestBody BookingRequest request) throws ParseException {


        Customer customer1 = Customer.builder()
                .name("test test")
                .address("Åhusgatan 3")
                .build();
        customerRepository.save(customer1);

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
    //OBS should only be accessible for admins !

    @DeleteMapping(value = "/deletebookings")
    public boolean removeBooking(@RequestBody String id) {
        long longId = parseLong(id);
        boolean success = false;

        try {
            Booking booking = bookingRepository.getById(longId);
            bookingRepository.delete(booking);
            success = true;
        } catch (Exception ex) {
        }
        return success;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/bookings")
    public List<Booking> fetchBookings() {
        return bookingRepository.findAll();
    }

}


@Getter
@Setter
class BookingRequest {

    String name;
    String address;
    String date;
    String time;

    String customerId;

}*/
