package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.Professional;
import com.example.service.ProfessionalService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专业
 *
 * @author wyl
 * @since 2021-10-07 18:02:09
 */
@RestController
@RequestMapping("/professional")
public class ProfessionalController {

    @Resource
    private ProfessionalService professionalService;


    /**
     * 查询专业
     *
     * @param currentPage      当前页面
     * @param pageSize         页面大小
     * @param professionalNum  专业编号
     * @param professionalName 专业名称
     * @return {@link Result}
     */
    @GetMapping("/findList")
    public Result<PageInfo<Professional>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "professionalNum",defaultValue = "") String professionalNum,
                           @RequestParam(value = "professionalName",defaultValue = "") String professionalName){
        List<Professional> list = professionalService.findList(currentPage, pageSize, professionalNum, professionalName);
        PageInfo<Professional> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo, MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询通过大学id
     *
     * @param collegeId 大学id
     * @return {@link Result}<{@link List}<{@link Professional}>>
     */
    @GetMapping("/findByCollegeId")
    public Result<List<Professional>> findByCollegeId(@RequestParam("collegeId") int collegeId){
        List<Professional> list = professionalService.findByCollegeId(collegeId);
        return Result.success(list,MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param professionalId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<Professional> findById(@RequestParam("professionalId") int professionalId) {
        return Result.success(this.professionalService.findById(professionalId),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<Professional>> findAll() {
        return Result.success(this.professionalService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param professional 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Professional professional) {
        Professional pro = professionalService.findByCollegeIdAndProfessionalName(professional.getCollege().getCollegeId(), professional.getProfessionalName());
        if (null == pro){
            int i = professionalService.insert(professional);
            if (i > 0){
                return Result.success(MyConstant.RES_INSERT_SUCCESS);
            }
            return Result.error(MyConstant.RES_INSERT_SUCCESS);
        }
        return Result.error("该院系下已有此专业!");
    }

    /**
     * 编辑数据
     *
     * @param professional 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Professional professional) {
        Professional pro = professionalService.findByIdAndCollegeIdAndProfessionalName(professional.getProfessionalId(),
                professional.getCollege().getCollegeId(),
                professional.getProfessionalName());
        if (null == pro){
            int i = professionalService.update(professional);
            if (i > 0){
                return Result.success(MyConstant.RES_UPDATE_SUCCESS);
            }
            return Result.error(MyConstant.RES_UPDATE_FAILED);
        }
        return Result.error("该院系下已有此专业!");
    }

    /**
     * 删除通过id
     *
     * @param professionalId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("professionalId") int professionalId) {
        boolean i = professionalService.deleteById(professionalId);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 删除批处理
     *
     * @param ids id
     * @return {@link Result}<{@link String}>
     */
    @DeleteMapping("deleteBatch")
    public Result<String> deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = professionalService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 查询专业数量
     *
     * @return {@link Result}<{@link Integer}>
     */
    @GetMapping("findCount")
    public Result<Integer> findCount(){
        return Result.success(professionalService.findCount(),MyConstant.RES_SUCCESS_MESSAGE);
    }
}

