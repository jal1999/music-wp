package com.music.app.controllers;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.music.app.models.Admin;
import com.music.app.pojos.CreateAdminPojo;
import com.music.app.repos.AdminRepository;
import com.music.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Authentication {
    private final AdminRepository adminRepository;
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public Authentication(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login") public ResponseEntity<?> authenticateAdmin(@RequestBody Admin requestBody) {
        Admin admin = adminRepository.findByEmail(requestBody.getEmail());
        if (admin == null)
            return ResponseEntity
                    .status(401)
                    .body("There is no user with the given email.");
        boolean passwordsMatch = encoder.matches(requestBody.getPassword(), admin.getPassword());
        if (!passwordsMatch)
            return ResponseEntity
                    .status(401)
                    .body("There is no user with the given password.");
        try {
            return ResponseEntity
                .status(201)
                .body(Map.of("token", new JwtUtil().generateToken()));
        } catch (JWTCreationException exception){
            return ResponseEntity
                    .status(500)
                    .body("Something went wrong when creating the JWT.");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminPojo requestBody) {
        if (!(requestBody.getPassword().equals(requestBody.getConfirmPassword())))
            return ResponseEntity
                    .status(400)
                    .body("Password and confirm password do not match.");
        Admin newAdmin = new Admin(requestBody.getEmail(), encoder.encode(requestBody.getPassword()));
        adminRepository.save(newAdmin);
        return ResponseEntity
                .status(201)
                .body(newAdmin);
    }
}