package com.example.demo.proje.service; // Senin paket adın

import com.example.demo.proje.models.League;
import com.example.demo.proje.models.User;
import com.example.demo.proje.repository.LeagueRepository;
import com.example.demo.proje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserRepository userRepository;

    // UYGULAMA AÇILDIĞINDA LİGLERİ OTOMATİK OLUŞTURUR
    @EventListener(ApplicationReadyEvent.class)
    public void initLeagues() {
        if (leagueRepository.count() == 0) {
            createLeague("Bronz Lig", 1);
            createLeague("Gümüş Lig", 2);
            createLeague("Altın Lig", 3);
            createLeague("Platinium Lig", 4);
            createLeague("Elmas Lig", 5);
            System.out.println("⭐⭐ 5 Temel Lig Veritabanına Otomatik Olarak Kuruldu! ⭐⭐");
        }
    }

    public League createLeague(String name, int level) {
        League league = new League();
        league.setName(name);
        league.setLeagueLevel(level);
        return leagueRepository.save(league);
    }

    public User assignUserToLeague(Long userId, Long leagueId) {
        User user = userRepository.findById(userId).orElseThrow();
        League league = leagueRepository.findById(leagueId).orElseThrow();
        user.setCurrentLeague(league);
        return userRepository.save(user);
    }

    // HAFTALIK LİG GÜNCELLEMESİ (Zorlaştırılmış XP Barajlı)
    public void processWeeklyLeagueUpdates() {
        System.out.println("--- HAFTALIK LİG HESAPLAMALARI BAŞLADI ---");

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            League currentLeague = user.getCurrentLeague();
            if (currentLeague == null) {
                assignUserToLeague(user.getId(), findLeagueByLevel(1).getId());
                continue;
            }

            int weeklyXp = user.getWeeklyXp();
            int currentLevel = currentLeague.getLeagueLevel();

            int promotionXp = getPromotionThreshold(currentLevel);
            int demotionXp = getDemotionThreshold(currentLevel);

            System.out.print("Oyuncu: " + user.getUsername() + " | Mevcut Lig: " + currentLeague.getName() + " | Toplanan XP: " + weeklyXp + " -> Sonuç: ");

            // TERFİ KONTROLÜ
            if (currentLevel < 5 && weeklyXp >= promotionXp) {
                League nextLeague = findLeagueByLevel(currentLevel + 1);
                if (nextLeague != null) {
                    user.setCurrentLeague(nextLeague);
                    System.out.println("🎉 TERFİ ETTİ! Yeni Ligi: " + nextLeague.getName());
                }
            }
            // KÜME DÜŞME KONTROLÜ
            else if (currentLevel > 1 && weeklyXp < demotionXp) {
                League previousLeague = findLeagueByLevel(currentLevel - 1);
                if (previousLeague != null) {
                    user.setCurrentLeague(previousLeague);
                    System.out.println("🔻 KÜME DÜŞTÜ (Yetersiz XP)! Yeni Ligi: " + previousLeague.getName());
                }
            }
            // LİGDE KALMA
            else {
                System.out.println("🛡️ Ligini Korudu.");
            }

            // Herkesin haftalık XP'sini sıfırla
            user.setWeeklyXp(0);
            userRepository.save(user);
        }
        System.out.println("--- HAFTALIK GÜNCELLEMELER TAMAMLANDI ---");
    }

    // --- YENİ VE ZORLU XP BARAJLARI ---

    // Bir üst lige ÇIKMAK için gereken XP miktarı
    private int getPromotionThreshold(int level) {
        switch (level) {
            case 1: return 1000; // Bronz -> Gümüş
            case 2: return 2000; // Gümüş -> Altın
            case 3: return 4000; // Altın -> Platinium
            case 4: return 8000; // Platinium -> Elmas
            default: return 999999; // Elmas'tan yukarı çıkış yok
        }
    }

    // O ligde KALMAK (düşmemek) için gereken MİNİMUM XP miktarı
    private int getDemotionThreshold(int level) {
        switch (level) {
            case 1: return 0;    // Bronz'dan aşağı düşüş yok
            case 2: return 500;  // Gümüş'te kalmak için
            case 3: return 1000; // Altın'da kalmak için
            case 4: return 2000; // Platinium'da kalmak için
            case 5: return 4000; // Elmas'ta tutunmak için en az 4000 lazım!
            default: return 0;
        }
    }

    private League findLeagueByLevel(int level) {
        return leagueRepository.findAll().stream()
                .filter(l -> l.getLeagueLevel() == level)
                .findFirst()
                .orElse(null);
    }
}
