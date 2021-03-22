package ru.otus.spring;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.spring.service.QuizPresentation;

@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(Main.class);
        QuizPresentation presentation = context.getBean(QuizPresentation.class);
        presentation.output();
        context.close();
    }
}
