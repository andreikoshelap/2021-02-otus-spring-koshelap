package ru.otus.spring.service;

import ru.otus.spring.ui.Greeting;
import ru.otus.spring.ui.Quiz;

public class QuizPresentation {
    private Quiz quiz;
    private Greeting greeting;

    public QuizPresentation(Quiz quiz, Greeting greeting) {
        this.quiz = quiz;
        this.greeting = greeting;
    }

    public void output() {
        quiz.questionsOutput();
    }

    public void defineLanguage() {
        quiz.setLocale(greeting.defineWorkingLocale());
    }
}
