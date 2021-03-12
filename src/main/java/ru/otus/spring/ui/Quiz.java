package ru.otus.spring.ui;

import ru.otus.spring.domain.CsvRow;

public interface Quiz {

    void addRow(CsvRow row);

    void display();

}
