package ru.otus.spring.ui;

import java.util.List;

import ru.otus.spring.domain.CsvRow;

public interface Quiz {

    void addRow(CsvRow row);

    void display();

    List<Double> getScores();
}
