package ru.otus.spring.ui;

import java.util.Locale;

import ru.otus.spring.domain.CsvRow;

public interface Quiz {

    void addRow(CsvRow row);

    void questionsOutput();

    void setLocale(Locale locale);
}
