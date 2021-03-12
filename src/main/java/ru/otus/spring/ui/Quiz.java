package ru.otus.spring.ui;

import org.springframework.context.MessageSource;

import ru.otus.spring.domain.CsvRow;

public interface Quiz {

    void addRow(CsvRow row);

    void display(MessageSource msg);

}
