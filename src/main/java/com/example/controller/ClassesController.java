package com.example.controller;

import com.example.entity.Classes;
import com.example.service.ClassesService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classes)表控制层
 *
 * @author wyl
 * @since 2021-10-09 11:02:58
 */
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Resource
    private ClassesService classesService;

    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "classesNum",defaultValue = "") String classesNum,
                           @RequestParam(value = "classesName",defaultValue = "") String classesName){
        List<Classes> list = classesService.findList(currentPage, pageSize, classesNum, classesName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param classesId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("classesId") int classesId) {
        return Result.success(this.classesService.findById(classesId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.classesService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param classes 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Classes classes) {
        Classes cla = classesService.findByGradeIdAndClassesName(classes.getGrade().getGradeId(), classes.getClassesName());
        if (null == cla){
            int i = classesService.insert(classes);
            if (i > 0){
                return Result.success("添加成功!");
            }
            return Result.error("添加失败!");
        }
        return Result.error("该年级下已有此班级!");
    }

    /**
     * 编辑数据
     *
     * @param classes 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Classes classes) {
        Classes cla = classesService.findByIdGradeIdAndClassesName(classes.getClassesId(),
                classes.getGrade().getGradeId(), classes.getClassesName());
        if (null == cla){
            int i = classesService.update(classes);
            if (i > 0){
                return Result.success("编辑成功!");
            }
            return Result.error("编辑失败!");
        }
        return Result.error("该年级下已有此班级!");
    }

    /**
     * 删除数据
     *
     * @param classesId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("classesId") int classesId) {
        boolean i = classesService.deleteById(classesId);
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
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = classesService.deleteBatch(ids);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 根据所在年级查询
     * @param currentPage
     * @param pageSize
     * @param gradeId
     * @param classesNum
     * @param classesName
     * @return
     */
    @GetMapping("/findByGradeId")
    public Result findByGradeId(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "gradeId") int gradeId,
                           @RequestParam(value = "classesNum",defaultValue = "") String classesNum,
                           @RequestParam(value = "classesName",defaultValue = "") String classesName){
        List<Classes> list = classesService.findByGradeId(currentPage, pageSize,gradeId, classesNum, classesName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    @GetMapping("/driver")
    public Result driver(@RequestParam("ids") int[] ids,
                         @RequestParam("classesId") int classesId){
        int i = classesService.driver(ids, classesId);
        return Result.success("本次共为"+i+"位同学分班");
    }
}

