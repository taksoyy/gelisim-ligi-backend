package com.example.demo.proje.service; // Paket adın

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeeklyUpdateScheduler {

    @Autowired
    private LeagueService leagueService;

    // Gerçek Sistem İçin: Her Pazar gece yarısı çalıştırır
    // @Scheduled(cron = "0 0 0 * * SUN")

    // TEST İÇİN: Her 1 dakikada bir çalıştırıp sistemin doğru işlediğini görelim!
    @Scheduled(fixedRate = 60000)
    public void runWeeklyUpdates() {
        leagueService.processWeeklyLeagueUpdates();
    }
}
