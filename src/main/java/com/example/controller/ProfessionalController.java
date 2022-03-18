package com.example.controller;

import com.example.entity.Professional;
import com.example.service.ProfessionalService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Professional)表控制层
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
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param professionalNum
     * @param professionalName
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "professionalNum",defaultValue = "") String professionalNum,
                           @RequestParam(value = "professionalName",defaultValue = "") String professionalName){
        List<Professional> list = professionalService.findList(currentPage, pageSize, professionalNum, professionalName);
        PageInfo<Professional> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    @GetMapping("/findByCollegeId")
    public Result findByCollegeId(@RequestParam("collegeId") int collegeId){
        List<Professional> list = professionalService.findByCollegeId(collegeId);
        return Result.success(list,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param professionalId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("professionalId") int professionalId) {
        return Result.success(this.professionalService.findById(professionalId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.professionalService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param professional 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Professional professional) {
        Professional pro = professionalService.findByCollegeIdAndProfessionalName(professional.getCollege().getCollegeId(), professional.getProfessionalName());
        if (null == pro){
            int i = professionalService.insert(professional);
            if (i > 0){
                return Result.success("添加成功!");
            }
            return Result.error("添加失败!");
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
    public Result update(@RequestBody Professional professional) {
        Professional pro = professionalService.findByIdAndCollegeIdAndProfessionalName(professional.getProfessionalId(),
                professional.getCollege().getCollegeId(),
                professional.getProfessionalName());
        if (null == pro){
            int i = professionalService.update(professional);
            if (i > 0){
                return Result.success("编辑成功!");
            }
            return Result.error("编辑失败!");
        }
        return Result.error("该院系下已有此专业!");
    }

    /**
     * 删除数据
     *
     * @param professionalId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("professionalId") int professionalId) {
        boolean i = professionalService.deleteById(professionalId);
        if (i){
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
        boolean i = professionalService.deleteBatch(ids);
        if (i){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 查询专业数量
     * @return
     */
    @GetMapping("findCount")
    public Result findCount(){
        int count = professionalService.findCount();
        return Result.success(count,"查询成功!");
    }
}

