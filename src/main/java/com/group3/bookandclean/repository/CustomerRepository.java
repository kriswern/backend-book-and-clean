package com.group3.bookandclean.repository;

import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUser(User user);
}
