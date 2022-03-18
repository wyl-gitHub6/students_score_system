package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.Task;
import com.example.service.TaskService;
import com.example.task.CloseJob;
import com.example.task.QuartzJob;
import com.example.utils.CronUtils;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wyl
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuartzJob quartzJob;

    @Autowired
    private CloseJob closeJob;

    /**
     * 查询所有定时任务
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        List<Task> list = taskService.findList(currentPage,pageSize);
        PageInfo<Task> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 判断选课功能是否开启
     * @return
     */
    @GetMapping("/checkCourse")
    public Result isCheckCourse(){
        log.info("开启:"+quartzJob.getState());
        log.info("关闭:"+closeJob.getState());
        if (quartzJob.getState() == MyConstant.ONE){
            return Result.success(MyConstant.ONE,"选课关闭中");
        }
        if (closeJob.getState() == MyConstant.ZERO){
            return Result.success(MyConstant.ZERO,"快开始选课吧");
        }
        return Result.success(MyConstant.ONE,"选课关闭中");
    }

    /**
     * 添加定时任务
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Task task){
        if (!CronUtils.isValid(task.getCronExpression())){
            return Result.error("cron表达式错误!");
        }
        int i = taskService.insert(task);
        if (i > 0){
            return Result.success("添加成功!");
        }
        return Result.error("添加失败!");
    }

    /**
     * 修改定时任务
     * @param task
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Task task) throws SchedulerException {
        if (!CronUtils.isValid(task.getCronExpression())){
            return Result.error("cron表达式错误!");
        }
        int i = taskService.update(task);
        if (i > 0){
            return Result.success("修改成功!");
        }
        return Result.error("修改失败!");
    }

    /**
     * 删除定时任务
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") int id) throws SchedulerException {
        int i = taskService.delete(id);
        if (i > 0){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 根据Id查询定时任务
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("id") int id){
        return Result.success(taskService.findById(id),"查询成功");
    }

    /**
     * 定时任务开关修改
     * @param id
     * @param jobStatus
     * @return
     * @throws SchedulerException
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(@RequestParam("id") int id,
                               @RequestParam("jobStatus") String jobStatus) throws SchedulerException {
        int i = taskService.updateStatus(id, jobStatus);
        if (i > 0){
            return Result.success("修改成功!");
        }
        return Result.error("修改失败!");
    }
}
