package ru.otus.spring;

import java.io.IOException;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.event.publisher.CsvPublisher;
import ru.otus.spring.event.publisher.LanguageEventPublisher;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final LanguageEventPublisher languageEventPublisher;
    private final CsvPublisher csvPublisher;
    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Dear reader") String userName) throws IOException {
        this.userName = userName;
//        csvPublisher.prepareQiuz();
        return String.format("Welcome, %s", userName);
    }

    @ShellMethod(value = "Pick language for test", key = {"lang", "language"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void publishEvent(@ShellOption(defaultValue = "classic") String languageKey) {
        languageEventPublisher.publishLanguage(languageKey);
    }

//    @ShellMethod(value = "Show available books", key = {"b", "book", "books"})
//    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
//    public String publishBooks(@ShellOption(defaultValue = "classic") String genreKey) {
//        booksPublisher.publishBooks(genreKey);
//        return "Pick another genre";
//    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null? Availability.unavailable("First login"): Availability.available();
    }
}
