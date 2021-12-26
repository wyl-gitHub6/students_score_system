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
 */
@Component
@DisallowConcurrentExecution
public class CloseJob implements Job {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-hh:mm:ss");

    public static final String CLASS_NAME = "com.example.task.CloseJob";

    private static int state = 0;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        state = 1;
        System.out.println(state+"学生学课关闭："+ dateTimeFormatter.format(LocalDateTime.now()));
    }

    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }
}
