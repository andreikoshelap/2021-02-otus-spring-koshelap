package ru.otus.spring;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ru.otus.spring.service.DataTransformer;
import ru.otus.spring.service.QuizPresentation;

@Configuration
public class AppConfig {

    @Bean
    DataTransformer transformer(@Value("${classpath:questionare.csv}") Resource csvFile) {
        return new DataTransformer(csvFile);
    }

    @Bean
    QuizPresentation presentation( DataTransformer transformer) throws IOException {
        return new QuizPresentation(transformer);
    }

}
