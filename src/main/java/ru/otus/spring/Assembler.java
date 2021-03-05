package ru.otus.spring;



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
import ru.otus.spring.dao.Excel;
import ru.otus.spring.domain.ExcelRow;

@Setter
@Getter
public class Assembler {

    private Resource csvFile;
    private Excel excel;

    private CSVParser getCsv() throws IOException {
        InputStream is=csvFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.newFormat(valueOf(';')).withHeader().parse(br);
    }

    public void fillData() throws IOException {
        CSVParser parser = getCsv();

        for (CSVRecord record : parser) {
            ExcelRow row = new ExcelRow();
            row.setNo(record.get("no"));
            row.setNo(record.get("type"));
            row.setNo(record.get("result"));
            row.setNo(record.get("correct"));
            excel.addRow(row);
        }
    }
}
