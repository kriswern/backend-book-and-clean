package com.group3.bookandclean.request;

import lombok.Data;

@Data
public class BookingRequest {

    String name;
    String address;
    String date;
    String time;

    String customerId;
}
