package com.example.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @author wyl
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class CloseJob implements Job {

    public static final String CLASS_NAME = "com.example.task.CloseJob";

    private static int state = 0;

    @Override
    public void execute(JobExecutionContext context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                state = 1;
            }
        }).start();
        log.info(state+"学生学课关闭：");
    }

    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }
}
