package dev.aminnorouzi.mcqueen.bot.keyboard.impl;

import dev.aminnorouzi.mcqueen.bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class AssignKeyboard implements Keyboard {

    @Override
    public InlineKeyboardMarkup getKeyboard(String upworkId) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Assign to me 🤚🏻");
        button.setCallbackData("assign-" + upworkId);

        return new InlineKeyboardMarkup(List.of(List.of(button)));
    }
}
