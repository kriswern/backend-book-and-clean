package com.group3.bookandclean.request;

import lombok.Data;

@Data
public class RejectCleaningRequest {
    String bookingId;
    String feedback;
}
