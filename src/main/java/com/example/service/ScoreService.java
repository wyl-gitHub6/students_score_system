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
     * @param currentPage
     * @param pageSize
     * @param courseName
     * @return
     */
    List<Score> findList(int currentPage, int pageSize, String courseName);

    /**
     * 成绩分页  所有
     * @param currentPage
     * @param pageSize
     * @param courseName
     * @param studentName
     * @return
     */
    List<Score> scoreList(int currentPage, int pageSize, String courseName, String studentName);

    /**
     * 根据课程查询学生 只查询选择选修课
     * @param currentPage
     * @param pageSize
     * @param courseId
     * @return
     */
    List<Course> findByCourseId(int currentPage,int pageSize,int courseId);

    /**
     * 修改状态
     * @param studentId
     * @param courseId
     * @return
     */
    int updateState(int studentId, int courseId);

    /**
     * 选修课统计
     * @return
     */
    List<Score> statistical();

    /**
     * 根据课程查询学生信息
     * @param courseId
     * @return
     */
    List<Course> findByCourse(int courseId);

    /**
     * 查询学生选课成绩
     * @param studentId
     * @return
     */
    List<Score> findByStudentId(int studentId);

    /**
     * 查看所有选课  包含取消的
     * @param studentId
     * @return
     */
    List<Score> findCourse(int studentId);

    /**
     * 查看所有必修课
     * @param studentId
     * @return
     */
    List<Score> findBxCourse(int studentId);

    /**
     * 删除选课
     * @param courseId
     * @return
     */
    int deleteCheck(int courseId);

    /**
     * 查看选修人数
     * @param courseId
     * @return
     */
    int checkCount(int courseId);

    /**
     * 根据课程ID查询成绩
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Score}>
     */
    List<Score> findScoreByCourseId(int courseId);

    Integer findYxCount(Integer courseId);

    Integer findLhCount(Integer courseId);

    Integer findJgCount(Integer courseId);

    Integer findBjgCount(Integer courseId);

    /**
     * 修改成绩
     *
     * @param map 地图
     * @return {@link String}
     */
    String update(Map<String, Object> map);

    HashMap<String, Object> findCreditStatistical(String teacherId);
}
