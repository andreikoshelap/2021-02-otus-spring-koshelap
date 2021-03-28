package ru.otus.spring.ui;

import java.util.Locale;

import java.util.List;

import ru.otus.spring.domain.CsvRow;

public interface Quiz {

    List<CsvRow> getRows();

    void questionsOutput();

    void setLocale(Locale locale);

    List<Double> getScores();
}
