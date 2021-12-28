package com.group3.bookandclean.model;

import lombok.Data;

@Data
public class RejectCleaningRequest {
    String bookingId;
    String feedback;
}
