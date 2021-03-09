package ru.otus.spring.service;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.ui.Quiz;

@Setter
@Getter
public class QuestionRepresentation {
//    private DataTransformer dataTransformer;
    private Quiz quiz;

    public QuestionRepresentation(Quiz quiz) {
        this.quiz = quiz;
    }

    public void output() {
        quiz.display();
    }
}
