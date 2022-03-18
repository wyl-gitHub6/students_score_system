package com.example.service.impl;

import com.example.constant.MyConstant;
import com.example.dao.TaskMapper;
import com.example.entity.Task;
import com.example.quartz.QuartzScheduler;
import com.example.service.TaskService;
import com.github.pagehelper.PageHelper;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wyl
 * @date 2021年12月23日  10:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private QuartzScheduler quartzScheduler;

    @Override
    public List<Task> findList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return taskMapper.findList();
    }

    @Override
    public int insert(Task task) {
        int i = taskMapper.insert(task);
        if (i > 0 && task.getJobStatus().equals(MyConstant.ZERO_STR)){
            quartzScheduler.addJob(task);
        }
        return i;
    }

    @Override
    public synchronized int update(Task task) throws SchedulerException {
        Task t = taskMapper.findById(task.getId());
        int i = taskMapper.update(task);
        if (i > 0){
            quartzScheduler.deleteJob(t);
            if (task.getJobStatus().equals(MyConstant.ZERO_STR)){
                quartzScheduler.addJob(task);
            }
        }
        return i;
    }

    @Override
    public int delete(int id) throws SchedulerException {
        Task task = taskMapper.findById(id);
        int i = taskMapper.delete(id);
        if (i > 0){
            quartzScheduler.deleteJob(task);
        }
        return i;
    }

    @Override
    public Task findById(int id) {
        return taskMapper.findById(id);
    }

    @Override
    public synchronized int updateStatus(int id, String jobStatus) throws SchedulerException {
        Task task = taskMapper.findById(id);
        task.setJobStatus(jobStatus);
        int i = taskMapper.update(task);
        if (i > 0){
            if (jobStatus.equals(MyConstant.ZERO_STR)){
                quartzScheduler.addJob(task);
            }else if (jobStatus.equals(MyConstant.ONE_STR)){
                quartzScheduler.deleteJob(task);
            }else{
                return 0;
            }
        }
        return i;
    }

    @Override
    public void initSchedule() {
        List<Task> jobList = taskMapper.list();
        jobList.forEach(task->{
            quartzScheduler.addJob(task);
        });
    }

}
