package ru.otus.spring.tools;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.tools.impl.LocaleProviderImpl;
import ru.otus.spring.TestContextConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestContextConfig.class})
public class LocaleProviderImplTest {

    private LocaleProvider localeProvider;
    @Mock
    private MessageSource msg;


    @BeforeEach
    void setUp() {
        localeProvider = new LocaleProviderImpl(msg);
    }

    @Test
    public void chooseEstonianLocale() {
        String input = "et";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Locale locale = localeProvider.defineWorkingLocale();

        assertThat(locale).isEqualTo(new Locale("et", "EE"));
    }
}
