package com.example.demo.proje.controller; // Kendi paket adına dikkat et!

import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.proje.models.User;
import com.example.demo.proje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")  // <--- İŞTE KAPILARI AÇAN SİHİRLİ KOD
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/{userId}/add-xp")
    public ResponseEntity<String> addXp(@PathVariable Long userId, @RequestParam int xp) {
        userService.addXpToUser(userId, xp);
        return ResponseEntity.ok("Kullanıcıya " + xp + " XP başarıyla eklendi.");
    }
}