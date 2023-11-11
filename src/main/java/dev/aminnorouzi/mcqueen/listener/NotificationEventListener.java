package dev.aminnorouzi.mcqueen.listener;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.bot.keyboard.impl.AssignKeyboard;
import dev.aminnorouzi.mcqueen.event.NotificationEvent;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class NotificationEventListener implements ApplicationListener<NotificationEvent> {

    private final Bot bot;
    private final StringUtil stringUtil;

    @Override
    public void onApplicationEvent(NotificationEvent event) {
        for (Job job : event.getJobs()) {
            AssignKeyboard keyboard = new AssignKeyboard();
            String message = stringUtil.generateJobPostingMessage(job);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(message);
            sendMessage.disableWebPagePreview();
            sendMessage.setParseMode(ParseMode.HTML);
            sendMessage.setReplyMarkup(keyboard.getKeyboard(job.getUpworkId()));
            sendMessage.setChatId(bot.getChannel());

            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
