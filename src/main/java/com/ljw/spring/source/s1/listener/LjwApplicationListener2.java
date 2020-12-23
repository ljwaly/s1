package com.ljw.spring.source.s1.listener;

import com.ljw.spring.source.s1.listener.event.LjwEvent2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LjwApplicationListener2 implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof LjwEvent2) {
            LjwEvent2 e1 = (LjwEvent2) event;
            System.out.println("LjwApplicationListener2 = " + e1.getName());
        }
    }
}
