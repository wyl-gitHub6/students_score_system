package com.example.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 开启选课
 *
 * @author Wangyl
 * @date 2022/12/23
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class QuartzJob implements Job {

    public static final String CLASS_NAME = "com.example.task.QuartzJob";

    /**
     * 0开启1关闭
     */
    public static AtomicInteger state = new AtomicInteger(1);

    @Override
    public synchronized void execute(JobExecutionContext context) {
        state.set(0);
        log.info(state+"学生选课开始：");
    }
}
