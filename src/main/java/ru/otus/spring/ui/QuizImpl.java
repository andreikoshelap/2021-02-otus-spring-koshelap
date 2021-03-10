package ru.otus.spring.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import ru.otus.spring.domain.CsvRow;

public class QuizImpl implements Quiz {

    private static final String ANSWER_NO_ONE = "A.";
    private static final String ANSWER_NO_TWO = "B.";
    private static final String ANSWER_NO_THREE = "C.";
    private static final String ANSWER_NO_FOUR = "D.";
    private static final String ANSWER_NO_FIVE = "E.";
    private static final String ANSWER_NO_SIX = "F.";
    public static final String QUESTION_TYPE_WITH_ONE_ANSWER = "1";
    public static final String QUESTION_TYPE_WITH_MULTIPLE_ANSWERS = "2";

    private List<CsvRow> rows;
    private List<Double> scores = new ArrayList<>();

    public QuizImpl(List<CsvRow> rows) {
        this.rows = rows;
    }

    @Override
    public void addRow(CsvRow row) {
        this.rows.add(row);
    }

    @Override
    public void display() {

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
        System.out.println("Your result is " + getResult(scores) + " percent");
    }

    private int getResult(List<Double> scores) {
        return (int) Math.round(scores.stream().reduce(0D, Double::sum) * 100D / Double.valueOf(scores.size()));
    }

    private void acceptAndMatchAnswer(CsvRow p) {
        System.out.println(getDelimiter(p));
        Scanner in = new Scanner(System.in);
        String userAnswer = in.nextLine();
        System.out.println("You entered answer " + userAnswer.toUpperCase());
        System.out.println("Correct answer is " + p.getCorrectAnswer());
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

    private String getDelimiter(CsvRow p) {
        if (p.getType().equals(QUESTION_TYPE_WITH_ONE_ANSWER)) {
            return "---------------------------insert correct answer------------------------";
        }
        return "------------insert correct multiple answers with ';' delimiter--------";
    }
}
