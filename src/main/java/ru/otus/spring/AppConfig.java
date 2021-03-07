package ru.otus.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ru.otus.spring.dao.CsvDao;
import ru.otus.spring.domain.CsvRow;
import ru.otus.spring.service.DataTransformer;

@Configuration
public class AppConfig {

    @Bean
    DataTransformer transformer(@Value("${classpath:questionare.csv}") Resource csvFile, CsvDao csvDao) {
        return new DataTransformer(csvFile, csvDao);
    }

    @Bean
    CsvDao csv(List<CsvRow> csvRows) {
        return new CsvDao(csvRows);
    }

}
