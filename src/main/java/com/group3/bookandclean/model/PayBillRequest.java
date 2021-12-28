package com.group3.bookandclean.model;

import lombok.Data;

import java.util.List;

@Data
public class PayBillRequest {

    private Long billId;
    private List<Long> bookingIds;
}
