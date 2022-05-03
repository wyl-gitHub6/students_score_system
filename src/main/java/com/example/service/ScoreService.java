package com.example.service;

import com.example.entity.Course;
import com.example.entity.Score;
import com.example.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Score)表服务接口
 *
 * @author wyl
 * @since 2021-10-13 22:19:55
 */
public interface ScoreService {

    /**
     * 通过ID查询单条数据
     *
     * @param scoreId 主键
     * @return 实例对象
     */
    Score findById(Integer scoreId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Score> findAll();

    /**
     * 添加选修课
     *
     * @param studentId 学生证
     * @param courseId  进程id
     * @return {@link Result}<{@link String}>
     */
    Result<String> insert(int studentId, int courseId);

    /**
     * 成绩录入
     *
     * @param map 地图
     * @return {@link String}
     */
    String entry(Map<String,Object> map);

    /**
     * 通过主键删除数据
     *
     * @param scoreId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer scoreId);

    /**
     * 分页只查询选课不重复
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseName  课程名称
     * @return {@link List}<{@link Score}>
     */
    List<Score> findList(int currentPage, int pageSize, String courseName);

    /**
     * 成绩分页  所有
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseName  课程名称
     * @param studentName 学生名字
     * @return {@link List}<{@link Score}>
     */
    List<Score> scoreList(int currentPage, int pageSize, String courseName, String studentName);

    /**
     * 根据课程查询学生 只查询选择选修课
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseId    进程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByCourseId(int currentPage,int pageSize,int courseId);

    /**
     * 修改状态
     *
     * @param studentId 学生证
     * @param courseId  进程id
     * @return int
     */
    int updateState(int studentId, int courseId);

    /**
     * 选修课统计
     *
     * @return {@link List}<{@link Score}>
     */
    List<Score> statistical();

    /**
     * 根据课程查询学生信息
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByCourse(int courseId);

    /**
     * 查询学生选课成绩
     *
     * @param studentId 学生证
     * @return {@link List}<{@link Score}>
     */
    List<Score> findByStudentId(int studentId);

    /**
     * 查询课程
     * 查看所有选课  包含取消的
     *
     * @param studentId 学生证
     * @return {@link List}<{@link Score}>
     */
    List<Score> findCourse(int studentId);

    /**
     * 查询bx课程
     * 查看所有必修课
     *
     * @param studentId 学生证
     * @return {@link List}<{@link Score}>
     */
    List<Score> findBxCourse(int studentId);

    /**
     * 删除选课
     *
     * @param courseId 进程id
     * @return int
     */
    int deleteCheck(int courseId);

    /**
     * 查看选修人数
     *
     * @param courseId 进程id
     * @return int
     */
    int checkCount(int courseId);

    /**
     * 根据课程ID查询成绩
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Score}>
     */
    List<Score> findScoreByCourseId(int courseId);

    /**
     * 修改成绩
     *
     * @param map 地图
     * @return {@link String}
     */
    String update(Map<String, Object> map);

    /**
     * 查询学分统计
     *
     * @param teacherId 老师id
     * @return {@link HashMap}<{@link String}, {@link Object}>
     */
    HashMap<String, Object> findCreditStatistical(String teacherId);

    /**
     * 教师查看成绩统计
     *
     * @param teacherId 老师id
     * @return {@link HashMap}<{@link String}, {@link Object}>
     */
    HashMap<String, Object> statistical(int teacherId);

    /**
     * 查询通过课程id
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByCourseId(int courseId);
}
