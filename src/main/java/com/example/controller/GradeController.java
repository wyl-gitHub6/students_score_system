package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.Classes;
import com.example.entity.Grade;
import com.example.service.ClassesService;
import com.example.service.GradeService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 年级
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Resource
    private GradeService gradeService;

    @Resource
    private ClassesService classesService;


    /**
     * 查询年级
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param gradeNum    年级编号
     * @param gradeName   年级名字
     * @return {@link Result}
     */
    @GetMapping("/findList")
    public Result<PageInfo<Grade>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "gradeNum",defaultValue = "") String gradeNum,
                           @RequestParam(value = "gradeName",defaultValue = "") String gradeName){
        List<Grade> list = gradeService.findList(currentPage, pageSize, gradeNum, gradeName);
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 根据专业ID查询
     *
     * @param professionalId 专业id
     * @return {@link Result}
     */
    @GetMapping("/findByProfessionalId")
    public Result<List<Grade>> findByProfessionalId(@RequestParam("professionalId") int professionalId){
        List<Grade> list = gradeService.findByProfessionalId(professionalId);
        return Result.success(list,MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param gradeId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<Grade> findById(@RequestParam("gradeId") int gradeId) {
        return Result.success(this.gradeService.findById(gradeId),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<Grade>> findAll() {
        return Result.success(this.gradeService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param grade 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Grade grade) {
        Grade gra = gradeService.findByProfessionalIdAndGradeName(grade.getProfessional().getProfessionalId(), grade.getGradeName());
        if (null == gra){
            int i = gradeService.insert(grade);
            if (i > 0){
                return Result.success(MyConstant.RES_INSERT_SUCCESS);
            }
            return Result.error(MyConstant.RES_INSERT_SUCCESS);
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
    public Result<String>  update(@RequestBody Grade grade) {
        Grade gra = gradeService.findByIdAndProfessionalIdAndGradeName(grade.getGradeId(),
                grade.getProfessional().getProfessionalId(),
                grade.getGradeName());
        if (null == gra){
            int i = gradeService.update(grade);
            if (i > 0){
                return Result.success(MyConstant.RES_UPDATE_SUCCESS);
            }
            return Result.error(MyConstant.RES_UPDATE_FAILED);
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
    public Result<String>  deleteById(@RequestParam("gradeId") int gradeId) {

        List<Classes> res = classesService.findByGradeId(gradeId);
        if (!res.isEmpty()) {
            return Result.error(MyConstant.RES_DATA_USE);
        }
        boolean i = gradeService.deleteById(gradeId);
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
    public Result<String>  deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = gradeService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 查询年级数量
     *
     * @return {@link Result}
     */
    @GetMapping("findCount")
    public Result<Integer> findCount(){
        return Result.success(gradeService.findCount(), MyConstant.RES_SUCCESS_MESSAGE);
    }
}

