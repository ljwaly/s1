package com.ljw.spring.source.s1.beans.scanbean.jconditional;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.AH;
import org.springframework.stereotype.Component;

@Component
@ConditionOnProperty(value = {"ljw.asd"})
public class DemoConditionProperty {

    private String flag = "demoConditionPropertyTest";

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
