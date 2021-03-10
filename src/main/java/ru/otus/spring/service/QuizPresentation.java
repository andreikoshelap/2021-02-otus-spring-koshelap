package ru.otus.spring.service;

import ru.otus.spring.ui.Quiz;

public class QuizPresentation {
    private Quiz quiz;

    public QuizPresentation(Quiz quiz) {
        this.quiz = quiz;
    }

    public void output() {
        quiz.display();
    }
}
