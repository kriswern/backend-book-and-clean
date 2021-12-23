package com.group3.bookandclean.request;

import lombok.Data;

import java.util.List;

@Data
public class BookingsInBillRequest {
    private List<Long> bookingIds;
}
