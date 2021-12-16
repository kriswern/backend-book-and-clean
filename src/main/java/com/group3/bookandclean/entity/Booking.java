package com.group3.bookandclean.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @OneToOne(targetEntity = PriceList.class)
    @JoinColumn(name = "priceList")
    private PriceList priceList;

    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer", nullable = false)
    @JsonIgnore
    private Customer customer;


    @Column(name = "customer", insertable = false, updatable = false)
    private Long customerId;

    @ManyToOne(targetEntity = Cleaner.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "cleaner")
    @JsonIgnore
    private Cleaner cleaner;

    @Column(name = "cleaner", insertable = false, updatable = false)
    private Long cleanerId;

    @Column(nullable = false)
    private String status;
}
