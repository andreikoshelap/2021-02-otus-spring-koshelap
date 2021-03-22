package ru.otus.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;

import ru.otus.spring.domain.CsvRow;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.QuizImpl;

public class DataTransformer {

    protected static final char DELIMITER = ';';
    private Resource csvFile;

    public DataTransformer(Resource csvFile) {
        this.csvFile = csvFile;
    }

    private CSVParser getParsedCsv() throws IOException {
        InputStream is=csvFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.DEFAULT.withDelimiter(DELIMITER).withHeader().parse(br);
    }

    public Quiz fillData() throws IOException {
        List<CsvRow> rowList = new ArrayList<>();
        for (CSVRecord record : getParsedCsv()) {
            CsvRow row = new CsvRow();
            row.setNo(record.get("no"));
            row.setType(record.get("type"));
            row.setQuestion(record.get("question"));
            row.setAnswerA(record.get("A"));
            row.setAnswerB(record.get("B"));
            row.setAnswerC(record.get("C"));
            row.setAnswerD(record.get("D"));
            row.setAnswerE(record.get("E"));
            row.setAnswerF(record.get("F"));
            row.setCorrectAnswer(record.get("correct"));
            rowList.add(row);
        }
        return new QuizImpl(rowList);
    }

}
