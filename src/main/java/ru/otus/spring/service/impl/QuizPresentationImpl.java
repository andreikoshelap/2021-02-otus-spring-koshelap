package ru.otus.spring.service.impl;

import java.io.IOException;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.Applicant;
import ru.otus.spring.service.QuizPresentation;
import ru.otus.spring.tools.LocaleProvider;
import ru.otus.spring.ui.Quiz;

public class QuizPresentationImpl implements QuizPresentation {
    private Quiz quiz;
    private LocaleProvider localeProvider;
    private DataMapper mapper;
//    private Applicant applicant;

    public QuizPresentationImpl(DataMapper mapper, LocaleProvider localeProvider
//            , Applicant applicant
    ) {
        this.mapper = mapper;
        this.localeProvider = localeProvider;
//        this.applicant = applicant;
    }

    @Override
    public void executeExam(Applicant applicant) throws IOException {
        quiz = getQuiz();
        //        defineLanguage();
        output(applicant);
    }

    private Quiz getQuiz() throws IOException {
        return mapper.dataMapping();
    }

//    private void defineLanguage() {
//        quiz.setLocale(localeProvider.defineWorkingLocale());
//    }

    private void output(Applicant applicant) {
        quiz.questionsOutput(applicant);
    }
}
