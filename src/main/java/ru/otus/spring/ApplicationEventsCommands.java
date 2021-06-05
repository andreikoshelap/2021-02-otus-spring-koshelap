package ru.otus.spring;

import java.io.IOException;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Applicant;
import ru.otus.spring.event.publisher.LanguageEventPublisher;
import ru.otus.spring.service.QuizPresentation;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final LanguageEventPublisher languageEventPublisher;
//    private final CsvPublisher csvPublisher;
//    private final Quiz quiz;
    private final QuizPresentation quizPresentation;
    private Applicant applicant;
    private String languageKey;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login( @ShellOption(defaultValue = "John") String firstName,
            @ShellOption(defaultValue = "Doe") String lastName) {
        this.applicant = new Applicant(firstName, lastName);
        return String.format("Welcome, %s %s", firstName, lastName);
    }

    @ShellMethod(value = "Pick language for test", key = {"lang", "language"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void publishEvent(@ShellOption(defaultValue = "EN") String languageKey) {
        this.languageKey = languageKey;
        languageEventPublisher.publishLanguage(languageKey);
    }

    @ShellMethod(value = "Start test", key = { "t" })
    @ShellMethodAvailability(value = "isLanguageChosen")
    public void publishTest() {
//        QuizPresentationImpl presentation = context.getBean(QuizPresentationImpl.class);
        try {
            quizPresentation.executeExam(applicant);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            csvPublisher.prepareQiuz();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private Availability isPublishEventCommandAvailable() {
        return applicant == null? Availability.unavailable("First login"): Availability.available();
    }

    private Availability isLanguageChosen() {
        return languageKey == null? Availability.unavailable("Choose language"): Availability.available();
    }
}
