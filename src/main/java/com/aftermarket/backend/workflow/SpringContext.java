package com.aftermarket.backend.workflow;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** Gives classes instantiated outside Spring (Kogito-generated handlers) access to beans. */
@Component
public class SpringContext implements ApplicationContextAware {

    private static volatile ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        ApplicationContext ctx = context;
        if (ctx == null) {
            throw new IllegalStateException("Spring context not initialised yet");
        }
        return ctx.getBean(type);
    }
}
