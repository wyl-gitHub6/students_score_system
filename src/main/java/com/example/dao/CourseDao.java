package com.example.dao;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Course)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-11 22:26:53
 */
 @Mapper
public interface CourseDao {

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
     * @return 影响行数
     */
    int insert(Course course);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 影响行数
     */
    int update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 影响行数
     */
    int deleteById(Integer courseId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页
     * @param courseNum
     * @param courseName
     * @return
     */
    List<Course> findList(@Param("courseNum") String courseNum,
                          @Param("courseName") String courseName);
}

