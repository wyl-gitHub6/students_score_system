package com.example.service;

import com.example.entity.Course;
import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author wyl
 * @since 2021-10-11 22:26:53
 */
public interface CourseService {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    Course findById(Integer courseId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Course> findAll();

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    int insert(Course course);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    int update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer courseId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param courseNum
     * @param courseName
     * @return
     */
    List<Course> findList(int currentPage, int pageSize, String courseNum, String courseName);
}
