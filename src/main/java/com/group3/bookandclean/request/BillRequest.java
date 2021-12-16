package com.group3.bookandclean.request;


import lombok.Data;


import java.util.List;

@Data
public class BillRequest {
   private Long customerId;
   private Double total;
   private List<Long> bookingIds;
}
