package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.request.AddCleanerRequest;
import com.group3.bookandclean.request.BookingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

@Slf4j
@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CleanerRepository cleanerRepository;

    public ResponseEntity<?> registerBooking(BookingRequest request) {

        try {

            Long userId = parseLong(request.getCustomerId());
            Customer customer = customerRepository.getById(userId);
            LocalDate date = LocalDate.parse(request.getDate());
            LocalTime time = LocalTime.parse(request.getTime());

            List<Booking> customerBookings = bookingRepository.findBookingByCustomerId(customer.getId());

            for (Booking booking : customerBookings) {
                //Check if the customer have a booking the same date
                if (booking.getDate().equals(date)) {
                    LocalDateTime newBooking = LocalDateTime.of(date, time);
                    LocalDateTime oldBooking = LocalDateTime.of(booking.getDate(), booking.getTime());
                    if (compareTimeOfBookings(newBooking, oldBooking))
                        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
                }
            }

            Booking booking = Booking.builder()
                    .description(request.getName())
                    .address(request.getAddress())
                    .date(date)
                    .time(time)
                    .customer(customer)
                    .status(Status.UNCONFIRMED.toString())
                    .build();
            bookingRepository.save(booking);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    public ResponseEntity<?> addCleaner(AddCleanerRequest request) {

        try {
            Cleaner cleaner = cleanerRepository.getById(parseLong(request.getCleanerId()));
            Booking booking = bookingRepository.getById(parseLong(request.getBookingId()));

            List<Booking> cleanerBookings = bookingRepository.findBookingByCleanerId(cleaner.getId());

            for (Booking cleanerBooking : cleanerBookings) {
                if (booking.getDate().equals(cleanerBooking.getDate())) {
                    LocalDateTime newBooking = LocalDateTime.of(booking.getDate(), booking.getTime());
                    LocalDateTime oldBooking = LocalDateTime.of(cleanerBooking.getDate(), cleanerBooking.getTime());
                    if (compareTimeOfBookings(newBooking, oldBooking))
                        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
                }
            }
            booking.setCleaner(cleaner);
            booking.setStatus(Status.CONFIRMED.toString());
            bookingRepository.save(booking);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private boolean compareTimeOfBookings(LocalDateTime newBooking, LocalDateTime oldBooking) {
        long newBookingTimeInSeconds = newBooking.toEpochSecond(ZoneOffset.UTC);
        long oldBookingTimeInSeconds = oldBooking.toEpochSecond(ZoneOffset.UTC);

        //For hours in seconds
        int difference = 60 * 60 * 4;

        log.info(String.valueOf(Math.abs(newBookingTimeInSeconds - oldBookingTimeInSeconds)));
        //If that booking is within 4 hours of the other booking
        if (Math.abs(newBookingTimeInSeconds - oldBookingTimeInSeconds) < difference)
            return true;
        return false;
    }

    public ResponseEntity<Booking> removeCleaner(String bookingId) {
        Booking booking = bookingRepository.getById(parseLong(bookingId));

        booking.setCleaner(null);
        booking.setStatus(Status.UNCONFIRMED.toString());
        final Booking updatedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(updatedBooking);
    }


    public ResponseEntity<Booking> updateStatus(String id) {
        Booking booking = bookingRepository.getById(parseLong(id));

        if(booking.getStatus().equalsIgnoreCase("confirmed")){
            booking.setStatus(Status.DONE.toString());
            final Booking updatedBooking = bookingRepository.save(booking);
            return ResponseEntity.ok(updatedBooking);
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
    }
}
