package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.request.AddCleanerRequest;
import com.group3.bookandclean.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Long.parseLong;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CleanerRepository cleanerRepository;

    public boolean registerBooking(BookingRequest request) throws ParseException {
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

    public boolean deleteBooking(String id) {
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

    public ResponseEntity<Booking> addCleaner(AddCleanerRequest request) {
        Cleaner cleaner = cleanerRepository.getById(parseLong(request.getCleanerId()));
        Booking booking = bookingRepository.getById(parseLong(request.getBookingId()));

        booking.setCleaner(cleaner);
        booking.setStatus("pending");
        final Booking updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }

    public ResponseEntity<Booking> removeCleaner(String bookingId) {
        Booking booking = bookingRepository.getById(parseLong(bookingId));

        booking.setCleaner(null);
        booking.setStatus("unconfirmed");
        final Booking updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }
}
