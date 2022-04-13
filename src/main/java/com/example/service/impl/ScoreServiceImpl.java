package com.example.service.impl;

import com.example.constant.MyConstant;
import com.example.dao.CourseDao;
import com.example.entity.Course;
import com.example.entity.Score;
import com.example.dao.ScoreDao;
import com.example.service.ScoreService;
import com.example.utils.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

/**
 * (Score)表服务实现类
 *
 * @author wyl
 * @since 2021-10-13 22:19:55
 */
@Service("scoreService")
@Transactional(rollbackFor = Exception.class)
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreDao scoreDao;

    @Resource
    private CourseDao courseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param scoreId 主键
     * @return 实例对象
     */
    @Override
    public Score findById(Integer scoreId) {
        return this.scoreDao.findById(scoreId);
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Score> findAll() {
        return this.scoreDao.findAll();
    }

    /**
     * 添加选修课
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public Result insert(int studentId, int courseId) {
        Course course = courseDao.findById(courseId);
        int number = scoreDao.findCountByCourseId(courseId);
        if (number >= course.getNumber()){
            return Result.error("这门课程选满啦!");
        }
        Score sc = scoreDao.findByStudentIdAndCourseId(studentId, courseId);
        if (null == sc){
            int count = scoreDao.findCountByStudentId(studentId);
            if (count >= MyConstant.CHECK_COURSE_MAX_NUMBER){
                return Result.error("已选择3门课程,不能再选啦!");
            }else{
                int i = this.scoreDao.insert(studentId, courseId,MyConstant.ZERO,MyConstant.ZERO);
                if (i > MyConstant.ZERO){
                    return Result.success("选择课程成功!");
                }
            }
        }
        return Result.error("已经选择该课程,不能再选啦!");
    }

    /**
     * 录入成绩
     *
     * @param score 实例对象
     * @return 实例对象
     */
    @Override
    public String entry(Score score) {
        Score s = scoreDao.findByStudentIdAndCourseId(score.getStudentId(), score.getCourseId());
        String msg = "";
        Course course = courseDao.findById(score.getCourseId());
        double grade = (double) Math.round(score.getScoreGrade());
        if (null == s){
            /*必修课*/
            Score sc = new Score();
            sc.setStudentId(score.getStudentId());
            sc.setCourseId(score.getCourseId());
            sc.setUsualGrade(score.getUsualGrade());
            sc.setTestGrade(score.getTestGrade());
            sc.setScoreGrade(grade);
            sc.setGradeState(MyConstant.ONE);
            if (grade>=MyConstant.PASS_GRADE){
                sc.setCredit(course.getCourseCredit());
                msg="成绩录入成功!";
            }else{
                sc.setCredit(MyConstant.ZERO);
                msg="该同学暂无学分，将通知其补考或重修!";
            }
            scoreDao.entryInsert(sc);

            return msg;
        }
        /*选修课*/
        s.setTestGrade(score.getTestGrade());
        s.setUsualGrade(score.getUsualGrade());
        s.setScoreGrade(grade);
        s.setGradeState(MyConstant.ONE);
        if (grade>=MyConstant.PASS_GRADE){
            s.setCredit(course.getCourseCredit());
            msg="成绩录入成功!";
        }else{
            s.setCredit(0);
            msg="该同学暂无学分，将通知其补考或重修!";
        }
        this.scoreDao.update(s);
        return msg;
    }

    /**
     * 通过主键删除数据
     *
     * @param scoreId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer scoreId) {
        return this.scoreDao.deleteById(scoreId) > 0;
    }

    @Override
    public List<Score> findList(int currentPage, int pageSize, String courseName) {
        PageHelper.startPage(currentPage,pageSize);
        return scoreDao.findList(courseName);
    }

    @Override
    public List<Score> scoreList(int currentPage, int pageSize, String courseName, String studentName) {
        PageHelper.startPage(currentPage,pageSize);
        return scoreDao.scoreList(courseName,studentName);
    }

    @Override
    public List<Course> findByCourseId(int currentPage, int pageSize, int courseId) {
        PageHelper.startPage(currentPage,pageSize);
        return scoreDao.findByCourseId(courseId);
    }

    @Override
    public int updateState(int studentId, int courseId) {
        Score sc = scoreDao.findByStudentIdAndCourseId(studentId, courseId);
        sc.setState(MyConstant.ONE);
        return scoreDao.update(sc);
    }

    @Override
    public List<Score> statistical() {
        return scoreDao.statistical();
    }

    @Override
    public List<Course> findByCourse(int courseId) {
        return scoreDao.findByCourse(courseId);
    }

    @Override
    public List<Score> findByStudentId(int studentId) {
        return scoreDao.findByStudentId(studentId);
    }

    @Override
    public List<Score> findBxCourse(int studentId) {
        return scoreDao.findBxCourse(studentId);
    }

    @Override
    public int deleteCheck(int courseId) {
        return scoreDao.deleteCheck(courseId);
    }

    @Override
    public int checkCount(int courseId) {
        return scoreDao.checkCount(courseId);
    }

    @Override
    public List<Score> findScoreByCourseId(int courseId) {
        return scoreDao.findScoreByCourseId(courseId);
    }

    @Override
    public Integer findYxCount(Integer courseId) {
        return scoreDao.findYxCount(courseId);
    }

    @Override
    public Integer findLhCount(Integer courseId) {
        return scoreDao.findLhCount(courseId);
    }

    @Override
    public Integer findJgCount(Integer courseId) {
        return scoreDao.findJgCount(courseId);
    }

    @Override
    public Integer findBjgCount(Integer courseId) {
        return scoreDao.findBjgCount(courseId);
    }

    @Override
    public String update(Score score) {
        Course course = courseDao.findById(score.getCourse().getCourseId());
        String msg = "";
        double grade = (double) Math.round(score.getScoreGrade());
        if (grade>=MyConstant.PASS_GRADE){
            score.setCredit(course.getCourseCredit());
            msg="成绩录入成功!";
        }else{
            score.setCredit(MyConstant.ZERO);
            msg="该同学暂无学分，将通知其补考或重修!";
        }
        score.setScoreGrade(grade);
        scoreDao.update(score);
        return msg;
    }

    @Override
    public List<Score> findCourse(int studentId) {
        return scoreDao.findCourse(studentId);
    }

    @Override
    public HashMap<String, Object> findCreditStatistical(String teacherId) {
        HashMap<String, HashMap<String, String>> map = scoreDao.findCreditStatistical(teacherId);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        LinkedList<Object> courseList = new LinkedList<>();
        LinkedList<Object> creditList = new LinkedList<>();
        map.values().forEach(s->{
            courseList.add(ObjectUtils.isEmpty(s.get("courseName"))?"":String.valueOf(s.get("courseName"))+"-[学分:"+String.valueOf(s.get("credit"))+"分]");
            creditList.add(ObjectUtils.isEmpty(s.get("credit"))?0:s.get("creditAvg"));
        });
        hashMap.put("courseName",courseList);
        hashMap.put("credit",creditList);
        return hashMap;
    }
}
