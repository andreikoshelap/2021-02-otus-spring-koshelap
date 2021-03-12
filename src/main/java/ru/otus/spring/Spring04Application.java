package ru.otus.spring;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.spring.service.DataTransformer;
import ru.otus.spring.service.QuizPresentation;

@SpringBootApplication
public class Spring04Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Spring04Application.class, args);
		DataTransformer transformer = context.getBean(DataTransformer.class);
		QuizPresentation presentation = context.getBean(QuizPresentation.class);
		transformer.fillData();
		presentation.output();
	}

}
