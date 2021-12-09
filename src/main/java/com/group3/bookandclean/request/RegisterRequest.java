package com.group3.bookandclean.request;

import lombok.Data;

@Data
public class RegisterRequest {
    String name;
    String email;
    String password;
    String address;
    String type;

}
