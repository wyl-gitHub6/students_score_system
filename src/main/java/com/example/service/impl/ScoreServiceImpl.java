package com.example.service.impl;

import com.example.constant.MyConstant;
import com.example.dao.CourseDao;
import com.example.entity.Course;
import com.example.entity.Score;
import com.example.dao.ScoreDao;
import com.example.service.ScoreService;
import com.example.utils.OverAll;
import com.example.utils.Result;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

/**
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

    @Resource
    private OverAll overAll;

    @Override
    public Score findById(Integer scoreId) {
        return this.scoreDao.findById(scoreId);
    }

    @Override
    public List<Score> findAll() {
        return this.scoreDao.findAll();
    }

    @Override
    public synchronized Result<String> insert(int studentId, int courseId) {
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

    @Override
    public String entry(Map<String,Object> map) {
        int studentId = (int) map.get("studentId");
        int courseId = (int) map.get("courseId");
        double testGrade = Double.parseDouble((String.valueOf(map.get("testGrade"))));
        double usualGrade = Double.parseDouble(String.valueOf(map.get("usualGrade")));

        Pair<String, BigDecimal> pair = overAll.calculate(map.get("domains"), testGrade, usualGrade);
        String builder = pair.getLeft();
        double scoreGrade = pair.getRight().doubleValue();

        Score s = scoreDao.findByStudentIdAndCourseId(studentId, courseId);
        String msg = "";
        Course course = courseDao.findById(courseId);

        if (null == s){
            /*必修课*/
            Score sc = new Score();
            sc.setStudentId(studentId);
            sc.setCourseId(courseId);
            sc.setUsualGrade(usualGrade);
            sc.setTestGrade(testGrade);
            sc.setScoreGrade(scoreGrade);
            sc.setStageGrade(builder);
            sc.setGradeState(MyConstant.ONE);
            sc.setState(MyConstant.TWO);
            if (scoreGrade>=MyConstant.PASS_GRADE){
                sc.setCredit(course.getCourseCredit());
                msg=MyConstant.ENTRY_GRADE_PASS;
            }else{
                sc.setCredit(MyConstant.ZERO);
                msg=MyConstant.ENTRY_GRADE_FAILED;
            }
            /*库中没有信息 添加*/
            scoreDao.entryInsert(sc);
            return msg;
        }
        /*选修课*/
        s.setTestGrade(testGrade);
        s.setUsualGrade(usualGrade);
        s.setScoreGrade(scoreGrade);
        s.setStageGrade(builder);
        s.setGradeState(MyConstant.ONE);
        if (scoreGrade>=MyConstant.PASS_GRADE){
            s.setCredit(course.getCourseCredit());
            msg=MyConstant.ENTRY_GRADE_PASS;
        }else{
            s.setCredit(0);
            msg=MyConstant.ENTRY_GRADE_FAILED;
        }
        /*库中已有信息 直接修改*/
        this.scoreDao.update(s);
        return msg;
    }

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
        return scoreDao.findByCourseList(courseId);
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
    public String update(Map<String, Object> map) {

        int courseId = (int) map.get("courseId");
        double testGrade = Double.parseDouble((String.valueOf(map.get("testGrade"))));
        double usualGrade = Double.parseDouble(String.valueOf(map.get("usualGrade")));

        /*获取课程信息*/
        Course course = courseDao.findById(courseId);
        String msg = "";

        Pair<String, BigDecimal> pair = overAll.calculate(map.get("domains"), usualGrade, testGrade);
        String builder = pair.getLeft();
        double scoreGrade = pair.getRight().doubleValue();

        Score score = new Score();

        if (scoreGrade>=MyConstant.PASS_GRADE){
            /*获得学分*/
            score.setCredit(course.getCourseCredit());
            msg=MyConstant.ENTRY_GRADE_PASS;
        }else{
            /*学分为0*/
            score.setCredit(MyConstant.ZERO);
            msg=MyConstant.ENTRY_GRADE_FAILED;
        }
        score.setStageGrade(builder);
        score.setTestGrade(testGrade);
        score.setUsualGrade(usualGrade);
        score.setScoreGrade(scoreGrade);
        score.setScoreId((int) map.get("scoreId"));
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
            courseList.add(ObjectUtils.isEmpty(s.get(MyConstant.COURSE_NAME))?"": s.get(MyConstant.COURSE_NAME) +"-[学分:"+ String.valueOf(s.get(MyConstant.CREDIT)) +"分]");
            creditList.add(ObjectUtils.isEmpty(s.get(MyConstant.CREDIT))?0:s.get("creditAvg"));
        });
        hashMap.put(MyConstant.COURSE_NAME,courseList);
        hashMap.put(MyConstant.CREDIT,creditList);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> statistical(int teacherId) {
        List<Course> courses = courseDao.findByTeacherId(teacherId);
        List<String> courseNames = new LinkedList<>();
        List<Integer> yx = new LinkedList<>();
        List<Integer> lh = new LinkedList<>();
        List<Integer> zd = new LinkedList<>();
        List<Integer> jg = new LinkedList<>();
        List<Integer> bjg = new LinkedList<>();
        courses.forEach(c->{
            Course course = courseDao.findById(c.getCourseId());
            Integer yxCount = scoreDao.findYxCount(c.getCourseId());
            Integer lhCount = scoreDao.findLhCount(c.getCourseId());
            Integer zdCount = scoreDao.findZdCount(c.getCourseId());
            Integer jgCount = scoreDao.findJgCount(c.getCourseId());
            Integer bjgCount = scoreDao.findBjgCount(c.getCourseId());
            courseNames.add(course.getCourseName());
            yx.add(yxCount);
            lh.add(lhCount);
            zd.add(zdCount);
            jg.add(jgCount);
            bjg.add(bjgCount);
        });
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("courseName",courseNames);
        map.put("yx",yx);
        map.put("lh",lh);
        map.put("zd",zd);
        map.put("jg",jg);
        map.put("bjg",bjg);
        return map;
    }

    @Override
    public List<Course> findByCourseId(int courseId) {
        return scoreDao.findByCourseId(courseId);
    }
}
