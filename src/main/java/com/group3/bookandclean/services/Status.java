package com.group3.bookandclean.services;

public enum Status {
    UNCONFIRMED("Unconfirmed"), CONFIRMED("Confirmed"), BOOKED("Booked"), IN_PROGRESS("In progress"), DONE("Done"), APPROVED("Approved"), BILLED("Billed"), PAYED("Payed");

    private String msg;

    Status(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
