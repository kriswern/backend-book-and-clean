package com.group3.bookandclean;

import com.group3.bookandclean.entity.*;
import com.group3.bookandclean.repository.BookingRepository;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
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

    @Override
    public void run(String... args) throws Exception {

        User user1 = User.builder()
                .email("email")
                .password("password")
                .type("type")
                .build();

        User user2 = User.builder()
                .email("email2")
                .password("password2")
                .type("type")
                .build();

        User user3 = User.builder()
                .email("email3")
                .password("password3")
                .type("type")
                .build();

        User user4 = User.builder()
                .email("email4")
                .password("password4")
                .type("type")
                .build();




        Customer customer1 = Customer.builder()
                .name("Oskar Andersson")
                .address("Åhusgatan 3")
                .user(user1)
                .build();

        Customer customer2 = Customer.builder()
                .name("Kenneth Karlsson")
                .address("Afrika")
                .user(user2)
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Cleaner cleaner1 = Cleaner.builder()
                .name("Tina Törner")
                .address("Sjöbo 1")
                .user(user3)
                .build();

        Cleaner cleaner2 = Cleaner.builder()
                .name("Silja Line")
                .address("Östersjön 43a")
                .user(user4)
                .build();

        cleanerRepository.save(cleaner1);
        cleanerRepository.save(cleaner2);

        Booking booking1 = Booking.builder()
                .description(customer1.getName())
                .address(customer1.getAddress())
                .date(new SimpleDateFormat("yyyy-mm-dd").parse("2021-12-01"))
                .time(new SimpleDateFormat("HH:mm").parse("16:30"))
                .customer(customer1)
                .status("unconfirmed")
                .build();

        Booking booking2 = Booking.builder()
                .description(customer2.getName())
                .address(customer2.getAddress())
                .date(new SimpleDateFormat("yyyy-mm-dd").parse("2022-02-01"))
                .time(new SimpleDateFormat("HH:mm").parse("17:25"))
                .customer(customer2)
                .cleaner(cleaner1)
                .status("Confirmed")
                .build();

        Booking booking3 = Booking.builder()
                .description(customer2.getName())
                .address(customer2.getAddress())
                .date(new SimpleDateFormat("yyyy-mm-dd").parse("2023-05-14"))
                .time(new SimpleDateFormat("HH:mm").parse("07:00"))
                .customer(customer2)
                .cleaner(cleaner2)
                .status("Confirmed")
                .build();

        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
        bookingRepository.save(booking3);


    }
}
