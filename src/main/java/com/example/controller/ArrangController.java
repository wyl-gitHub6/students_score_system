package com.example.controller;

import cn.hutool.db.Page;
import com.example.entity.Arrang;
import com.example.entity.Classes;
import com.example.service.ArrangService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Arrang)表控制层
 *
 * @author wyl
 * @since 2021-10-13 22:58:58
 */
@RestController
@RequestMapping("/arrang")
public class ArrangController {

    @Resource
    private ArrangService arrangService;

     /**
     * 查询班级
     *
     * @return 数据数组
     */
    @GetMapping("/findClasses")
    public Result findClasses(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                              @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                              @RequestParam(value = "classesName",defaultValue = "") String classesName) {
        List<Classes> list = this.arrangService.findClasses(currentPage,pageSize,classesName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param arrang 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Arrang arrang) {
        int i = arrangService.insert(arrang);
        if (i > 0){
            return Result.success("添加成功!");
        }
        return Result.error("添加失败!");
    }

    /**
     * 安排必修课
     * @param courseIdr
     * @param classesId
     * @return
     */
    @GetMapping("/arrangCourse")
    public Result arrangCourse(@RequestParam("courseIdr") int[] courseIdr,
                               @RequestParam("classesId") int classesId){
        String msg = arrangService.arrangCourse(courseIdr,classesId);
        return Result.success(msg);
    }

    @GetMapping("/findByClassesId")
    public Result findByClassesId(@RequestParam("classesId") int classesId){
        List<Classes> arrang = arrangService.findByClassesId(classesId);
        return Result.success(arrang,"查询成功!");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("courseId") int courseId,
                         @RequestParam("classesId") int classesId){
        boolean b = arrangService.delete(courseId,classesId);
        if (b){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }
}

