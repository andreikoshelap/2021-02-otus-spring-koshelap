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

    public static final Set<Locale> AVAILABLE_LOCALES = Set.of(
            Locale.ENGLISH,
            new Locale("ru", "RU"),
            new Locale("et", "EE"));

    private List<CsvRow> rows;
//    private MessageSource msg;
    private List<Double> scores = new ArrayList<>();
    private Locale locale = Locale.ENGLISH;

//    @Autowired
    public QuizImpl(List<CsvRow> rows) {
        this.rows = rows;
//        this.msg = msg;
    }

    @Override
    public void addRow(CsvRow row) {
        this.rows.add(row);
    }

    @Override
    public void display(MessageSource msg) {
        defineLanguage(msg);
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
            acceptAndMatchAnswer(p, msg);
        });
        output(msg, "quiz.result", new String[] {ANSI_RED,  String.valueOf(getResult(scores)), ANSI_RESET } );
    }

    private void defineLanguage(MessageSource msg) {
        AVAILABLE_LOCALES.stream().forEach(loc-> {
            System.out.println(msg.getMessage("title.language", null, loc));
        });

        Scanner in = new Scanner(System.in);
        switch (in.nextLine().toUpperCase()) {
            case "ET":
                locale  = new Locale("et", "EE");
                break;
            case "RU":
                locale = new Locale("ru", "RU");
                break;
            default:
                locale  = Locale.ENGLISH;        }

        output(msg, "picked.language", new String[] {locale.getLanguage().toUpperCase()});
    }

    private int getResult(List<Double> scores) {
        return (int) Math.round(scores.stream().reduce(0D, Double::sum) * 100D / Double.valueOf(scores.size()));
    }

    private void acceptAndMatchAnswer(CsvRow p, MessageSource msg) {
        getDelimiterLine(p, msg);
        Scanner in = new Scanner(System.in);
        String userAnswer = in.nextLine();
        output(msg,"entered.answer",  new String[] {userAnswer.toUpperCase()});
        output(msg, "correct.answer", new String[] {p.getCorrectAnswer()});
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

    private void getDelimiterLine(CsvRow p, MessageSource msg ) {
        if (p.getType().equals(QUESTION_TYPE_WITH_ONE_ANSWER)) {
            output(msg, "insert.one.answer", null);
            return;
        }
        output(msg, "insert.multiple.answer", null);
    }

    private void output(MessageSource msg, String propertyString, String[] msgParameters) {
        System.out.println(msg.getMessage(propertyString, msgParameters, locale));
    }
}
