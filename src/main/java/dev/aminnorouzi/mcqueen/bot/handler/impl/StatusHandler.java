package dev.aminnorouzi.mcqueen.bot.handler.impl;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.bot.handler.Handler;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.service.JobService;
import dev.aminnorouzi.mcqueen.service.UserService;
import dev.aminnorouzi.mcqueen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatusHandler implements Handler {

    private final JobService jobService;
    private final UserService userService;
    private final StringUtil stringUtil;

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery() &&
                update.getCallbackQuery().getData().startsWith("status");
    }

    @SneakyThrows
    @Override
    public void handle(Update update, Bot bot) {
        CallbackQuery callback = update.getCallbackQuery();
        String status = callback.getData().split("-")[1];
        String upworkId = callback.getData().split("-")[2];
        Integer messageId = callback.getMessage().getMessageId();

        User user = userService.find(update);
        Job job = jobService.update(upworkId, status);

        answer(bot, callback.getId(), "The job status has been updated.");

        String message = stringUtil.generateJobPostingStatusMessage(job, user);

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(bot.getChannel());
        editMessageText.setText(message);
        editMessageText.setMessageId(messageId);
        editMessageText.disableWebPagePreview();
        editMessageText.setParseMode(ParseMode.HTML);
        editMessageText.setReplyMarkup(null);

        bot.execute(editMessageText);
    }
}
