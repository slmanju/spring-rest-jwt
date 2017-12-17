package com.manjula.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @GetMapping("/admin")
    public ResponseEntity<?> getAdminMessage() {
        return ResponseEntity.ok(new Message("Hello Admin"));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserMessage() {
        return ResponseEntity.ok(new Message("Hello User"));
    }

    @GetMapping("/anonymous")
    public ResponseEntity<?> getAnonymousMessage() {
        return ResponseEntity.ok(new Message("Hello anonymous"));
    }

}
