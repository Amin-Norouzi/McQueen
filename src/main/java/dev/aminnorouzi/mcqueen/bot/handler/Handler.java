package dev.aminnorouzi.mcqueen.bot.handler;

import dev.aminnorouzi.mcqueen.bot.Bot;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public interface Handler {

    boolean supports(Update update);

    void handle(Update update, Bot bot);

    @SneakyThrows
    default void answer(Bot bot, String callbackId, String message) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);

        bot.execute(answer);
    }
}