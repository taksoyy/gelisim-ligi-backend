package com.example.demo.proje.controller; // Paket adın

import com.example.demo.proje.models.League;
import com.example.demo.proje.models.User;
import com.example.demo.proje.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/leagues")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    // Yeni Lig Ekleme
    @PostMapping("/create")
    public ResponseEntity<League> createLeague(@RequestParam String name, @RequestParam int level) {
        League createdLeague = leagueService.createLeague(name, level);
        return ResponseEntity.ok(createdLeague);
    }

    // Kullanıcıyı Lige Atama
    @PostMapping("/assign")
    public ResponseEntity<User> assignUserToLeague(@RequestParam Long userId, @RequestParam Long leagueId) {
        User updatedUser = leagueService.assignUserToLeague(userId, leagueId);
        return ResponseEntity.ok(updatedUser);
    }
}
