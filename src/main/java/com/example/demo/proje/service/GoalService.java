package com.example.demo.proje.service; // Senin paket adın (örn: com.proje.service)

import com.example.demo.proje.models.Goal; // Kendi paket adın
import com.example.demo.proje.models.User; // Kendi paket adın
import com.example.demo.proje.repository.GoalRepository; // Kendi paket adın
import com.example.demo.proje.repository.UserRepository; // Kendi paket adın
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // 1. Yeni Görev Ekleme
    public Goal createGoal(Long userId, Goal goal) {
        // Önce kullanıcının veritabanında var olup olmadığına bakıyoruz
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        goal.setUser(user); // Görevi bu kullanıcıya bağlıyoruz
        goal.setCompleted(false); // Yeni görev varsayılan olarak "tamamlanmadı" başlar
        return goalRepository.save(goal);
    }

    // 2. Görevi Tamamlama ve Puan (XP) Kazanma!
    public Goal completeGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı!"));

        // Eğer görev zaten tamamlanmamışsa işlemleri yap
        if (!goal.isCompleted()) {
            goal.setCompleted(true);
            goalRepository.save(goal);

            // UserService'teki yazdığımız metodu çağırıp kullanıcıya görevdeki XP'yi ekliyoruz!
            userService.addXpToUser(goal.getUser().getId(), goal.getXpReward());
        }
        return goal;
    }
}
