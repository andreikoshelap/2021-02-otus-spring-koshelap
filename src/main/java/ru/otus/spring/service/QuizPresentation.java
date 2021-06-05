package ru.otus.spring.service;

import java.io.IOException;

import ru.otus.spring.domain.Applicant;

public interface QuizPresentation {

    void executeExam(Applicant applicant)  throws IOException;
}
