package ru.otus.spring.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.domain.CsvRow;

@Setter
@Getter
public class QuizImpl implements Quiz {

    public static final String ANSWER_NO_ONE = "A.";
    public static final String ANSWER_NO_TWO = "B.";
    public static final String ANSWER_NO_THREE = "C.";
    public static final String ANSWER_NO_FOUR = "D.";
    public static final String ANSWER_NO_FIVE = "E.";
    public static final String ANSWER_NO_SIX = "F.";

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
    }

    private void acceptAndMatchAnswer(CsvRow p) {
        System.out.println(getDelimiter(p));
        Scanner in = new Scanner(System.in);
        String userAnswer = in.nextLine();
        System.out.println("You entered string " + userAnswer);
        System.out.println("Correct answer is " + p.getCorrectAnswer());
        scoringAnswer(userAnswer, p.getCorrectAnswer(), p.getType());
    }

    private void scoringAnswer(String userAnswer, String correctAnswer, String questionType) {
        if(questionType.equals("1") && userAnswer.compareToIgnoreCase(correctAnswer) == 0){
            scores.add(1D);
            return;
        }
        if(questionType.equals("2")){
            String[] userAnswers = userAnswer.split(";");
            String[] correctAnswers = correctAnswer.split(";");
            if(Arrays.asList(correctAnswers).containsAll(Arrays.asList(userAnswers))) {
                scores.add(1D);
                return;
            }
        }
        scores.add(0d);
    }

    private String getDelimiter(CsvRow p) {
        if (p.getType().equals("1")) {
            return "---------------------------insert correct answer------------------------";
        }
        return "--insert correct multiple answers with ';' delimiter-------------";
    }
}
