package dev.aminnorouzi.mcqueen.repository;

import dev.aminnorouzi.mcqueen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.chatId = ?1")
    boolean existsByChatId(Long chatId);

}
