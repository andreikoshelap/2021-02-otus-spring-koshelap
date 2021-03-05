package ru.otus.spring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelRow {

    private String no;
    private String type;
    private String result;
    private String correct;
}
