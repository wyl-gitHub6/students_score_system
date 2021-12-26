package com.example.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wyl
 * @date 2021年12月23日  11:30
 */
@Component
@DisallowConcurrentExecution
public class QuartzJob implements Job {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-hh:mm:ss");

    public static final String CLASS_NAME = "com.example.task.QuartzJob";

    /**
     * 0开启1关闭
     */
    private static int state = 1;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        state = 0;
        System.out.println(state+"学生选课开始："+ dateTimeFormatter.format(LocalDateTime.now()));
    }

    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }
}
