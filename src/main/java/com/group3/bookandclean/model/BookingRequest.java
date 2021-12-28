package com.group3.bookandclean.model;

import lombok.Data;

@Data
public class BookingRequest {

    String name;
    String address;
    String date;
    String time;
    String priceListId;

    String customerId;
}
