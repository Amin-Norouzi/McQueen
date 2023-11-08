package dev.aminnorouzi.mcqueen.service;

import dev.aminnorouzi.mcqueen.exception.UserAlreadyExistsException;
import dev.aminnorouzi.mcqueen.model.User;
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
        Long chatId = update.getMessage().getChatId();

        exists(chatId);

        User user = User.builder()
                .chatId(chatId)
                .username(update.getMessage().getFrom().getUserName())
                .firstName(update.getMessage().getFrom().getFirstName())
                .lastName(update.getMessage().getFrom().getLastName())
                .build();

        User saved = userRepository.save(user);

        log.info("Created new user: {}", saved);
        return saved;
    }

    public void exists(Long chatId) {
        boolean exists = userRepository.existsByChatId(chatId);
        if (exists) {
            throw new UserAlreadyExistsException();
        }
    }

    public List<User> getAll() {
        List<User> found = userRepository.findAll();

        log.info("Found all users: {}", found);
        return found;
    }

}
