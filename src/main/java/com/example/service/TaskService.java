package com.example.service;

import com.example.entity.Task;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author wyl
 * @date 2021年12月23日  10:34
 */
public interface TaskService {

    /**
     * 开机时查看开启的定时任务
     */
    void initSchedule();

    /**
     * 查询所有定时任务
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @return {@link List}<{@link Task}>
     */
    List<Task> findList(int currentPage, int pageSize);

    /**
     * 添加定时任务
     *
     * @param task 任务
     * @return int
     */
    int insert(Task task);

    /**
     * 修改定时任务
     *
     * @param task 任务
     * @return int
     * @throws SchedulerException 调度程序异常
     */
    int update(Task task) throws SchedulerException;

    /**
     * 删除定时任务
     *
     * @param id id
     * @return int
     * @throws SchedulerException 调度程序异常
     */
    int delete(int id) throws SchedulerException;

    /**
     * 根据Id查询定时任务
     *
     * @param id id
     * @return {@link Task}
     */
    Task findById(int id);

    /**
     * 定时任务开关修改
     *
     * @param id        id
     * @param jobStatus 作业状态
     * @return int
     * @throws SchedulerException 调度程序异常
     */
    int updateStatus(int id, String jobStatus) throws SchedulerException;
}
