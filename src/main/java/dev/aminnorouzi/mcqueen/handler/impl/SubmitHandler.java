package dev.aminnorouzi.mcqueen.handler.impl;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.handler.Handler;
import dev.aminnorouzi.mcqueen.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubmitHandler implements Handler {

    private final JobService jobService;

    @Override
    public boolean supports(Update update) {
        return update.getCallbackQuery().getData().startsWith("submit");
    }

    @SneakyThrows
    @Override
    public void handle(Update update, Bot bot) {
        Long upworkId = Long.valueOf(update.getCallbackQuery().getData().split("-")[1]);


    }
}
