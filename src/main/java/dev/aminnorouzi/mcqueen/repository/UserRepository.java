package dev.aminnorouzi.mcqueen.repository;

import dev.aminnorouzi.mcqueen.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.chatId = ?1")
    boolean existsByChatId(Long chatId);

    @Query("select u from User u where u.chatId = ?1")
    Optional<User> findByChatId(Long chatId);

}
