package dev.aminnorouzi.mcqueen.service;

import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Notification;
import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification save(Message message, Job job, User user) {
        Notification notification = Notification.builder()
                .userId(user.getId())
                .chatId(user.getChatId())
                .messageId(message.getMessageId())
                .upworkId(job.getUpworkId())
                .build();

        Notification saved = notificationRepository.save(notification);

        log.info("Saved new notification: {}", saved);
        return saved;
    }

    public List<Notification> getAllByUpworkId(String upworkId) {
        List<Notification> found = notificationRepository.findByUpworkId(upworkId);

        log.info("Found all notifications by upworkId: {}", found);
        return found;
    }
}
