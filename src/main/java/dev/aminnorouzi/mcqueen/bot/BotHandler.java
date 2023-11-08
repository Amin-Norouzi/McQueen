package dev.aminnorouzi.mcqueen.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class BotHandler {

    public boolean supports(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    public void handle(Update update, Bot bot) {
//        handlers.stream()
//                .filter(handler -> handler.supports(update))
//                .findFirst()
//                .ifPresent(handler -> handler.handle(update, bot));
    }
}