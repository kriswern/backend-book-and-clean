package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.Bills;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.repository.BillsRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.request.BillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
     @Autowired
    BillsRepository billsRepository;

     @Autowired
    CustomerRepository customerRepository;

     public boolean addBill (BillRequest billRequest){

         Customer activeCustomer = customerRepository.getById(billRequest.getCustomerId());

             Bills newBill = Bills.builder()
                     .customer(activeCustomer)
                     .total(billRequest.getTotal())
                     .build();

            billsRepository.save(newBill);


             return true;

     }



}
