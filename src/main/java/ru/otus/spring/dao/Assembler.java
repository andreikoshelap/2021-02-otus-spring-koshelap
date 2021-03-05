package ru.otus.spring.dao;



import static java.lang.Character.valueOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.domain.ExcelRow;

@Setter
@Getter
public class Assembler {

    private Resource csvFile;
    private Excel excel;

    private CSVParser getCsv() throws IOException {
        InputStream is=csvFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(br);
    }

    public void fillData() throws IOException {
        CSVParser parser = getCsv();

        for (CSVRecord record : parser) {
            ExcelRow row = new ExcelRow();
            row.setNo(record.get("no"));
            row.setType(record.get("type"));
            row.setQuestion(record.get("question"));
            row.setAnswerOne(record.get("a1"));
            row.setAnswerTwo(record.get("a2"));
            row.setAnswerThree(record.get("a3"));
            row.setAnswerFour(record.get("a4"));
            row.setAnswerFive(record.get("a5"));
            row.setAnswerSix(record.get("a6"));
            excel.addRow(row);
        }
    }

    public void output() {
        excel.display();
    }
}
