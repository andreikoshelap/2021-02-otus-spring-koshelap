package ru.otus.spring.ui;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ru.otus.spring.service.CsvDataTransformer;
import ru.otus.spring.service.DataTransformer;

@Configuration
public class TestContextConfig {

    @Bean
    DataTransformer transformer(@Value("${classpath:test.csv}") Resource csvFile, Quiz quiz) {
        return new CsvDataTransformer(csvFile, quiz);
    }

    @Bean
    MessageSource msg(){
        return Mockito.mock(MessageSource.class);
    }

}
