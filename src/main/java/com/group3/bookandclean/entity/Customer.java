package com.group3.bookandclean.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "user", insertable = false, updatable = false)
    private Long cleanerId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Booking> bookings;


}
