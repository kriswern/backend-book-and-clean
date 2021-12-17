package com.group3.bookandclean.controller;


import com.group3.bookandclean.entity.*;
import com.group3.bookandclean.repository.*;
import com.group3.bookandclean.request.*;
import com.group3.bookandclean.services.AdminService;
import com.group3.bookandclean.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static java.lang.Long.parseLong;

@Slf4j
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
    @Autowired
    private PriceListRepository priceListRepository;
    @Autowired
    private BillsRepository billsRepository;
    @Autowired
    BookingService bookingService;

    @Autowired
    AdminService adminService;

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

    @GetMapping("/priceList")
    public List<PriceList> fetchPriceList(){
        return priceListRepository.findAll();}

    @PostMapping(value = "/addbooking")
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest request) throws ParseException {
        return bookingService.registerBooking(request);
    }

    @DeleteMapping(value = "/deletebookings{id}")
    public boolean removeBooking(@RequestParam String id) {
        return bookingService.deleteBooking(id);
    }

    @PutMapping("/assigncleaner")
    public ResponseEntity<?> assignCleanerToBooking(@RequestBody AddCleanerRequest request) {
        return bookingService.addCleaner(request);
    }

    @PutMapping("/removecleaner")
    public ResponseEntity<Booking> removeCleanerFromBooking(@RequestBody ByIdRequest request) {

        return bookingService.removeCleaner(request.getId());
    }

    @GetMapping("/cleanername{id}")
    public String getCleanerName(@RequestParam String id) {
        Cleaner cleaner = cleanerRepository.getById(parseLong(id));
        return cleaner.getName();
    }

    @PostMapping ("/addbill")
    public boolean addBill(@RequestBody BillRequest billRequest){
        return adminService.addBill(billRequest);
    }

    @PutMapping ("/updatebookingbill")
    public boolean updateBookingStatus(@RequestBody List<Long> bookingIds){

        for(Long id : bookingIds){
            bookingService.changeStatusBilled(id);
        }
        return true;
    }

}
