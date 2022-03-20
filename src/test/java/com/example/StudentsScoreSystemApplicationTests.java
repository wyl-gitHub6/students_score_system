package com.example;

import com.example.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentsScoreSystemApplicationTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CollegeService collegeService;

    @Test
    void contextLoads() {
    }

}
