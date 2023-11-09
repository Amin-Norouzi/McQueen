package dev.aminnorouzi.mcqueen.bot.handler.impl;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.bot.handler.Handler;
import dev.aminnorouzi.mcqueen.bot.keyboard.impl.StatusKeyboard;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Notification;
import dev.aminnorouzi.mcqueen.model.job.Status;
import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.service.JobService;
import dev.aminnorouzi.mcqueen.service.NotificationService;
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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AssignHandler implements Handler {

    private final JobService jobService;
    private final UserService userService;
    private final NotificationService notificationService;
    private final StringUtil stringUtil;

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery() &&
                update.getCallbackQuery().getData().startsWith("assign");
    }

    @SneakyThrows
    @Override
    public void handle(Update update, Bot bot) {
        CallbackQuery callback = update.getCallbackQuery();
        Long chatId = callback.getFrom().getId();
        String upworkId = callback.getData().split("-")[1];

        User user = userService.getByChatId(chatId);
        Job job = jobService.assign(upworkId, user.getId());

        answer(bot, callback.getId(), "This job is now assigned to you.");

        String message = stringUtil.generateJobPostingSAssignMessage(job, user);

        List<Notification> notifications = notificationService.getAllByUpworkId(upworkId);
        for (Notification notification : notifications) {
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(notification.getChatId());
            editMessageText.setText(message);
            editMessageText.setMessageId(notification.getMessageId());
            editMessageText.disableWebPagePreview();
            editMessageText.setParseMode(ParseMode.HTML);

            if (notification.getUserId().equals(job.getUserId())) {
                StatusKeyboard keyboard = new StatusKeyboard();
                editMessageText.setReplyMarkup(keyboard.getKeyboard(notification.getUpworkId()));
            } else {
                editMessageText.setReplyMarkup(null);
            }

            bot.execute(editMessageText);
        }
    }
}
