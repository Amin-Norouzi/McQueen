package dev.aminnorouzi.mcqueen.repository;

import dev.aminnorouzi.mcqueen.model.job.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.upworkId = ?1")
    List<Notification> findByUpworkId(String upworkId);

}
