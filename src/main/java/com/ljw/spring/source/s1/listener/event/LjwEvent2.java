package com.ljw.spring.source.s1.listener.event;

import org.springframework.context.ApplicationEvent;

public class LjwEvent2 extends ApplicationEvent {

    private String name;

    public LjwEvent2(String name, String source) {
        super(source);
        this.name = name;
    }

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public LjwEvent2(Object source) {
        super(source);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
