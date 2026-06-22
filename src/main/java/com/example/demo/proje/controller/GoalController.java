package com.example.demo.proje.controller; // Senin paket adın

import com.example.demo.proje.models.Goal;
import com.example.demo.proje.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // 1. Yeni Hedef Oluşturma Ucu
    @PostMapping("/user/{userId}")
    public ResponseEntity<Goal> createGoal(@PathVariable Long userId, @RequestBody Goal goal) {
        Goal createdGoal = goalService.createGoal(userId, goal);
        return ResponseEntity.ok(createdGoal);
    }

    // 2. Hedefi Tamamlama Ucu (Oyunlaştırmanın Kalbi!)
    @PostMapping("/{goalId}/complete")
    public ResponseEntity<String> completeGoal(@PathVariable Long goalId) {
        Goal completedGoal = goalService.completeGoal(goalId);
        return ResponseEntity.ok("Tebrikler! Görev tamamlandı. Kazanılan XP: " + completedGoal.getXpReward());
    }
}
