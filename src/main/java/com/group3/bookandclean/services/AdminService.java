package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.Bills;
import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BillsRepository;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.request.BillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    BillsRepository billsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    public boolean addBill(BillRequest billRequest) {

        List<Booking> foundBookings = new ArrayList<>();
        for (Long id : billRequest.getBookingIds()) {
            foundBookings.add(bookingRepository.getById(id));
        }

        Customer activeCustomer = customerRepository.getById(billRequest.getCustomerId());

        Bills newBill = Bills.builder()
                .customer(activeCustomer)
                .total(billRequest.getTotal())
                .bookings(foundBookings)
                .build();

        billsRepository.save(newBill);


        return true;

    }


}
