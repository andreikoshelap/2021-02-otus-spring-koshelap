package ru.otus.spring.events.impl;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class LanguagesPropositionEvent extends ApplicationEvent {

    @Getter
    private final String payload;

    public LanguagesPropositionEvent(Object source) {
        super(source);
        this.payload = "Pick language for quiz - EN for English, RU for russian, ET for Estonian";
    }
}
