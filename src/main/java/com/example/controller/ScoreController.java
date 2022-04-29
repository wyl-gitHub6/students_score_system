package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.Course;
import com.example.entity.Score;
import com.example.service.*;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 成绩
 *
 * @author wyl
 * @since 2021-10-13 22:19:55
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @Resource
    private CourseService courseService;

    /**
     * 分页查询选修课不重复
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseName  课程名称
     * @return {@link Result}<{@link PageInfo}<{@link Score}>>
     */
    @GetMapping("/findList")
    public Result<PageInfo<Score>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "courseName",defaultValue = "") String courseName){
        List<Score> list = scoreService.findList(currentPage, pageSize, courseName);
        PageInfo<Score> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 成绩查询分页
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseName  课程名称
     * @param studentName 学生名字
     * @return {@link Result}<{@link PageInfo}<{@link Score}>>
     */
    @GetMapping("/scoreList")
    public Result<PageInfo<Score>> scoreList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "courseName",defaultValue = "") String courseName,
                           @RequestParam(value = "studentName",defaultValue = "") String studentName){
        List<Score> list = scoreService.scoreList(currentPage, pageSize, courseName,studentName);
        PageInfo<Score> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param scoreId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<Score> findById(@RequestParam("scoreId") int scoreId) {
        return Result.success(this.scoreService.findById(scoreId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<Score>> findAll() {
        return Result.success(this.scoreService.findAll(),"查询成功!");
    }

    /**
     * 添加选修课
     *
     * @param studentId 学生证
     * @param courseId  进程id
     * @return {@link Result}<{@link String}>
     */
    @GetMapping("/insert")
    public Result<String> insert(@RequestParam("studentId") int studentId,
                         @RequestParam("courseId") int courseId) {
        return scoreService.insert(studentId,courseId);
    }

    /**
     * 成绩录入
     *
     * @param map 对象
     * @return {@link Result}<{@link String}>
     */
    @PutMapping("/entry")
    public Result<String> entry(@RequestBody Map<String,Object> map) {
        String msg = scoreService.entry(map);
        return Result.success(msg);
    }

    /**
     * 删除数据
     *
     * @param scoreId 分数id
     * @return {@link Result}<{@link String}>
     */
    @DeleteMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("scoreId") int scoreId) {
        boolean i = scoreService.deleteById(scoreId);
        if (i){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 修改成绩
     *
     * @param map 地图
     * @return {@link Result}<{@link String}>
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Map<String, Object> map){
        String msg = scoreService.update(map);
        return Result.success(msg);
    }

    /**
     * 修改选修状态
     *
     * @param studentId 学生id
     * @param courseId  课程id
     * @return {@link Result}
     */
    @GetMapping("/updateState")
    public Result<String> updateState(@RequestParam("studentId") int studentId,
                              @RequestParam("courseId") int courseId){
        int i = scoreService.updateState(studentId,courseId);
        if (i > 0){
            return Result.success("选修课取消成功!");
        }
        return Result.error("选修课取消失败!");
    }

    /**
     * 根据课程ID查询学生分页  只查询选择选修课
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseId    进程id
     * @return {@link Result}<{@link PageInfo}<{@link Course}>>
     */
    @GetMapping("/findByCourseId")
    public Result<PageInfo<Course>> findByCourseId(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                 @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                 @RequestParam("courseId") int courseId){
        List<Course> list = scoreService.findByCourseId(currentPage,pageSize,courseId);
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 选修课统计
     *
     * @return {@link Result}
     */
    @GetMapping("/statistical")
    public Result<List<Score>> statistical(){
        List<Score> list = scoreService.statistical();
        return Result.success(list,"查询成功!");
    }

    /**
     * 根据课程查询无成绩学生信息
     *
     * @param courseId 课程id
     * @return {@link Result}
     */
    @GetMapping("/findByCourse")
    public Result<List<Course>> findByCourse(@RequestParam("courseId") int courseId){
        List<Course> list = scoreService.findByCourse(courseId);
        if (list.isEmpty()){
            return Result.error("暂无数据！");
        }
        return Result.success(list,"查询成功!");
    }

    /**
     * 学生查看必修课和选修课成绩
     *
     * @param studentId 学生证
     * @return {@link Result}<{@link HashMap}<{@link Object}, {@link Object}>>
     */
    @GetMapping("/findScore")
    public Result<HashMap<Object, Object>> findScore(@RequestParam("studentId") int studentId){
        /*选修课程集合*/
        List<Score> xxList = scoreService.findByStudentId(studentId);
        /*必修课集合*/
        List<Score> bxList = scoreService.findBxCourse(studentId);
        HashMap<Object, Object> hashMap = new HashMap<>(1);
        hashMap.put("必修",bxList);
        hashMap.put("选修",xxList);
        return Result.success(hashMap,"查询成功!");
    }

    /**
     * 查看所有选课  包括取消的
     *
     * @param studentId 学生证
     * @return {@link Result}<{@link List}<{@link Score}>>
     */
    @GetMapping("/findCourse")
    public Result<List<Score>> findCourse(@RequestParam("studentId") int studentId){
        List<Score> list = scoreService.findCourse(studentId);
        return Result.success(list,"查询成功!");
    }

    /**
     * 停止选修课程
     *
     * @param courseId 进程id
     * @return {@link Result}<{@link String}>
     */
    @GetMapping("/deleteCheck")
    public Result<String> deleteCheck(@RequestParam("courseId") int courseId){
        /*查看选修该课程人数*/
        int count = scoreService.checkCount(courseId);
        if (count >= MyConstant.COURSE_MIN_NUMBER){
            return Result.error("该课程已满足开课要求!不可停课");
        }

        int i = scoreService.deleteCheck(courseId);
        if (i > 0){
            return Result.success("停课成功!");
        }
        return Result.error("停课失败!");
    }

    /**
     * 根据课程ID查询成绩
     *
     * @param courseId 进程id
     * @return {@link Result}<{@link List}<{@link Score}>>
     */
    @GetMapping("/findScoreByCourseId")
    public Result<List<Score>> findScoreByCourseId(@RequestParam("courseId") int courseId){
        List<Score> list = scoreService.findScoreByCourseId(courseId);
        if (list.isEmpty()){
            return Result.error("暂无数据!");
        }
        return Result.success(list,"查询成功!");
    }

    /**
     * 教师查看各科成绩统计
     *
     * @param teacherId 老师id
     * @return {@link Result}<{@link HashMap}<{@link Object}, {@link Object}>>
     */
    @GetMapping("/statistical/{teacherId}")
    public Result<HashMap<Object, Object>> statistical(@PathVariable("teacherId") int teacherId){
        List<Course> courses = courseService.findByTeacherId(teacherId);
        List<String> courseNames = new LinkedList<>();
        List<Integer> yx = new LinkedList<>();
        List<Integer> lh = new LinkedList<>();
        List<Integer> jg = new LinkedList<>();
        List<Integer> bjg = new LinkedList<>();
        courses.forEach(c->{
            Course course = courseService.findById(c.getCourseId());
            Integer yxCount = scoreService.findYxCount(c.getCourseId());
            Integer lhCount = scoreService.findLhCount(c.getCourseId());
            Integer jgCount = scoreService.findJgCount(c.getCourseId());
            Integer bjgCount = scoreService.findBjgCount(c.getCourseId());
            courseNames.add(course.getCourseName());
            yx.add(ObjectUtils.isEmpty(yxCount)?0:yxCount);
            lh.add(ObjectUtils.isEmpty(lhCount)?0:lhCount);
            jg.add(ObjectUtils.isEmpty(jgCount)?0:jgCount);
            bjg.add(ObjectUtils.isEmpty(bjgCount)?0:bjgCount);
        });
        HashMap<Object, Object> map = new HashMap<>(5);
        map.put("courseName",courseNames);
        map.put("yx",yx);
        map.put("lh",lh);
        map.put("jg",jg);
        map.put("bjg",bjg);
        return Result.success(map,"查询成功！");
    }

    /**
     * 查询平均学分
     *
     * @param teacherId 老师id
     * @return {@link Result}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    @GetMapping("/findCreditStatistical/{teacherId}")
    public Result<HashMap<String, Object>> findCreditStatistical(@PathVariable("teacherId") String teacherId){
        HashMap<String, Object> map = scoreService.findCreditStatistical(teacherId);
        return Result.success(map,"查询成功");
    }
}

