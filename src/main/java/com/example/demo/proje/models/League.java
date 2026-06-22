package com.example.demo.proje.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Örn: Bronz, Gümüş, Altın

    private int leagueLevel; // 1: En alt lig, 5: Şampiyonlar Ligi
}