package com.ljw.spring.source.s1.listener;

import com.ljw.spring.source.s1.listener.event.LjwEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class LjwApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof LjwEvent) {
            LjwEvent e1 = (LjwEvent) event;
            System.out.println("event = " + e1.getName());
        }
    }
}
