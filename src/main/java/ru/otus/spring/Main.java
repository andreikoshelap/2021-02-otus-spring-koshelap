package ru.otus.spring;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        Assembler assembler=(Assembler) context.getBean("assembler");
        assembler.fillData();
        context.close();
    }
}
