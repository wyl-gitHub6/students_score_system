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
     * 删除批处理
     *
     * @param ids id
     * @return boolean
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseNum   当然num
     * @param courseName  课程名称
     * @param courseState 课程状态
     * @return {@link List}<{@link Course}>
     */
    List<Course> findList(int currentPage, int pageSize, String courseNum, String courseName, int courseState);

    /**
     * 根据课程类被查询
     *
     * @param courseState 课程状态
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByCourseState(int courseState);

    /**
     * 查询教师所教授课程
     *
     * @param teacherId 老师id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByTeacherId(int teacherId);
}
