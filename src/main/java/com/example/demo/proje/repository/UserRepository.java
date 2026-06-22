package com.example.demo.proje.repository;

import com.example.demo.proje.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // YENİ EKLENEN KOD: Kullanıcıları haftalık XP'ye göre çoktan aza doğru sırala (İlk 10 kişi)
    List<User> findTop10ByOrderByWeeklyXpDesc();
}
