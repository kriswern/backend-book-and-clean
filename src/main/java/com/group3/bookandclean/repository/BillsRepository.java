package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Bills;
import com.group3.bookandclean.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillsRepository extends JpaRepository<Bills, Long> {
    List<Bills> findBillsByCustomer(Customer customer);
}
