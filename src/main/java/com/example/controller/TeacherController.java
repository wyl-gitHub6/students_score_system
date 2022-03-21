package com.example.controller;

import cn.hutool.crypto.SecureUtil;
import com.example.config.SendEmailConfig;
import com.example.entity.Teacher;
import com.example.service.TeacherService;
import com.example.utils.Result;
import com.example.utils.UploadXls;
import com.example.utils.VerCode;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * (Teacher)表控制层
 *
 * @author wyl
 * @since 2021-10-06 18:43:28
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param teacherNum
     * @param teacherName
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "teacherNum",defaultValue = "") String teacherNum,
                           @RequestParam(value = "teacherName",defaultValue = "") String teacherName){
        List<Teacher> list = teacherService.findList(currentPage, pageSize, teacherNum, teacherName);
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param teacherId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("teacherId") int teacherId) {
        return Result.success(this.teacherService.findById(teacherId),"查询成功!");
    }

    /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.teacherService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param teacher 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Teacher teacher) {
        int i = teacherService.insert(teacher);
        if (i > 0){
            return Result.success("添加成功!");
        }
        return Result.error("添加失败!");
    }

    /**
     * 编辑数据
     *
     * @param teacher 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Teacher teacher) {
        int i = teacherService.update(teacher);
        if (i > 0){
            return Result.success("编辑成功!");
        }
        return Result.error("编辑失败!");
    }

    /**
     * 删除数据
     *
     * @param teacherId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("teacherId") int teacherId) {
        boolean i = teacherService.deleteById(teacherId);
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
        boolean i = teacherService.deleteBatch(ids);
        if (i){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 导入
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadXls")
    public Result uploadXls(MultipartFile file) throws IOException {
        int i = teacherService.uploadXls(file);
        return Result.success("共导入"+i+"条数据!");
    }

    /**
     * 登录
     * @param teacherNum
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Result login(@RequestParam("teacherNum") String teacherNum,
                        @RequestParam("password") String password){
        Teacher teacher = teacherService.login(teacherNum, SecureUtil.md5(password));
        if(null != teacher){
            return Result.success(teacher,"登录成功!");
        }
        return Result.error("职工编号或密码错误!");
    }

    /**
     * 查询教师数量
     * @return
     */
    @GetMapping("findCount")
    public Result findCount(){
        int count = teacherService.findCount();
        return Result.success(count,"查询成功!");
    }

    /**
     * 修改密码判断与旧密码加密后是否相同
     * @param teacherId
     * @param password
     * @return
     */
    @GetMapping("/updatePassword")
    public Result updatePassword(@RequestParam("teacherId") int teacherId,
                                 @RequestParam("password") String password){
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher.getTeacherPassword().equals(SecureUtil.md5(password))){
            return Result.success("与旧密码相同");
        }
        return Result.error("密码不同");
    }

    /**
     * 发送邮箱验证码
     * @param teacherNum
     * @param emailAddress
     * @return
     */
    @GetMapping("/sendEmail")
    public Result sendEmail(@RequestParam("teacherNum") String teacherNum,
                            @RequestParam("emailAddress") String emailAddress){
        Teacher teacher = teacherService.findByNum(teacherNum);
        if (null == teacher){
            return Result.error("请输入正确的职工编号！");
        }
        if (!teacher.getTeacherEmail().equals(emailAddress)){
            return Result.error("请输入绑定的邮箱！");
        }
        String code = VerCode.getVerCode();
        SendEmailConfig.sendEmail(emailAddress,code);
        teacher.setCode(code);
        return Result.success(teacher,"发送成功,注意查收！");
    }


}

