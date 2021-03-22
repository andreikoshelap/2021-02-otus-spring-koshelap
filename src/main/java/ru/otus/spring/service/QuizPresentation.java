package ru.otus.spring.service;

import java.io.IOException;

import ru.otus.spring.ui.Quiz;


public class QuizPresentation {
    private Quiz quiz;
    private DataTransformer transformer;

    public QuizPresentation( DataTransformer transformer) throws IOException {
        this.quiz = transformer.fillData();
    }

    public void output() {
        quiz.display();
    }

}
