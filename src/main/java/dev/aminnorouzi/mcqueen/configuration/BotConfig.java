package dev.aminnorouzi.mcqueen.configuration;

import dev.aminnorouzi.mcqueen.handler.Handler;
import dev.aminnorouzi.mcqueen.handler.impl.JoinHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BotConfig {

    private final JoinHandler joinHandler;

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
                joinHandler
        );
    }
}