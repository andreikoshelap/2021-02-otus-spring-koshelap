package ru.otus.spring;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ru.otus.spring.service.DataTransformer;
import ru.otus.spring.service.QuestionRepresentation;

@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(Main.class);
        DataTransformer transformer = context.getBean(DataTransformer.class);
        QuestionRepresentation presentation = context.getBean(QuestionRepresentation.class);
        transformer.fillData();
        presentation.output();
        context.close();
    }
}
