package ru.otus.spring.ui;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.domain.CsvRow;

@ExtendWith(SpringExtension.class)
public class QuizImplTest {

    @Autowired
    private Quiz quiz;

    @Autowired
    private List<CsvRow> rows;

    private Locale locale = Locale.ENGLISH;

    @Test
    void shouldExecuteServiceMethodsForRussianGreeting() {


        quiz.questionsOutput();

//        verify(quiz).out("");
//        verify(greetingDao, times(1)).findGreetingByCountryCode(COUNTRY_CODE_RU);
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("ru", "Тестовый привет"),
                Arguments.of("RU", "Тестовый привет"),
                Arguments.of("en", "Тестовый hello"),
                Arguments.of("EN", "Тестовый hello"),
                Arguments.of("cn", "Тестовый nihao"),
                Arguments.of("CN", "Тестовый nihao")
        );
    }
}