package com.example.auth.controller;

import com.example.auth.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String nama = body.get("nama");
        String email = body.get("email");
        String password = body.get("password");

        System.out.println("nama = " + nama + "email = " + email + "password = " + password);

        if (nama.isEmpty()) {
            return ResponseEntity.status(500).body("Nama wajib diisi");
        } else if (email.isEmpty()) {
            return ResponseEntity.status(500).body("Email wajib diisi");
        } else if (password.isEmpty()) {
            return ResponseEntity.status(500).body("Password wajib diisi");
        } else {
            boolean success = authService.register(nama, email, password);
            if (success) {
                return ResponseEntity.status(200).body("Registrasi berhasil");
            } else {
                return ResponseEntity.status(409).body("Email sudah terdaftar");
            }
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email wajib diisi!");
        } else if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Password wajib diisi!");
        } else {
            boolean valid = authService.login(email, password);
            if (valid) {
                return ResponseEntity.ok("Login berhasil");
            } else {
                return ResponseEntity.status(401).body("Email atau password salah");
            }
        }
    }
}
