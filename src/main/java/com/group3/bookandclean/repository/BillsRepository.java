package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRepository extends JpaRepository<Bills, Long> {
}
