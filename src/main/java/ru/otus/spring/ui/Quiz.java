package ru.otus.spring.ui;

import java.util.List;
import java.util.Locale;

import ru.otus.spring.domain.Applicant;
import ru.otus.spring.domain.CsvRow;
import ru.otus.spring.domain.QuizResult;

public interface Quiz {

    List<CsvRow> getRows();

    QuizResult questionsOutput(Applicant applicant);

    void setLocale(Locale locale);

    List<Double> getScores();
}
