package ru.otus.spring.events.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.events.GreetingPublisher;

@Service
@RequiredArgsConstructor
public class LanguageDeciderPublisher implements GreetingPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish() {
        publisher.publishEvent(new LanguagesPropositionEvent(this));
    }

}
