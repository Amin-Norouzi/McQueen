package dev.aminnorouzi.mcqueen.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface Keyboard {

    InlineKeyboardMarkup getKeyboard(String upworkId);
}
