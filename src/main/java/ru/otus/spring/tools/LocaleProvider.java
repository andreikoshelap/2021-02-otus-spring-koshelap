package ru.otus.spring.tools;

import java.util.Locale;

public interface LocaleProvider {

    Locale defineWorkingLocale(String  key);

}
