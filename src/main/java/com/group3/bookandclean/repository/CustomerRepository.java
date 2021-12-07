package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT b FROM Booking b WHERE b.customerId = ?1")
    List<Booking> fetchBookingsByCustomerId(Long customerId);

}
