package com.ljw.spring.source.s1.listener;

import com.ljw.spring.source.s1.listener.event.LjwEvent1;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LjwApplicationListener1 implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof LjwEvent1) {
            LjwEvent1 e1 = (LjwEvent1) event;
            System.out.println("LjwApplicationListener1 = " + e1.getName());
        }
    }
}
