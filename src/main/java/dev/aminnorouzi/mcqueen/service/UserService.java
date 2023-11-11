package dev.aminnorouzi.mcqueen.service;

import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(Update update) {
        User user = User.builder()
                .chatId(update.getCallbackQuery().getFrom().getId())
                .username(update.getCallbackQuery().getFrom().getUserName())
                .firstName(update.getCallbackQuery().getFrom().getFirstName())
                .lastName(update.getCallbackQuery().getFrom().getLastName())
                .build();

        User saved = userRepository.save(user);

        log.info("Created new user: {}", saved);
        return saved;
    }

    public User find(Update update) {
        Long chatId = update.getCallbackQuery().getFrom().getId();

        if (!exists(chatId)) {
            return create(update);
        }

        return getByChatId(chatId);
    }

    public boolean exists(Long chatId) {
        return userRepository.existsByChatId(chatId);
    }

    public List<User> getAll() {
        List<User> found = userRepository.findAll();

        log.info("Found all users: {}", found);
        return found;
    }

    public User getByChatId(Long chatId) {
        User found = userRepository.findByChatId(chatId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist!"));

        log.info("Found a user: {}", found);
        return found;
    }

}
