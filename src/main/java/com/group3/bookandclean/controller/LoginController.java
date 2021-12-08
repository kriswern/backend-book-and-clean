package com.group3.bookandclean.controller;

import com.group3.bookandclean.model.AuthenticationRequest;
import com.group3.bookandclean.model.AuthenticationResponse;
import com.group3.bookandclean.services.UserServiceImpl;
import com.group3.bookandclean.utility.JWTutility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JWTutility jwTutility;

    @GetMapping("/")
    public String home() {
        return "Welcome to Cleaning Booking Portal!!";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch(BadCredentialsException e){
                    throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwTutility.generateAccessToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
