package com.example.quartz;

import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wyl
 * @date 2021年12月23日  10:32
 */
@Component
public class SchedulerJobInitListener implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        taskService.initSchedule();
    }
}
