package com.group3.bookandclean.controller;


import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.model.ByIdRequest;
import com.group3.bookandclean.services.BookingService;
import com.group3.bookandclean.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/cleaner")
@CrossOrigin(origins = "http://localhost:3000")
public class CleanerController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CleanerRepository cleanerRepository;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    BookingService bookingService;

    @GetMapping("/bookings")
    public List<Booking> fetchCleanerBookings(@RequestParam String name) {
        User user = userService.getUser(name);
        Cleaner cleaner = cleanerRepository.findCleanerByUser(user);
        Long id = cleaner.getId();
        List<Booking> bookings = bookingRepository.findBookingByCleanerId(id);

        return bookingService.setInProgressAndFilter(bookings);
    }

    @PostMapping("bookings/update")
    public ResponseEntity<?> updateStatus(@RequestBody ByIdRequest request) {
        return bookingService.updateStatus(request.getId());
    }

    @GetMapping("/email")
    public Cleaner getCleanerById(@RequestParam String email) {
        User user = userService.getUser(email);
        if (user.getType().equalsIgnoreCase("cleaner")) {
            Cleaner cleaner = cleanerRepository.findCleanerByUser(user);
            return cleaner;
        }
        return null;
    }

}
