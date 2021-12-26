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
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Task> findList(int currentPage, int pageSize);

    /**
     * 添加定时任务
     * @param task
     * @return
     */
    int insert(Task task);

    /**
     * 修改定时任务
     * @param task
     * @return
     * @throws SchedulerException
     */
    int update(Task task) throws SchedulerException;

    /**
     * 删除定时任务
     * @param id
     * @return
     * @throws SchedulerException
     */
    int delete(int id) throws SchedulerException;

    /**
     * 根据Id查询定时任务
     * @param id
     * @return
     */
    Task findById(int id);

    /**
     * 定时任务开关修改
     * @param id
     * @param jobStatus
     * @return
     * @throws SchedulerException
     */
    int updateStatus(int id, String jobStatus) throws SchedulerException;
}
