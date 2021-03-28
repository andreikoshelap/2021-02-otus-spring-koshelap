package ru.otus.spring.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.service.Parser;

@Service
@RequiredArgsConstructor
public class ParserImpl implements Parser {


    @Value("${classpath:questionare.csv}")
    private final Resource csvFile;
    private static final char DELIMITER = ';';

    @Override
    public CSVParser getParsedCsv() throws IOException {
        InputStream is = csvFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.DEFAULT.withDelimiter(DELIMITER).withHeader().parse(br);
    }
}
