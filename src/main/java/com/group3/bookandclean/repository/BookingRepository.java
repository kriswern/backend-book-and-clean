package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingByCleanerId(Long id);
    List<Booking> findBookingByCustomerId(Long id);





}
