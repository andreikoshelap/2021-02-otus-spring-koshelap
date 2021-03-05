package ru.otus.spring;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.spring.service.DataTransformer;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        DataTransformer transformer=(DataTransformer) context.getBean("transformer");
        transformer.fillData();
        transformer.output();
        context.close();
    }
}
