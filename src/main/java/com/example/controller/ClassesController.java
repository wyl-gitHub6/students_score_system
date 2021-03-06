package com.example.controller;

import com.example.constant.MyConstant;
import com.example.entity.Classes;
import com.example.entity.Student;
import com.example.service.ClassesService;
import com.example.service.StudentService;
import com.example.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级
 *
 * @author wyl
 * @since 2021-10-09 11:02:58
 */
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Resource
    private ClassesService classesService;

    @Resource
    private StudentService studentService;

    @GetMapping("/findList")
    public Result<PageInfo<Classes>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "classesNum",defaultValue = "") String classesNum,
                           @RequestParam(value = "classesName",defaultValue = "") String classesName){
        List<Classes> list = classesService.findList(currentPage, pageSize, classesNum, classesName);
        PageInfo<Classes> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param classesId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<Classes> findById(@RequestParam("classesId") int classesId) {
        return Result.success(this.classesService.findById(classesId),MyConstant.RES_SUCCESS_MESSAGE);
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<Classes>> findAll() {
        return Result.success(this.classesService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param classes 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Classes classes) {
        Classes cla = classesService.findByGradeIdAndClassesName(classes.getGrade().getGradeId(), classes.getClassesName());
        if (null == cla){
            int i = classesService.insert(classes);
            if (i > 0){
                return Result.success(MyConstant.RES_INSERT_SUCCESS);
            }
            return Result.error(MyConstant.RES_INSERT_SUCCESS);
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
    public Result<String> update(@RequestBody Classes classes) {
        Classes cla = classesService.findByIdGradeIdAndClassesName(classes.getClassesId(),
                classes.getGrade().getGradeId(), classes.getClassesName());
        if (null == cla){
            int i = classesService.update(classes);
            if (i > 0){
                return Result.success(MyConstant.RES_UPDATE_SUCCESS);
            }
            return Result.error(MyConstant.RES_UPDATE_FAILED);
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
    public Result<String> deleteById(@RequestParam("classesId") int classesId) {
        List<Student> res = studentService.findByClassesId(classesId);
        if (!res.isEmpty()){
            return Result.error(MyConstant.RES_DATA_USE);
        }

        boolean i = classesService.deleteById(classesId);
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
        boolean i = classesService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 根据所在年级查询
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param gradeId     年级id
     * @param classesNum  班级编号
     * @param classesName 班级名称
     * @return {@link Result}<{@link PageInfo}<{@link Classes}>>
     */
    @GetMapping("/findByGradeId")
    public Result<PageInfo<Classes>> findByGradeId(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "gradeId") int gradeId,
                           @RequestParam(value = "classesNum",defaultValue = "") String classesNum,
                           @RequestParam(value = "classesName",defaultValue = "") String classesName){
        List<Classes> list = classesService.findByGradeId(currentPage, pageSize,gradeId, classesNum, classesName);
        PageInfo<Classes> pageInfo = new PageInfo(list);
        return Result.success(pageInfo, MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 分班
     *
     * @param ids       学生数组
     * @param classesId 班级id
     * @return {@link Result}
     */
    @GetMapping("/driver")
    public Result<String> driver(@RequestParam("ids") int[] ids,
                         @RequestParam("classesId") int classesId){
        int i = classesService.driver(ids, classesId);
        return Result.success("本次共为"+i+"位同学分班");
    }

    /**
     * 查询班级数量
     *
     * @return {@link Result}
     */
    @GetMapping("findCount")
    public Result<Integer> findCount(){
        return Result.success(classesService.findCount(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询教师所带班级
     *
     * @param teacherId 老师id
     * @return {@link Result}<{@link List}<{@link Classes}>>
     */
    @GetMapping("/findByTeacherId")
    public Result<List<Classes>> findByTeacherId(@RequestParam("teacherId") int teacherId){
        List<Classes> classes = classesService.findByTeacherId(teacherId);
        return Result.success(classes,MyConstant.RES_SUCCESS_MESSAGE);
    }
}

