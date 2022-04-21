package com.example.quartz;

import com.example.constant.MyConstant;
import com.example.entity.Task;
import com.example.task.CloseJob;
import com.example.task.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wyl
 * @date 2021年12月23日  11:25
 */
@Component
public class QuartzScheduler {

    @Autowired
    private Scheduler scheduler;

    /**
     * 开启定时任务
     *
     * @param task 任务
     */
    @SuppressWarnings("unchecked")
    public void addJob(Task task) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(task.getJobName(), task.getJobGroup())
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getJobGroup())
                    .startAt(DateBuilder.futureDate(MyConstant.ONE, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭任务
     *
     * @param task 任务
     * @throws SchedulerException 调度程序异常
     */
    public void deleteJob(Task task) throws SchedulerException {
        if (task.getBeanClass().equals(QuartzJob.CLASS_NAME)){
            QuartzJob.state.set(MyConstant.ONE);
        }
        if (task.getBeanClass().equals(CloseJob.CLASS_NAME)){
            CloseJob.state.set(MyConstant.ZERO);
        }
        scheduler.deleteJob(JobKey.jobKey(task.getJobName(),task.getJobGroup()));
    }
}
