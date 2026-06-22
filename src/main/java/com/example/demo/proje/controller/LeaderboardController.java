package com.example.demo.proje.controller;

import com.example.demo.proje.models.User;
import com.example.demo.proje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = "*") // Vercel'deki sitemizin buraya erişmesine izin ver
public class LeaderboardController {

    @Autowired
    private UserRepository userRepository;

    // En iyi 10 oyuncuyu getiren API ucu
    @GetMapping("/top")
    public ResponseEntity<List<User>> getTopPlayers() {
        List<User> topPlayers = userRepository.findTop10ByOrderByWeeklyXpDesc();
        return ResponseEntity.ok(topPlayers);
    }
}


