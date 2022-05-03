package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.example.service.ScoreService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程
 *
 * @author wyl
 * @since 2021-10-11 22:26:53
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private ScoreService scoreService;


    /**
     * 查询课程
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param courseNum   当然num
     * @param courseName  课程名称
     * @param courseState 课程状态
     * @return {@link Result}
     */
    @GetMapping("/findList")
    public Result<PageInfo<Course>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "courseNum",defaultValue = "") String courseNum,
                           @RequestParam(value = "courseName",defaultValue = "") String courseName,
                           @RequestParam(value = "courseState",defaultValue = "-1") int courseState){
        List<Course> list = courseService.findList(currentPage, pageSize, courseNum, courseName,courseState);
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param courseId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<Course> findById(@RequestParam("courseId") int courseId) {
        return Result.success(this.courseService.findById(courseId),MyConstant.RES_SUCCESS_MESSAGE);
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<Course>> findAll() {
        return Result.success(this.courseService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param course 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Course course) {
        int i = courseService.insert(course);
        if (i > 0){
            return Result.success(MyConstant.RES_INSERT_SUCCESS);
        }
        return Result.error(MyConstant.RES_INSERT_SUCCESS);
    }

    /**
     * 编辑数据
     *
     * @param course 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Course course) {
        int i = courseService.update(course);
        if (i > 0){
            return Result.success(MyConstant.RES_UPDATE_SUCCESS);
        }
        return Result.error(MyConstant.RES_UPDATE_FAILED);
    }

    /**
     * 删除数据
     *
     * @param courseId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("courseId") int courseId) {

        List<Course> course = scoreService.findByCourseId(courseId);
        if (!course.isEmpty()){
            return Result.error(MyConstant.RES_DATA_USE);
        }

        boolean i = courseService.deleteById(courseId);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 删除批处理
     *
     * @param ids 课程id数组
     * @return {@link Result}
     */
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = courseService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }


    /**
     * 根据课程类型查询
     *
     * @param courseState 课程状态
     * @return {@link Result}<{@link List}<{@link Course}>>
     */
    @GetMapping("/findByCourseState")
    public Result<List<Course>> findByCourseState(@RequestParam("courseState") int courseState){
        List<Course> list = courseService.findByCourseState(courseState);
        return Result.success(list, MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询教师所教授课程
     *
     * @param teacherId 老师id
     * @return {@link Result}<{@link List}<{@link Course}>>
     */
    @GetMapping("/findByTeacherId")
    public Result<List<Course>> findByTeacherId(@RequestParam("teacherId") int teacherId){
        List<Course> list = courseService.findByTeacherId(teacherId);
        if (list.isEmpty()){
            return Result.error(MyConstant.RES_DATA_NULL);
        }
        return Result.success(list,MyConstant.RES_SUCCESS_MESSAGE);
    }
}

