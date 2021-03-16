package ru.otus.spring.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.otus.spring.domain.CsvRow;

public class QuizImplTest {

    private Quiz quiz;
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
        quiz = new QuizImpl(rows);
    }

    @Test
    public void shouldGiveOneCorrectAnswer() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        quiz.display();

        assertThat(quiz.getScores()).isNotEmpty().containsExactly(1D);

    }

    @Test
    public void shouldGiveOneWrongAnswer() {
        String input = "C";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        quiz.display();

        assertThat(quiz.getScores()).isNotEmpty().containsExactly(0D);

    }
}
