package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CleanerRepository extends JpaRepository<Cleaner, Long> {
    Cleaner findCleanerByUser(User user);
}
