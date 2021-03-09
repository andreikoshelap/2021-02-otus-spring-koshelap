package ru.otus.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ru.otus.spring.domain.CsvRow;
import ru.otus.spring.service.DataTransformer;
import ru.otus.spring.service.QuestionRepresentation;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.QuizImpl;

@Configuration
public class AppConfig {

    @Bean
    DataTransformer transformer(@Value("${classpath:questionare.csv}") Resource csvFile, Quiz quiz) {
        return new DataTransformer(csvFile, quiz);
    }

    @Bean
    QuestionRepresentation presentation( Quiz quiz) {
        return new QuestionRepresentation(quiz);
    }

    @Bean
    QuizImpl csv(List<CsvRow> csvRows) {
        return new QuizImpl(csvRows);
    }

}
