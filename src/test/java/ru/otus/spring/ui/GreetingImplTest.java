package ru.otus.spring.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.AppConfig;
import ru.otus.spring.ui.impl.GreetingImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class, TestContextConfig.class})
public class GreetingImplTest {

    private Greeting greeting;
    @Autowired
    private MessageSource msg;


    @BeforeEach
    void setUp() {
        greeting = new GreetingImpl(msg);
    }

    @Test
    public void chooseEstonianLocale() {
        String input = "et";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Locale locale = greeting.defineWorkingLocale();

        assertThat(locale).isEqualTo(new Locale("et", "EE"));
    }
}
