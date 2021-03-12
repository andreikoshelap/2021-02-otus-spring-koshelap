package ru.otus.spring.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;

import ru.otus.spring.domain.CsvRow;

public class QuizImpl implements Quiz {

    private static final String ANSWER_NO_ONE = "A.";
    private static final String ANSWER_NO_TWO = "B.";
    private static final String ANSWER_NO_THREE = "C.";
    private static final String ANSWER_NO_FOUR = "D.";
    private static final String ANSWER_NO_FIVE = "E.";
    private static final String ANSWER_NO_SIX = "F.";
    private static final String QUESTION_TYPE_WITH_ONE_ANSWER = "1";
    private static final String QUESTION_TYPE_WITH_MULTIPLE_ANSWERS = "2";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static final Set<Locale> AVAILABLE_LOCALES = Set.of(
            Locale.ENGLISH,
            new Locale("ru", "RU"),
            new Locale("et", "EE"));
    private static final String QUIZ_RESULT = "quiz.result";
    private static final String TITLE_LANGUAGE = "title.language";
    private static final String ET = "et";
    private static final String EE = "EE";
    private static final String SMALL_RU = "ru";
    private static final String RU = "RU";
    private static final String PICKED_LANGUAGE = "picked.language";
    private static final String ENTERED_ANSWER = "entered.answer";
    private static final String CORRECT_ANSWER = "correct.answer";
    private static final String INSERT_ONE_ANSWER = "insert.one.answer";
    private static final String INSERT_MULTIPLE_ANSWER = "insert.multiple.answer";

    private List<CsvRow> rows;
    private MessageSource msg;
    private List<Double> scores = new ArrayList<>();
    private Locale locale = Locale.ENGLISH;

    public QuizImpl(List<CsvRow> rows, MessageSource msg) {
        this.rows = rows;
        this.msg = msg;
    }

    @Override
    public void addRow(CsvRow row) {
        this.rows.add(row);
    }

    @Override
    public void display() {
        defineLanguage();
        rows.stream().forEach(p ->{
            System.out.println(p.getNo());
            System.out.println(p.getQuestion());
            System.out.println();
            System.out.println(ANSWER_NO_ONE + p.getAnswerA());
            System.out.println(ANSWER_NO_TWO + p.getAnswerB());
            System.out.println(ANSWER_NO_THREE + p.getAnswerC());
            System.out.println(ANSWER_NO_FOUR + p.getAnswerD());
            System.out.println(ANSWER_NO_FIVE + p.getAnswerE());
            if(p.getAnswerF() != null && !p.getAnswerF().isEmpty()) {
                System.out.println(ANSWER_NO_SIX + p.getAnswerF());
            }
            acceptAndMatchAnswer(p);
        });
        output(QUIZ_RESULT, new String[] {ANSI_RED,  String.valueOf(getResult(scores)), ANSI_RESET } );
    }

    private void defineLanguage() {
        AVAILABLE_LOCALES.stream().forEach(loc-> {
            System.out.println(msg.getMessage(TITLE_LANGUAGE, new String[] {ANSI_BLUE, ANSI_RESET}, loc));
        });

        Scanner in = new Scanner(System.in);
        switch (in.nextLine().toUpperCase()) {
            case "ET":
                locale  = new Locale(ET, EE);
                break;
            case "RU":
                locale = new Locale(SMALL_RU, RU);
                break;
            default:
                locale  = Locale.ENGLISH;
        }

        output(PICKED_LANGUAGE, new String[] { ANSI_BLUE, ANSI_RESET});
    }

    private int getResult(List<Double> scores) {
        return (int) Math.round(scores.stream().reduce(0D, Double::sum) * 100D / Double.valueOf(scores.size()));
    }

    private void acceptAndMatchAnswer(CsvRow p) {
        getDelimiterLine(p);
        Scanner in = new Scanner(System.in);
        String userAnswer = in.nextLine();
        output(ENTERED_ANSWER,  new String[] {ANSI_BLUE, userAnswer.toUpperCase(), ANSI_RESET});
        output(CORRECT_ANSWER, new String[] {ANSI_BLUE, p.getCorrectAnswer(), ANSI_RESET});
        scoringAnswer(userAnswer, p.getCorrectAnswer(), p.getType());
    }


    private void scoringAnswer(String userAnswer, String correctAnswer, String questionType) {
        if(questionType.equals(QUESTION_TYPE_WITH_ONE_ANSWER) && userAnswer.compareToIgnoreCase(correctAnswer) == 0){
            scores.add(1D);
            return;
        }
        if(questionType.equals(QUESTION_TYPE_WITH_MULTIPLE_ANSWERS)){
            scores.add(scoringMultipleAnswers(userAnswer, correctAnswer));
            return;
        }
        scores.add(0D);
    }

    private Double scoringMultipleAnswers(String userAnswer, String correctAnswer) {
        String[] userAnswers = userAnswer.split(";");
        List<String> userAnswersUppercase  = Arrays.asList(userAnswers).stream()
                .map(ua ->ua.toUpperCase()).collect(Collectors.toList());
        String[] correctAnswers = correctAnswer.split(";");
        if(Arrays.asList(correctAnswers).equals(userAnswersUppercase)) {
            return 1D;
        }
        return 0D;
    }

    private void getDelimiterLine(CsvRow p) {
        if (p.getType().equals(QUESTION_TYPE_WITH_ONE_ANSWER)) {
            output(INSERT_ONE_ANSWER, new String[] {ANSI_RED, ANSI_RESET});
            return;
        }
        output(INSERT_MULTIPLE_ANSWER, new String[] {ANSI_RED, ANSI_RESET});
    }

    private void output( String propertyString, String[] msgParameters) {
        System.out.println(msg.getMessage(propertyString, msgParameters, locale));
    }
}
