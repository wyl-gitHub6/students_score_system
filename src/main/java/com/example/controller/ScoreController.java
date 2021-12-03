package com.example.controller;

import com.example.entity.Course;
import com.example.entity.Score;
import com.example.service.*;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (Score)表控制层
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
     * @param currentPage
     * @param pageSize
     * @param courseName
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "courseName",defaultValue = "") String courseName){
        List<Score> list = scoreService.findList(currentPage, pageSize, courseName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 成绩查询分页
     * @param currentPage
     * @param pageSize
     * @param courseName
     * @param studentName
     * @return
     */
    @GetMapping("/scoreList")
    public Result scoreList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "courseName",defaultValue = "") String courseName,
                           @RequestParam(value = "studentName",defaultValue = "") String studentName){
        List<Score> list = scoreService.scoreList(currentPage, pageSize, courseName,studentName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }



    /**
     * 通过主键查询单条数据
     *
     * @param scoreId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("scoreId") int scoreId) {
        return Result.success(this.scoreService.findById(scoreId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.scoreService.findAll(),"查询成功!");
    }

    /**
     * 添加选修课
     * @param studentId
     * @param courseId
     * @return
     */
    @GetMapping("/insert")
    public Result insert(@RequestParam("studentId") int studentId,
                         @RequestParam("courseId") int courseId) {
        Result res = scoreService.insert(studentId,courseId);
        return res;
    }

    /**
     * 成绩录入
     *
     * @param score 实体
     * @return 编辑结果
     */
    @PutMapping("/entry")
    public Result entry(@RequestBody Score score) {
        String msg = scoreService.entry(score);
        return Result.success(msg);
    }

    /**
     * 删除数据
     *
     * @param scoreId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("scoreId") int scoreId) {
        boolean i = scoreService.deleteById(scoreId);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 修改成绩
     * @param score
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Score score){
        String msg = scoreService.update(score);
        return Result.success(msg);
    }

    /**
     * 修改选修状态
     * @param studentId
     * @param courseId
     * @return
     */
    @GetMapping("/updateState")
    public Result updateState(@RequestParam("studentId") int studentId,
                              @RequestParam("courseId") int courseId){
        int i = scoreService.updateState(studentId,courseId);
        if (i > 0){
            return Result.success("选修课取消成功!");
        }
        return Result.error("选修课取消失败!");
    }

    /**
     * 根据课程ID查询学生分页  只查询选择选修课
     * @param currentPage
     * @param pageSize
     * @param courseId
     * @return
     */
    @GetMapping("/findByCourseId")
    public Result findByCourseId(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                 @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                 @RequestParam("courseId") int courseId){
        List<Course> list = scoreService.findByCourseId(currentPage,pageSize,courseId);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 选修课统计
     * @return
     */
    @GetMapping("/statistical")
    public Result statistical(){
        List<Score> list = scoreService.statistical();
        return Result.success(list,"查询成功!");
    }

    /**
     * 根据课程查询无成绩学生信息
     * @param courseId
     * @return
     */
    @GetMapping("/findByCourse")
    public Result findByCourse(@RequestParam("courseId") int courseId){
        List<Course> list = scoreService.findByCourse(courseId);
        if (list.isEmpty()){
            return Result.error("暂无数据！");
        }
        return Result.success(list,"查询成功!");
    }

    /**
     * 学生查看必修课和选修课成绩
     * @param studentId
     * @return
     */
    @GetMapping("/findScore")
    public Result findScore(@RequestParam("studentId") int studentId){

        /*选修课程集合*/
        List<Score> xxList = scoreService.findByStudentId(studentId);

        List<Score> bxList = scoreService.findBxCourse(studentId);
        /*必修课集合*/

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("必修",bxList);
        hashMap.put("选修",xxList);

        return Result.success(hashMap,"查询成功!");
    }

    /**
     * 查看所有选课  包括取消的
     * @param studentId
     * @return
     */
    @GetMapping("/findCourse")
    public Result findCourse(@RequestParam("studentId") int studentId){

        List<Score> list = scoreService.findCourse(studentId);
        return Result.success(list,"查询成功!");
    }

    /**
     * 停止选修课程
     * @param courseId
     * @return
     */
    @GetMapping("/deleteCheck")
    public Result deleteCheck(@RequestParam("courseId") int courseId){
        /*查看选修该课程人数*/
        int count = scoreService.checkCount(courseId);
        if (count >= 25){
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
     * @param courseId
     * @return
     */
    @GetMapping("/findScoreByCourseId")
    public Result findScoreByCourseId(@RequestParam("courseId") int courseId){
        List<Score> list = scoreService.findScoreByCourseId(courseId);
        if (list.isEmpty()){
            return Result.error("暂无数据!");
        }
        return Result.success(list,"查询成功!");
    }

    /**
     * 教师查看各科成绩统计
     * @param teacherId
     * @return
     */
    @GetMapping("/statistical/{teacherId}")
    public Result statistical(@PathVariable("teacherId") int teacherId){
        List<Course> courses = courseService.findByTeacherId(teacherId);
        List<String> courseNames = new ArrayList<>();
        List<Integer> yx = new ArrayList<>();
        List<Integer> lh = new ArrayList<>();
        List<Integer> jg = new ArrayList<>();
        List<Integer> bjg = new ArrayList<>();
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
        HashMap<Object, Object> map = new HashMap<>();
        map.put("courseName",courseNames);
        map.put("yx",yx);
        map.put("lh",lh);
        map.put("jg",jg);
        map.put("bjg",bjg);
        return Result.success(map,"查询成功！");
    }
}

