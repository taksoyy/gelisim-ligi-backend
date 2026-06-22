package com.example.demo.proje.repository; // Kendi paket adına dikkat et

import com.example.demo.proje.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}