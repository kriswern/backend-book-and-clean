package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingByCleanerId(Long id);
    List<Booking> findBookingByCustomerId(Long id);
}
