package com.example.controller;

import com.example.entity.Grade;
import com.example.service.GradeService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Grade)表控制层
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Resource
    private GradeService gradeService;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param gradeNum
     * @param gradeName
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "gradeNum",defaultValue = "") String gradeNum,
                           @RequestParam(value = "gradeName",defaultValue = "") String gradeName){
        List<Grade> list = gradeService.findList(currentPage, pageSize, gradeNum, gradeName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 根据专业ID查询
     * @param professionalId
     * @return
     */
    @GetMapping("/findByProfessionalId")
    public Result findByProfessionalId(@RequestParam("professionalId") int professionalId){
        List<Grade> list = gradeService.findByProfessionalId(professionalId);
        return Result.success(list,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param gradeId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("gradeId") int gradeId) {
        return Result.success(this.gradeService.findById(gradeId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.gradeService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param grade 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Grade grade) {
        Grade gra = gradeService.findByProfessionalIdAndGradeName(grade.getProfessional().getProfessionalId(), grade.getGradeName());
        if (null == gra){
            int i = gradeService.insert(grade);
            if (i > 0){
                return Result.success("添加成功!");
            }
            return Result.error("添加失败!");
        }
        return Result.error("该专业下已有此年级!");
    }

    /**
     * 编辑数据
     *
     * @param grade 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Grade grade) {
        Grade gra = gradeService.findByIdAndProfessionalIdAndGradeName(grade.getGradeId(),
                grade.getProfessional().getProfessionalId(),
                grade.getGradeName());
        if (null == gra){
            int i = gradeService.update(grade);
            if (i > 0){
                return Result.success("编辑成功!");
            }
            return Result.error("编辑失败!");
        }
        return Result.error("该专业下已有此年级!");
    }

    /**
     * 删除数据
     *
     * @param gradeId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("gradeId") int gradeId) {
        boolean i = gradeService.deleteById(gradeId);
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
        boolean i = gradeService.deleteBatch(ids);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 查询年级数量
     * @return
     */
    @GetMapping("findCount")
    public Result findCount(){
        int count = gradeService.findCount();
        return Result.success(count,"查询成功!");
    }
}

