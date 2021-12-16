package com.group3.bookandclean.entity;


import lombok.*;

import javax.persistence.*;


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

    @OneToOne(targetEntity = PriceList.class)
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(nullable = false)
    private Double total;


}
