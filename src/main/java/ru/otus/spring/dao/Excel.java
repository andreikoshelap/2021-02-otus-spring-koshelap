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

    public void display() {
        rows.stream().forEach(p ->{
            System.out.println(p.getNo());
            System.out.println(p.getQuestion());
            System.out.println(p.getAnswerOne());
            System.out.println(p.getAnswerTwo());
            System.out.println(p.getAnswerThree());
            System.out.println(p.getAnswerFour());
            System.out.println(p.getAnswerFive());
            if(p.getAnswerSix() != null) {
                System.out.println(p.getAnswerSix());
            }
            System.out.println();
        });
    }
}
