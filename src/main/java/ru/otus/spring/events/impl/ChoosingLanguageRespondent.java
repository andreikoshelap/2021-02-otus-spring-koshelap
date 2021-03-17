package ru.otus.spring.events.impl;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Order(10)
@Component
public class ChoosingLanguageRespondent implements ApplicationListener<LanguagesPropositionEvent> {

    @Override
    @SneakyThrows
    public void onApplicationEvent(LanguagesPropositionEvent languagesPropositionEvent) {
        Thread.sleep(100);
        String locale  = languagesPropositionEvent.getPayload();

        System.out.println("locale" + locale);
    }
}
