package ru.otus.spring.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.domain.CsvRow;
import ru.otus.spring.tools.InputOutputService;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.impl.QuizImpl;

@ExtendWith(SpringExtension.class)
public class QuizImplTest {

    private Quiz quiz;
    @Mock
    private MessageSource msg;
    @Mock
    private InputOutputService ioService;

    private List<CsvRow> rows = new ArrayList<>();

    @BeforeEach
    void setUp() {
        CsvRow csvRow = new CsvRow();
        csvRow.setNo("1");
        csvRow.setType("1");
        csvRow.setQuestion("question subj one");
        csvRow.setAnswerA("answer one");
        csvRow.setAnswerB("answer two");
        csvRow.setAnswerC("answer three");
        csvRow.setAnswerD("answer four");
        csvRow.setAnswerE("answer five");
        csvRow.setCorrectAnswer("A");
        rows.add(csvRow);
        quiz = new QuizImpl(rows, msg, ioService);
    }

    @Test
    public void shouldGiveOneCorrectAnswer() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

//        quiz.questionsOutput();
//
//        assertThat(quiz.getScores()).isNotEmpty().containsExactly(1D);

    }

    @Test
    public void shouldGiveOneWrongAnswer() {
        String input = "C";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

//        quiz.questionsOutput();
//
//        assertThat(quiz.getScores()).isNotEmpty().containsExactly(0D);

    }
}
