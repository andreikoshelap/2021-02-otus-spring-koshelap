package ru.otus.spring.service;

import java.io.IOException;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.tools.LocaleProvider;
import ru.otus.spring.ui.Quiz;

public class QuizPresentation {
    private Quiz quiz;
    private LocaleProvider localeProvider;
    private DataMapper mapper;

    public QuizPresentation(DataMapper mapper, LocaleProvider localeProvider) {
        this.mapper = mapper;
        this.localeProvider = localeProvider;
    }

    public void executeExam()  throws IOException {
        quiz = getQuiz();
//        defineLanguage();
        output();
    }

    private Quiz getQuiz() throws IOException {
        return mapper.dataMapping();
    }

//    private void defineLanguage() {
//        quiz.setLocale(localeProvider.defineWorkingLocale());
//    }

    private void output() {
        quiz.questionsOutput();
    }
}
