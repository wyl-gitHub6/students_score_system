package com.example.task;

import com.example.constant.MyConstant;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * 关闭选课
 *
 * @author Wangyl
 * @date 2022/12/23
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class CloseJob implements Job {

    public static final String CLASS_NAME = "com.example.task.CloseJob";

    private static int  state = MyConstant.ZERO;

    @Override
    public synchronized void execute(JobExecutionContext context) {
        state = MyConstant.ONE;
        log.info(state+"学生学课关闭：");
    }

    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }
}
