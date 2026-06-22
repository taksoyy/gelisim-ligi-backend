package com.example.demo.proje.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // İşte özel kullanıcı adı kısmı! Boş bırakılamaz ve benzersiz olmalı.
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Daha sonra Spring Security ile şifreleyeceğiz

    // Rekabet Sistemi Alanları
    private int totalXp = 0;
    private int weeklyXp = 0;
    private int streakCount = 0; // Aralıksız görev yapma serisi

    // Her kullanıcının bir ligi olmalı
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League currentLeague;
}