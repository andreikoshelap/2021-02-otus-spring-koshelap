package ru.otus.spring.service;

import org.springframework.context.MessageSource;

import ru.otus.spring.ui.Quiz;

public class QuizPresentation {
    private Quiz quiz;

    public QuizPresentation(Quiz quiz) {
        this.quiz = quiz;
    }

    public void output(MessageSource msg) {
        quiz.display(msg);
    }
}
