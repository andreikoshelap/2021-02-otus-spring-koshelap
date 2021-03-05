package ru.otus.spring.dao;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.domain.ExcelRow;

@Setter
@Getter
public class Excel {

    private List<ExcelRow> rows;

    public void addRow(ExcelRow row) {
        this.rows.add(row);
    }
}
