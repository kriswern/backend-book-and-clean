package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
