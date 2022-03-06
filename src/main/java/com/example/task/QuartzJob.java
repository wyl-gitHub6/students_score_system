package com.example.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @author wyl
 * @date 2021年12月23日  11:30
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class QuartzJob implements Job {

    public static final String CLASS_NAME = "com.example.task.QuartzJob";

    /**
     * 0开启1关闭
     */
    private static int state = 1;

    @Override
    public void execute(JobExecutionContext context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                state = 0;
            }
        }).start();
        log.info(state+"学生选课开始：");
    }

    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }
}
