package ru.otus.spring;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.dao.impl.CsvDataMapperImpl;
import ru.otus.spring.service.Parser;
import ru.otus.spring.service.impl.ParserImpl;
import ru.otus.spring.tools.InputOutputService;

@Configuration
public class TestContextConfig {

    @Bean
    public Parser parser(@Value("${classpath:questionare.csv}") Resource csvFile) {
        return new ParserImpl(csvFile);
    }

    @Bean
    public DataMapper mapper(Parser parser, MessageSource msg, InputOutputService ioService) {
        return new CsvDataMapperImpl(parser, msg, ioService);
    }

    @Bean
    public MessageSource msg(){
        return Mockito.mock(MessageSource.class);
    }
    @Bean
    public InputOutputService ioService(){
        return Mockito.mock(InputOutputService.class);
    }

}
