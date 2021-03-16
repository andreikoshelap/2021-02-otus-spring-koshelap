package ru.otus.spring.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.AppConfig;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.TestContextConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class, TestContextConfig.class})
public class CsvDataTransformerTest {

    @Autowired
    private DataTransformer transformer;
    @Autowired
    private Quiz quiz;


    @Test
    public void getCsvData() throws IOException {

        transformer.exportData();

        assertThat(quiz.getRows()).isNotEmpty().hasSize(2);
    }
}
