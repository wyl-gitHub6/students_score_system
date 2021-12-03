package com.example.dao;

import com.example.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Teacher)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-06 18:43:28
 */
 @Mapper
public interface TeacherDao {

    /**
     * 通过ID查询单条数据
     *
     * @param teacherId 主键
     * @return 实例对象
     */
    Teacher findById(Integer teacherId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Teacher> findAll();

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int insert(Teacher teacher);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param teacherId 主键
     * @return 影响行数
     */
    int deleteById(Integer teacherId);

    /**
     * 分页查询
     * @param teacherNum
     * @param teacherName
     * @return
     */
    List<Teacher> findList(@Param("teacherNum") String teacherNum,
                           @Param("teacherName") String teacherName);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 登录
     * @param teacherNum
     * @param password
     * @return
     */
    Teacher findByTeacherNumAndPassword(@Param("teacherNum") String teacherNum,
                                        @Param("password") String password);

    /**
     * 查询教师数量
     * @return
     */
    int findCount();

    /**
     * 根据职工编号查询
     * @param teacherNum
     * @return
     */
    Teacher findByTeacherNum(String teacherNum);

}

