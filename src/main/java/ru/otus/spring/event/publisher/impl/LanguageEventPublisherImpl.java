package ru.otus.spring.event.publisher.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.event.LanguageEvent;
import ru.otus.spring.event.publisher.LanguageEventPublisher;
import ru.otus.spring.tools.LocaleProvider;

@Service
@RequiredArgsConstructor
public class LanguageEventPublisherImpl implements LanguageEventPublisher {

    private final ApplicationEventPublisher publisher;
    private final LocaleProvider localeProvider;

    @Override
    public void publishLanguage(String key) {
        publisher.publishEvent(new LanguageEvent(this, localeProvider, key));
    }
}
