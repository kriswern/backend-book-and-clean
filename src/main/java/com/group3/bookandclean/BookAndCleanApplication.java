package com.group3.bookandclean;

import com.group3.bookandclean.entity.*;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.services.Status;
import com.group3.bookandclean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication ( exclude = {SecurityAutoConfiguration.class} )
public class BookAndCleanApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookAndCleanApplication.class, args);
    }

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CleanerRepository cleanerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user1 = User.builder()
                .email("customer")
                .password(passwordEncoder.encode("customer"))
                .type("customer")
                .build();

        User user2 = User.builder()
                .email("customer2")
                .password(passwordEncoder.encode("customer2"))
                .type("customer")
                .build();

        User user3 = User.builder()
                .email("admin")
                .password(passwordEncoder.encode("admin"))
                .type("admin")
                .build();

        userService.saveUser(user3);

        User user4 = User.builder()
                .email("cleaner")
                .password(passwordEncoder.encode("cleaner"))
                .type("cleaner")
                .build();

        User user5 = User.builder()
                .email("cleaner2")
                .password(passwordEncoder.encode("cleaner2"))
                .type("cleaner")
                .build();

        Customer customer1 = Customer.builder()
                .name("Signe Student")
                .address("Karlskronaplan 3")
                .user(user1)
                .build();

        Customer customer2 = Customer.builder()
                .name("Kenneth Karlsson")
                .address("von Rosens väg 15")
                .user(user2)
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Cleaner cleaner1 = Cleaner.builder()
                .name("Marcus Karlsson")
                .address("Sjöbo byväg 1")
                .user(user4)
                .build();

        Cleaner cleaner2 = Cleaner.builder()
                .name("Caren Johnson")
                .address("Östersjön 43a")
                .user(user5)
                .build();

        cleanerRepository.save(cleaner1);
        cleanerRepository.save(cleaner2);

        Booking booking1 = Booking.builder()
                .description(customer1.getName())
                .address(customer1.getAddress())
                .date(LocalDate.parse("2021-12-01"))
                .time(LocalTime.parse("16:30"))
                .customer(customer1)
                .status(Status.UNCONFIRMED.toString())
                .build();

        Booking booking2 = Booking.builder()
                .description(customer2.getName())
                .address(customer2.getAddress())
                .date(LocalDate.parse("2022-07-01"))
                .time(LocalTime.parse("17:25"))
                .customer(customer2)
                .cleaner(cleaner1)
                .status(Status.CONFIRMED.toString())
                .build();

        Booking booking3 = Booking.builder()
                .description(customer2.getName())
                .address(customer2.getAddress())
                .date(LocalDate.parse("2023-05-14"))
                .time(LocalTime.parse("07:00"))
                .customer(customer2)
                .cleaner(cleaner2)
                .status(Status.CONFIRMED.toString())
                .build();


        Booking booking4 = Booking.builder()
                .description(customer2.getName())
                .address(customer2.getAddress())
                .date(LocalDate.parse("2022-02-01"))
                .time(LocalTime.parse("17:25"))
                .customer(customer2)
                .cleaner(cleaner2)
                .status(Status.CONFIRMED.toString())
                .build();

        Booking booking5 = Booking.builder()
                .description(customer2.getName())
                .address(customer2.getAddress())
                .date(LocalDate.parse("2021-12-01"))
                .time(LocalTime.parse("17:25"))
                .customer(customer2)
                .status(Status.UNCONFIRMED.toString())
                .build();

        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
        bookingRepository.save(booking3);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);


    }
}
