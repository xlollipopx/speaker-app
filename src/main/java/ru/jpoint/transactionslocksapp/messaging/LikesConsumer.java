package ru.jpoint.transactionslocksapp.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;
import ru.jpoint.transactionslocksapp.dto.Likes;

import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LikesConsumer implements Consumer<Likes> {

    private final SpeakerMessageProcessor messageProcessor;

    @Override
    public void accept(Likes likes) {
        log.warn("Message received {}", likes);
        messageProcessor.processOneMessage(likes);
    }

    @Bean
    ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> customizer() {
        return (container, dest, group) -> {
            if (group.equals("likes-consumer-group")) {
                container.setCommonErrorHandler(new DefaultErrorHandler(
                        new FixedBackOff(0, 0)));
            }
        };
    }
}
