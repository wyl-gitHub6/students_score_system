package com.example.controller;

import cn.hutool.crypto.SecureUtil;
import com.example.config.SendEmailConfig;
import com.example.constant.MyConstant;
import com.example.entity.Teacher;
import com.example.service.TeacherService;
import com.example.utils.Result;
import com.example.utils.VerCode;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 教师
 *
 * @author wyl
 * @since 2021-10-06 18:43:28
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private SendEmailConfig sendEmailConfig;


    /**
     * 查询教师列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param teacherNum  老师num
     * @param teacherName 老师名字
     * @return {@link Result}<{@link PageInfo}<{@link Teacher}>>
     */
    @GetMapping("/findList")
    public Result<PageInfo<Teacher>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "teacherNum",defaultValue = "") String teacherNum,
                           @RequestParam(value = "teacherName",defaultValue = "") String teacherName){
        List<Teacher> list = teacherService.findList(currentPage, pageSize, teacherNum, teacherName);
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param teacherId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<Teacher> findById(@RequestParam("teacherId") int teacherId) {
        return Result.success(this.teacherService.findById(teacherId),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<Teacher>> findAll() {
        return Result.success(this.teacherService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param teacher 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Teacher teacher) {
        int i = teacherService.insert(teacher);
        if (i > 0){
            return Result.success(MyConstant.RES_INSERT_SUCCESS);
        }
        return Result.error(MyConstant.RES_INSERT_FAILED);
    }

    /**
     * 编辑数据
     *
     * @param teacher 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Teacher teacher) {
        int i = teacherService.update(teacher);
        if (i > 0){
            return Result.success(MyConstant.RES_UPDATE_SUCCESS);
        }
        return Result.error(MyConstant.RES_UPDATE_FAILED);
    }

    /**
     * 删除数据
     *
     * @param teacherId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("teacherId") int teacherId) {
        boolean i = teacherService.deleteById(teacherId);
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
        boolean i = teacherService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 上传xls
     *
     * @param file 文件
     * @return {@link Result}
     * @throws IOException ioexception
     */
    @PostMapping("/uploadXls")
    public Result<String> uploadXls(MultipartFile file) throws IOException {
        int i = teacherService.uploadXls(file);
        return Result.success("共导入"+i+"条数据!");
    }

    /**
     * 登录
     *
     * @param teacherNum 老师职工编号
     * @param password   密码
     * @return {@link Result}<{@link Teacher}>
     */
    @GetMapping("/login")
    public Result<Teacher> login(@RequestParam("teacherNum") String teacherNum,
                        @RequestParam("password") String password){
        Teacher teacher = teacherService.login(teacherNum, SecureUtil.md5(password));
        if(null != teacher){
            return Result.success(teacher,"登录成功!");
        }
        return Result.error("职工编号或密码错误!");
    }

    /**
     * 查询教师数量
     *
     * @return {@link Result}<{@link Integer}>
     */
    @GetMapping("findCount")
    public Result<Integer> findCount(){
        int count = teacherService.findCount();
        return Result.success(count, MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 修改密码判断与旧密码加密后是否相同
     *
     * @param teacherId 老师id
     * @param password  密码
     * @return {@link Result}<{@link String}>
     */
    @GetMapping("/updatePassword")
    public Result<String> updatePassword(@RequestParam("teacherId") int teacherId,
                                 @RequestParam("password") String password){
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher.getTeacherPassword().equals(SecureUtil.md5(password))){
            return Result.success("与旧密码相同");
        }
        return Result.error("密码不同");
    }

    /**
     * 发送电子邮件
     *
     * @param teacherNum   老师num
     * @param emailAddress 电子邮件地址
     * @return {@link Result}<{@link Teacher}>
     */
    @GetMapping("/sendEmail")
    public Result<Teacher> sendEmail(@RequestParam("teacherNum") String teacherNum,
                            @RequestParam("emailAddress") String emailAddress){
        Teacher teacher = teacherService.findByNum(teacherNum);
        if (null == teacher){
            return Result.error("请输入正确的职工编号！");
        }
        if (!teacher.getTeacherEmail().equals(emailAddress)){
            return Result.error("请输入绑定的邮箱！");
        }
        String code = VerCode.getVerCode();
        sendEmailConfig.sendEmail(emailAddress,code);
        teacher.setCode(code);
        return Result.success(teacher,"发送成功,注意查收！");
    }


}

