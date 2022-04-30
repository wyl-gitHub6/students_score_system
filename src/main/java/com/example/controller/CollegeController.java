package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.College;
import com.example.service.CollegeService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学院
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
     * 查询学院
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param collegeNum  学院编号
     * @param collegeName 学院名称
     * @return {@link Result}
     */
    @GetMapping("/findList")
    public Result<PageInfo<College>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "collegeNum",defaultValue = "") String collegeNum,
                           @RequestParam(value = "collegeName",defaultValue = "") String collegeName){
        List<College> list = collegeService.findList(currentPage, pageSize, collegeNum, collegeName);
        PageInfo<College> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo, MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param collegeId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<College> findById(@RequestParam("collegeId") int collegeId) {
        return Result.success(this.collegeService.findById(collegeId),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询所有
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<College>> findAll() {
        return Result.success(this.collegeService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param college 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody College college) {
        int i = collegeService.insert(college);
        if (i > 0){
            return Result.success(MyConstant.RES_INSERT_SUCCESS);
        }
        return Result.error(MyConstant.RES_INSERT_SUCCESS);
    }

    /**
     * 编辑数据
     *
     * @param college 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody College college) {
        int i = collegeService.update(college);
        if (i > 0){
            return Result.success(MyConstant.RES_UPDATE_SUCCESS);
        }
        return Result.error(MyConstant.RES_UPDATE_FAILED);
    }

    /**
     * 删除数据
     *
     * @param collegeId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("collegeId") int collegeId) {
        boolean i = collegeService.deleteById(collegeId);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 删除批处理
     *
     * @param ids id
     * @return {@link Result}
     */
    @DeleteMapping("deleteBatch")
    public Result<String> deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = collegeService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 查询院系数量
     *
     * @return {@link Result}
     */
    @GetMapping("findCount")
    public Result<Integer> findCount(){
        int count = collegeService.findCount();
        return Result.success(count,MyConstant.RES_SUCCESS_MESSAGE);
    }
}

