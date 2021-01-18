package com.ljw.spring.source.s1.service;

import com.ljw.spring.source.s1.beans.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private Teacher teacher;

    public void doTeacherService() throws Exception {
        teacher.teaching();
    }
}
