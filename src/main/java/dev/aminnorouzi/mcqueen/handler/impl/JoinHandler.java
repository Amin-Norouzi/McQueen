package dev.aminnorouzi.mcqueen.handler.impl;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.exception.UserAlreadyExistsException;
import dev.aminnorouzi.mcqueen.handler.Handler;
import dev.aminnorouzi.mcqueen.model.User;
import dev.aminnorouzi.mcqueen.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class JoinHandler implements Handler {

    private final UserService userService;

    @Override
    public boolean supports(Update update) {
        return getText(update).equalsIgnoreCase("/start");
    }

    @SneakyThrows
    @Override
    public void handle(Update update, Bot bot) {
        try {
            User user = userService.create(update);

            SendMessage message = new SendMessage();
            message.setChatId(user.getChatId());
            message.setText("Thanks! You will be notified for jobs posting.");

            bot.execute(message);

        } catch (UserAlreadyExistsException exception) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            message.setText("You already have an account in the bot!");

            bot.execute(message);

            log.error("Caught an error: User already exists.");
        }
    }
}
