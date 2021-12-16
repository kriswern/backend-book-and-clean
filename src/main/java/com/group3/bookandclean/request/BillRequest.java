package com.group3.bookandclean.request;

import com.group3.bookandclean.entity.Customer;
import lombok.Data;

@Data
public class BillRequest {
   private Long customerId;
   private Double total;
}
