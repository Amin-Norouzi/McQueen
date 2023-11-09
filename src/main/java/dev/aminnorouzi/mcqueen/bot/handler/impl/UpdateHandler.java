package dev.aminnorouzi.mcqueen.bot.handler.impl;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.bot.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UpdateHandler implements Handler {

    private final Set<Handler> handlers;

    @Override
    public boolean supports(Update update) {
        return (update.hasMessage() && update.getMessage().hasText()) ||
                update.hasCallbackQuery();
    }

    @Override
    public void handle(Update update, Bot bot) {
        handlers.stream()
                .filter(handler -> handler.supports(update))
                .findFirst()
                .ifPresent(handler -> handler.handle(update, bot));
    }
}