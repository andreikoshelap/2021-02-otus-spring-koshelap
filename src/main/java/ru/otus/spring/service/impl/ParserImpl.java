package ru.otus.spring.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.core.io.Resource;

import ru.otus.spring.service.Parser;


public class ParserImpl implements Parser {

    private final Resource csvFile;
    private static final char DELIMITER = ';';

    public ParserImpl(Resource csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public CSVParser getParsedCsv() throws IOException {
        InputStream is = csvFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.DEFAULT.withDelimiter(DELIMITER).withHeader().parse(br);
    }
}
