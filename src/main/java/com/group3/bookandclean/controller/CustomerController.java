package com.group3.bookandclean.controller;

import com.group3.bookandclean.entity.*;
import com.group3.bookandclean.model.BookingRequest;
import com.group3.bookandclean.model.ByIdRequest;
import com.group3.bookandclean.model.PayBillRequest;
import com.group3.bookandclean.model.RejectCleaningRequest;
import com.group3.bookandclean.repository.BillsRepository;
import com.group3.bookandclean.repository.PriceListRepository;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.services.BookingService;
import com.group3.bookandclean.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


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

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    BillsRepository billsRepository;

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
        List<Booking> bookings = bookingRepository.findBookingByCustomerId(id);

        return bookingService.setInProgressAndFilter(bookings);
    }

    @GetMapping("/customerid")
    public String getCustomerID(@RequestParam String name) {
        User user = userService.getUser(name);
        Customer customer = customerRepository.findCustomerByUser(user);
        String id = String.valueOf(customer.getId());

        return id;

    }

    @PostMapping("/approve-cleaning")
    public ResponseEntity<?> approveCleaning(@RequestBody ByIdRequest request) {
        return bookingService.updateStatus(request.getId());

    }

    @PostMapping("/reject-cleaning")
    public ResponseEntity<?> rejectCleaning(@RequestBody RejectCleaningRequest request) {
        return bookingService.rejectCleaning(request);

    }

    @GetMapping("/email")
    public Customer getCustomerById(@RequestParam String email) {
        User user = userService.getUser(email);
        if (user.getType().equalsIgnoreCase("customer")) {
            Customer customer = customerRepository.findCustomerByUser(user);
            return customer;

        }
        return null;
    }

    @GetMapping("/priceList")
    public List<PriceList> fetchPriceList() {

        return priceListRepository.findAll();
    }

    @GetMapping("/bills")
    public List<Bills> getBills(@RequestParam String email) {
        User user = userService.getUser(email);
        Customer customer = customerRepository.findCustomerByUser(user);

        return billsRepository.findBillsByCustomer(customer);

    }

    @PostMapping("/pay-for-bill")
    public Boolean payForBill(@RequestBody PayBillRequest request) {
        for (Long id : request.getBookingIds()) {
            bookingService.updateStatus(String.valueOf(id));
        }
        billsRepository.deleteById(request.getBillId());
        return true;
    }
}







