package com.example.demo.proje.service; // Senin paket adın (örn: com.proje.service)

import com.example.demo.proje.models.User; // Kendi paket adın
import com.example.demo.proje.repository.UserRepository; // Kendi paket adın
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Kullanıcı Kaydetme Metodu
    public User registerUser(User user) {
        // İleride şifreyi burada hashleyeceğiz
        return userRepository.save(user);
    }

    // Puan Ekleme Metodu
    public void addXpToUser(Long userId, int xp) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        user.setTotalXp(user.getTotalXp() + xp);
        user.setWeeklyXp(user.getWeeklyXp() + xp);
        userRepository.save(user);
    }
}