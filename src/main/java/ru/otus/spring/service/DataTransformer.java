package ru.otus.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.dao.CsvDao;
import ru.otus.spring.domain.CsvRow;

@Setter
@Getter
public class DataTransformer {

    private static final char DELIMITER = ';';
    private Resource csvFile;
    private CsvDao csv;

    public DataTransformer(Resource csvFile, CsvDao csv) {
        this.csvFile = csvFile;
        this.csv = csv;
    }

    private CSVParser getCsv() throws IOException {
        InputStream is=csvFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.DEFAULT.withDelimiter(DELIMITER).withHeader().parse(br);
    }

    public void fillData() throws IOException {
        csv.setRows(new ArrayList<>());
        for (CSVRecord record : getCsv()) {
            CsvRow row = new CsvRow();
            row.setNo(record.get("no"));
            row.setType(record.get("type"));
            row.setQuestion(record.get("question"));
            row.setAnswerOne(record.get("a1"));
            row.setAnswerTwo(record.get("a2"));
            row.setAnswerThree(record.get("a3"));
            row.setAnswerFour(record.get("a4"));
            row.setAnswerFive(record.get("a5"));
            row.setAnswerSix(record.get("a6"));
            csv.addRow(row);
        }
    }

    public void output() {
        csv.display();
    }
}
