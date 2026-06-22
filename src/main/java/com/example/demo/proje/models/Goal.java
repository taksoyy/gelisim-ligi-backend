package com.example.demo.proje.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Örn: "Günde 10.000 adım at"

    private int xpReward; // Görev tamamlanınca verilecek puan

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isCompleted = false;
}