package ru.otus.spring;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.spring.dao.Assembler;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        Assembler assembler=(Assembler) context.getBean("assembler");
        assembler.fillData();
        assembler.output();
        context.close();
    }
}
