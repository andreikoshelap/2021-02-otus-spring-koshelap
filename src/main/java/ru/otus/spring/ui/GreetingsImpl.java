package ru.otus.spring.ui;

import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.MessageSource;

public class GreetingsImpl implements Greeting{

    private static final String TITLE_LANGUAGE = "title.language";
    private static final String ET = "et";
    private static final String EE = "EE";
    private static final String SMALL_RU = "ru";
    private static final String RU = "RU";
    private static final String PICKED_LANGUAGE = "picked.language";
    private static final Set<Locale> AVAILABLE_LOCALES = Set.of(
            Locale.ENGLISH,
            new Locale(SMALL_RU, RU),
            new Locale(ET, EE));
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private MessageSource msg;

    public GreetingsImpl(MessageSource msg) {
        this.msg = msg;
    }

    @Override
    public Locale defineWorkingLocale() {
        Locale locale = Locale.ENGLISH;
        AVAILABLE_LOCALES.stream()
                .forEach(loc -> System.out.println(msg.getMessage(TITLE_LANGUAGE, new String[] {ANSI_BLUE, ANSI_RESET}, loc)));

        Scanner in = new Scanner(System.in);
        switch (in.nextLine().toUpperCase()) {
            case ET:
                locale  = new Locale(ET, EE);
                break;
            case RU:
                locale = new Locale(SMALL_RU, RU);
                break;
            default:
        }

        System.out.println(msg.getMessage(PICKED_LANGUAGE, new String[] {ANSI_BLUE, ANSI_RESET}, locale));
        return locale;
    }

}
