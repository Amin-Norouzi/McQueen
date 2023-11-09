package dev.aminnorouzi.mcqueen.bot.keyboard.impl;

import dev.aminnorouzi.mcqueen.bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class StatusKeyboard implements Keyboard {

    @Override
    public InlineKeyboardMarkup getKeyboard(String upworkId) {
        InlineKeyboardButton submit = new InlineKeyboardButton();
        submit.setText("Submitted");
        submit.setCallbackData("status-submitted-" + upworkId);

        InlineKeyboardButton reject = new InlineKeyboardButton();
        reject.setText("Rejected");
        reject.setCallbackData("status-rejected-" + upworkId);

        return new InlineKeyboardMarkup(List.of(
                List.of(submit),
                List.of(reject)
        ));
    }
}
