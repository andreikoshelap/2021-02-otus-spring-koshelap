package ru.otus.spring;

import java.io.IOException;
import java.util.Locale;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.events.GreetingPublisher;
import ru.otus.spring.service.DataTransformer;
import ru.otus.spring.service.QuizPresentation;

@ShellComponent
@RequiredArgsConstructor
public class GreetingEventsCommands {

    private final GreetingPublisher eventsPublisher;
    private final QuizPresentation presentation;
    private final DataTransformer transformer;

    @ShellMethod(value = "Pick language for quiz - EN for English, RU for russian, ET for Estonian", key = { "l", "language" })
    public Locale chooseLanguage(@ShellOption(help = "Pick language for quiz - EN for English, RU for russian, ET for Estonian",
            defaultValue = "en") String lang) throws IOException {
        Locale locale = Locale.ENGLISH;
        //        switch (lang) {
        //            case "et":
        //                locale  = new Locale("et", "EE");
        //                break;
        //            case "ru":
        //                locale = new Locale("ru", "RU");
        //                break;
        //            default:
        //        }
        transformer.exportData();
        presentation.defineLanguage();
        presentation.output();
        eventsPublisher.publish();
        return locale;
    }

}
