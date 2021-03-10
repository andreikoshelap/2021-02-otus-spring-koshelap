package ru.otus.spring.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class AppConfigTest {

    @Bean
    DataTransformer transformer(){
        return Mockito.mock(DataTransformer.class);
    }

    @Bean
    QuizPresentation presentation(){
        return Mockito.mock(QuizPresentation.class);
    }

    @Test
    public void testApplicationContext() {
        assertNotNull(transformer());
        assertNotNull(presentation());
    }
}