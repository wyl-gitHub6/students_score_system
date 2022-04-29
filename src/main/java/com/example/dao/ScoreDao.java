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
     *
     * @param studentId  学生证
     * @param courseId   进程id
     * @param state      状态
     * @param gradeState 级状态
     * @return int
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
     *
     * @param studentId 学生证
     * @param courseId  进程id
     * @return {@link Score}
     */
    Score findByStudentIdAndCourseId(@Param("studentId") int studentId,
                                     @Param("courseId") int courseId);

    /**
     * 根据学生ID查询数量
     *
     * @param studentId 学生证
     * @return int
     */
    int findCountByStudentId(int studentId);

    /**
     * 根据课程ID查询数量
     *
     * @param courseId 进程id
     * @return int
     */
    int findCountByCourseId(int courseId);

    /**
     * 根据课程名称模糊查询选修课不重复
     *
     * @param courseName 课程名称
     * @return {@link List}<{@link Score}>
     */
    List<Score> findList(String courseName);

    /**
     * 根据课程名模糊查询查询所有课程 不包含取消的
     *
     * @param courseName  课程名称
     * @param studentName 学生名字
     * @return {@link List}<{@link Score}>
     */
    List<Score> scoreList(@Param("courseName") String courseName,
                          @Param("studentName") String studentName);

    /**
     * 根据课程ID查询学生  只查询选择选修课
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByCourseId(int courseId);

    /**
     * 选修课统计
     *
     * @return {@link List}<{@link Score}>
     */
    List<Score> statistical();

    /**
     * 根据课程查询无成绩学生
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findByCourse(int courseId);

    /**
     * 必修课录入成绩
     *
     * @param sc sc
     * @return int
     */
    int entryInsert(Score sc);

    /**
     * 根据学生ID查询选修课  选择的
     *
     * @param studentId 学生证
     * @return {@link List}<{@link Score}>
     */
    List<Score> findByStudentId(int studentId);

    /**
     * 查看学生所有选课  包含取消的
     *
     * @param studentId 学生证
     * @return {@link List}<{@link Score}>
     */
    List<Score> findCourse(int studentId);

    /**
     * 查询必修课
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
     * 查询分数通过课程id
     *
     * @param courseId 进程id
     * @return {@link List}<{@link Score}>
     */
    List<Score> findScoreByCourseId(int courseId);

    /**
     * 查询优秀数
     *
     * @param courseId 进程id
     * @return {@link Integer}
     */
    Integer findYxCount(Integer courseId);

    /**
     * 查询良好数
     *
     * @param courseId 进程id
     * @return {@link Integer}
     */
    Integer findLhCount(Integer courseId);

    /**
     * 查询及格数
     *
     * @param courseId 进程id
     * @return {@link Integer}
     */
    Integer findJgCount(Integer courseId);

    /**
     * 查询不及格数
     *
     * @param courseId 进程id
     * @return {@link Integer}
     */
    Integer findBjgCount(Integer courseId);

    /**
     * 查询平均学分
     *
     * @param teacherId 老师id
     * @return {@link HashMap}<{@link String}, {@link HashMap}<{@link String}, {@link String}>>
     */
    @MapKey("courseId")
    HashMap<String,HashMap<String,String>> findCreditStatistical(String teacherId);
}

