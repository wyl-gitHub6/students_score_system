package com.example.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

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

    public static AtomicInteger state = new AtomicInteger(0);

    @Override
    public void execute(JobExecutionContext context) {
        state.addAndGet(1);
        log.info(state+"学生学课关闭：");
    }
}
