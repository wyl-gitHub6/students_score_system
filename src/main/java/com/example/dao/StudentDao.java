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
     * 删除批处理
     *
     * @param ids id
     * @return boolean
     */
    boolean deleteBatch(int[] ids);

    /**
     * 查询列表
     *
     * @param studentNum  学生num
     * @param studentName 学生名字
     * @return {@link List}<{@link Student}>
     */
    List<Student> findList(String studentNum, String studentName);

    /**
     * 根据学号查询
     *
     * @param studentNum 学生num
     * @return {@link Student}
     */
    Student findByStudentNum(String studentNum);

    /**
     * 查询班级人数
     *
     * @param classesId 类id
     * @return int
     */
    int findCount(Integer classesId);

    /**
     * 查询通过类列表
     *
     * @param classesId   类id
     * @param studentNum  学生num
     * @param studentName 学生名字
     * @return {@link List}<{@link Student}>
     */
    List<Student> findByClassesList(@Param("classesId") Integer classesId,
                                  @Param("studentNum") String studentNum,
                                  @Param("studentName") String studentName);

    /**
     * 登录
     *
     * @param studentNum 学生num
     * @param password   密码
     * @return {@link Student}
     */
    Student findByStudentNumAndPassword(@Param("studentNum") String studentNum,
                                        @Param("password") String password);

    /**
     * 必修课录入成绩时查询该课程没成绩的学生
     *
     * @param classesId 类id
     * @param courseId  进程id
     * @return {@link List}<{@link Student}>
     */
    List<Student> findByClasses(@Param("classesId") int classesId,
                                @Param("courseId") int courseId);

    /**
     * 查询学生数量
     *
     * @return int
     */
    int getCount();

    /**
     * 获取班级最大学号
     *
     * @param classesId 类id
     * @return {@link Long}
     */
    Long findMaxCode(int classesId);

    /**
     * 查询通过类id
     *
     * @param classesId 类id
     * @return {@link List}<{@link Student}>
     */
    List<Student> findByClassesId(int classesId);
}

