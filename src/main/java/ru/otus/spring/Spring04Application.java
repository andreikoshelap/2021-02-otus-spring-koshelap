package ru.otus.spring;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring04Application {

	public static void main(String[] args) throws IOException {
		 SpringApplication.run(Spring04Application.class, args);
//        QuizPresentationImpl presentation = context.getBean(QuizPresentationImpl.class);
//        presentation.executeExam();
	}

}
