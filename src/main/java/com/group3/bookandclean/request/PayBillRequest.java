package com.group3.bookandclean.request;

import lombok.Data;

import java.util.List;

@Data
public class PayBillRequest {

    private Long billId;
    private List<Long> bookingIds;
}
