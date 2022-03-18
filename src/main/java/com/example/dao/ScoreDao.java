package com.example.dao;

import com.example.entity.Course;
import com.example.entity.Score;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * (Score)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-13 22:19:55
 */
 @Mapper
public interface ScoreDao {

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
     * 选课
     * @param studentId
     * @param courseId
     * @param state
     * @param gradeState
     * @return
     */
    int insert(@Param("studentId") int studentId,
               @Param("courseId") int courseId,
               @Param("state") int state,
               @Param("gradeState") int gradeState);

    /**
     * 修改数据
     *
     * @param score 实例对象
     * @return 影响行数
     */
    int update(Score score);

    /**
     * 通过主键删除数据
     *
     * @param scoreId 主键
     * @return 影响行数
     */
    int deleteById(Integer scoreId);

    /**
     * 根据学生ID和课程ID查询
     * @param studentId
     * @param courseId
     * @return
     */
    Score findByStudentIdAndCourseId(@Param("studentId") int studentId,
                                     @Param("courseId") int courseId);

    /**
     * 根据学生ID查询数量
     * @param studentId
     * @return
     */
    int findCountByStudentId(int studentId);

    /**
     * 根据课程ID查询数量
     * @param courseId
     * @return
     */
    int findCountByCourseId(int courseId);

    /**
     * 根据课程名称模糊查询选修课不重复
     * @param courseName
     * @return
     */
    List<Score> findList(String courseName);

    /**
     * 根据课程名模糊查询选修课  所有
     * @param courseName
     * @param studentName
     * @return
     */
    List<Score> scoreList(@Param("courseName") String courseName,
                          @Param("studentName") String studentName);

    /**
     * 根据课程ID查询学生  只查询选择选修课
     * @param courseId
     * @return
     */
    List<Course> findByCourseId(int courseId);

    /**
     * 选修课统计
     * @return
     */
    List<Score> statistical();

    /**
     * 根据课程查询无成绩学生
     * @param courseId
     * @return
     */
    List<Course> findByCourse(int courseId);

    /**
     * 必修课录入成绩
     * @param sc
     * @return
     */
    int entryInsert(Score sc);

    /**
     * 根据学生ID查询选修课  选择的
     * @param studentId
     * @return
     */
    List<Score> findByStudentId(int studentId);

    /**
     * 查看学生所有选课  包含取消的
     * @param studentId
     * @return
     */
    List<Score> findCourse(int studentId);

    /**
     * 查询必修课
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
     * @param courseId
     * @return
     */
    List<Score> findScoreByCourseId(int courseId);

    Integer findYxCount(Integer courseId);

    Integer findLhCount(Integer courseId);

    Integer findJgCount(Integer courseId);

    Integer findBjgCount(Integer courseId);

    /**
     * 查询平均学分
     * @param teacherId
     * @return
     */
    @MapKey("scoreId")
    HashMap<String,HashMap<String,String>> findCreditStatistical(String teacherId);
}

