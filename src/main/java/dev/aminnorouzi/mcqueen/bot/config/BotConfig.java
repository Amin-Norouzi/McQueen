package dev.aminnorouzi.mcqueen.bot.config;

import dev.aminnorouzi.mcqueen.bot.handler.Handler;
import dev.aminnorouzi.mcqueen.bot.handler.impl.AssignHandler;
import dev.aminnorouzi.mcqueen.bot.handler.impl.JoinHandler;
import dev.aminnorouzi.mcqueen.bot.handler.impl.StatusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BotConfig {

    private final JoinHandler joinHandler;
    private final StatusHandler statusHandler;
    private final AssignHandler assignHandler;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Bean
    public String token() {
        return botToken;
    }

    @Bean
    public String username() {
        return botUsername;
    }

    @Bean
    public Set<Handler> handlers() {
        return Set.of(
                joinHandler,
                statusHandler,
                assignHandler
        );
    }
}