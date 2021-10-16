package com.example.controller;

import com.example.entity.Course;
import com.example.service.CourseService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Course)表控制层
 *
 * @author wyl
 * @since 2021-10-11 22:26:53
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;


    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "courseNum",defaultValue = "") String courseNum,
                           @RequestParam(value = "courseName",defaultValue = "") String courseName,
                           @RequestParam(value = "courseState",defaultValue = "-1") int courseState){
        List<Course> list = courseService.findList(currentPage, pageSize, courseNum, courseName,courseState);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param courseId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("courseId") int courseId) {
        return Result.success(this.courseService.findById(courseId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.courseService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param course 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Course course) {
        int i = courseService.insert(course);
        if (i > 0){
            return Result.success("添加成功!");
        }
        return Result.error("添加失败!");
    }

    /**
     * 编辑数据
     *
     * @param course 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Course course) {
        int i = courseService.update(course);
        if (i > 0){
            return Result.success("编辑成功!");
        }
        return Result.error("编辑失败!");
    }

    /**
     * 删除数据
     *
     * @param courseId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("courseId") int courseId) {
        boolean i = courseService.deleteById(courseId);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

     /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = courseService.deleteBatch(ids);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    @GetMapping("/findByCourseState")
    public Result findByCourseState(@RequestParam("courseState") int courseState){
        List<Course> list = courseService.findByCourseState(courseState);
        return Result.success(list,"查询成功!");
    }
}

