package ru.otus.spring.event.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class TimerAspect {

//    private final Locale locale;

//    public TimerAspect(Locale locale) {
//        this.locale = locale;
//    }

    @Around("@ru.otus.spring.annotation.Timestamp)")
    public Object logTimeOfTesting(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTestTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

//        System.out.println(localeService.getLocalizedString("testing.timer",
//                String.valueOf((double) ((System.currentTimeMillis() - startTestTime) / 1000)))
//        + localeService.getLocalizedString("testing.timer.sec"));

        return proceed;
    }

}
