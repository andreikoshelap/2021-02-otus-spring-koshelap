package ru.otus.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ru.otus.spring.domain.CsvRow;
import ru.otus.spring.service.DataTransformer;
import ru.otus.spring.service.QuizPresentation;
import ru.otus.spring.ui.Greeting;
import ru.otus.spring.ui.GreetingsImpl;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.QuizImpl;

@Configuration
public class AppConfig {

    @Bean
    DataTransformer transformer(@Value("${classpath:questionare.csv}") Resource csvFile, Quiz quiz) {
        return new DataTransformer(csvFile, quiz);
    }

    @Bean
    QuizPresentation presentation( Quiz quiz, Greeting greeting) {
        return new QuizPresentation(quiz, greeting);
    }

    @Bean
    Quiz quiz(List<CsvRow> csvRows, MessageSource msg) {
        return new QuizImpl(csvRows, msg);
    }

    @Bean
    Greeting greeting( MessageSource msg) {
        return new GreetingsImpl( msg);
    }

}
