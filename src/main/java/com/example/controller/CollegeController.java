package com.example.controller;

import com.example.entity.College;
import com.example.service.CollegeService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (College)表控制层
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param collegeNum
     * @param collegeName
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "collegeNum",defaultValue = "") String collegeNum,
                           @RequestParam(value = "collegeName",defaultValue = "") String collegeName){
        List<College> list = collegeService.findList(currentPage, pageSize, collegeNum, collegeName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param collegeId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("collegeId") int collegeId) {
        return Result.success(this.collegeService.findById(collegeId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.collegeService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param college 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody College college) {
        int i = collegeService.insert(college);
        if (i > 0){
            return Result.success("添加成功!");
        }
        return Result.error("添加失败!");
    }

    /**
     * 编辑数据
     *
     * @param college 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody College college) {
        int i = collegeService.update(college);
        if (i > 0){
            return Result.success("编辑成功!");
        }
        return Result.error("编辑失败!");
    }

    /**
     * 删除数据
     *
     * @param collegeId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("collegeId") int collegeId) {
        boolean i = collegeService.deleteById(collegeId);
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
        boolean i = collegeService.deleteBatch(ids);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 查询院系数量
     * @return
     */
    @GetMapping("findCount")
    public Result findCount(){
        int count = collegeService.findCount();
        return Result.success(count,"查询成功!");
    }
}

