package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Booking;
import com.group3.bookandclean.entity.Cleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CleanerRepository extends JpaRepository<Cleaner, Long> {

    @Query("SELECT b FROM Booking b WHERE b.cleanerId = ?1")
    List<Booking> fetchBookingsByCleanerId(Long cleanerId);
}
