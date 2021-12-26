package com.example.dao;

import com.example.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wyl
 * @date 2021年12月23日  10:35
 */
@Mapper
public interface TaskMapper {

    /**
     * 查询开启的定时任务
     * @return
     */
    List<Task> list();

    /**
     * 查询所有定时任务
     * @return
     */
    List<Task> findList();

    /**
     * 新增定时任务
     * @param task
     * @return
     */
    int insert(Task task);

    /**
     * 删除定时任务
     * @param id
     */
    int delete(int id);

    /**
     * 修改定时任务
     * @param task
     */
    int update(Task task);

    Task findById(int id);
}
