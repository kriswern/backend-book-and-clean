package com.group3.bookandclean.model;

import lombok.Data;

import java.util.List;

@Data
public class BookingsInBillRequest {
    private List<Long> bookingIds;
}
