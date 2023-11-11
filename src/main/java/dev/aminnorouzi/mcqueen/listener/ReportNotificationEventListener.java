package dev.aminnorouzi.mcqueen.listener;

import dev.aminnorouzi.mcqueen.bot.Bot;
import dev.aminnorouzi.mcqueen.event.ReportNotificationEvent;
import dev.aminnorouzi.mcqueen.model.report.Report;
import dev.aminnorouzi.mcqueen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class ReportNotificationEventListener implements ApplicationListener<ReportNotificationEvent> {

    private final Bot bot;
    private final StringUtil stringUtil;

    @Override
    public void onApplicationEvent(ReportNotificationEvent event) {
        Report report = event.getReport();
        String message = stringUtil.generateDailyReportMessage(report);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.disableWebPagePreview();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(bot.getChannel());

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
