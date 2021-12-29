package com.group3.bookandclean.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bills")
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(nullable = false)
    private Double total;

    @OneToMany(targetEntity = Booking.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookings")
    private List<Booking> bookings;

}
