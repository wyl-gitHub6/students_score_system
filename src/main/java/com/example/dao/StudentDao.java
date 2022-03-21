package com.example.dao;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Student)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-10 11:26:21
 */
@Mapper
public interface StudentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param studentId 主键
     * @return 实例对象
     */
    Student findById(Integer studentId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Student> findAll();

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 影响行数
     */
    int deleteById(Integer studentId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 模糊查询
     * @param studentNum
     * @param studentName
     * @return
     */
    List<Student> findList(String studentNum, String studentName);

    /**
     * 根据学号查询
     * @param studentNum
     * @return
     */
    Student findByStudentNum(String studentNum);

    /**
     * 查询班级人数
     * @param classesId
     * @return
     */
    int findCount(Integer classesId);

    /**
     * 根据班级ID查询
     * @param classesId
     * @param studentNum
     * @param studentName
     * @return
     */
    List<Student> findByClassesId(@Param("classesId") Integer classesId,
                                  @Param("studentNum") String studentNum,
                                  @Param("studentName") String studentName);

    /**
     * 登录
     * @param studentNum
     * @param password
     * @return
     */
    Student findByStudentNumAndPassword(@Param("studentNum") String studentNum,
                                        @Param("password") String password);

    /**
     * 必修课录入成绩时查询该课程没成绩的学生
     * @param classesId
     * @param courseId
     * @return
     */
    List<Student> findByClasses(@Param("classesId") int classesId,
                                @Param("courseId") int courseId);

    /**
     * 查询学生数量
     * @return
     */
    int getCount();

    /**
     * 获取班级最大学号
     * @param classesId
     * @return
     */
    Long findMaxCode(int classesId);
}

