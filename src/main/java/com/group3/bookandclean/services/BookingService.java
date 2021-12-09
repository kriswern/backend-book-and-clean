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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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


    public boolean addCleaner(AddCleanerRequest request) {
        Cleaner cleaner = cleanerRepository.getById(parseLong(request.getCleanerId()));
        Booking booking = bookingRepository.getById(parseLong(request.getBookingId()));

        booking.setCleaner(cleaner);
        bookingRepository.save(booking);
    return true;

    }


}
