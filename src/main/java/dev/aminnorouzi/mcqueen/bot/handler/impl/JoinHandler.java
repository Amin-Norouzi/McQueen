package dev.aminnorouzi.mcqueen.bot.handler.impl;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.bot.handler.Handler;
import dev.aminnorouzi.mcqueen.model.user.User;
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
        return update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/start");
    }

    @SneakyThrows
    @Override
    public void handle(Update update, Bot bot) {
        User user = userService.create(update);

        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText("Thanks! You will be notified for jobs posting.");

        bot.execute(message);
    }
}
