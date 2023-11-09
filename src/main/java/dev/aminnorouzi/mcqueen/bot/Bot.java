package dev.aminnorouzi.mcqueen.bot;

import dev.aminnorouzi.mcqueen.bot.handler.impl.UpdateHandler;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingBot {

    private final UpdateHandler handler;
    private final String token;
    private final String username;

    public Bot(UpdateHandler handler, String token, String username) {
        super(token);
        this.handler = handler;
        this.token = token;
        this.username = username;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (handler.supports(update)) {
            handler.handle(update, this);
        }
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }
}