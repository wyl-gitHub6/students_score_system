package com.example.controller;

import cn.hutool.crypto.SecureUtil;
import com.example.config.SendEmailConfig;
import com.example.entity.Classes;
import com.example.entity.Student;
import com.example.service.StudentService;
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
 * 学生
 *
 * @author wyl
 * @since 2021-10-10 11:26:20
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    /**
     * 查询学生
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param studentNum  学生编号
     * @param studentName 学生名字
     * @return {@link Result}
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "studentNum",defaultValue = "") String studentNum,
                           @RequestParam(value = "studentName",defaultValue = "") String studentName){
        List<Student> list = studentService.findList(currentPage, pageSize, studentNum, studentName);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param studentId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("studentId") int studentId) {
        return Result.success(this.studentService.findById(studentId),"查询成功!");
    }

    /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.studentService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param student 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Student student) {
        Student stu = studentService.findByStudentNum(student.getStudentNum());
        if (null == stu){
            int i = studentService.insert(student);
            if (i > 0){
                return Result.success("添加成功!");
            }
            return Result.error("添加失败!");
        }
        return Result.error("学号重复!");
    }

    /**
     * 编辑数据
     *
     * @param student 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Student student) {
        int i = studentService.update(student);
        if (i > 0){
            return Result.success("编辑成功!");
        }
        return Result.error("编辑失败!");
    }

    /**
     * 删除数据
     *
     * @param studentId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("studentId") int studentId) {
        boolean i = studentService.deleteById(studentId);
        if (i){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 删除批处理
     *
     * @param ids id
     * @return {@link Result}
     */
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = studentService.deleteBatch(ids);
        if (i){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 上传xls
     *
     * @param file 文件
     * @return {@link Result}
     * @throws IOException ioexception
     */
    @PostMapping("/uploadXls")
    public Result uploadXls(MultipartFile file) throws IOException {
        int i = studentService.uploadXls(file);
        return Result.success("共导入"+i+"条数据!");
    }

    /**
     * 根据班级查询
     *
     * @param classesId   班级id
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param studentNum  学生学号
     * @param studentName 学生名字
     * @return {@link Result}
     */
    @GetMapping("/findByClassesId")
    public Result findByClassesId(@RequestParam("classesId") int classesId,
                                  @RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                  @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                  @RequestParam(value = "studentNum",defaultValue = "") String  studentNum,
                                  @RequestParam(value = "studentName",defaultValue = "") String studentName){
        List<Student> list = studentService.findByClassesId(currentPage,pageSize,classesId,studentNum,studentName);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        if (list.isEmpty()){
            return Result.error("暂无数据！");
        }
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 登录
     *
     * @param studentNum 学生学号
     * @param password   密码
     * @return {@link Result}
     */
    @GetMapping("/login")
    public Result login(@RequestParam("studentNum") String studentNum,
                        @RequestParam("password") String password){
        Student student = studentService.login(studentNum, SecureUtil.md5(password));
        if(null != student){
            return Result.success(student,"登录成功!");
        }
        return Result.error("学号或密码错误!");
    }

    /**
     * 必修课录入成绩时查询该课程没成绩的学生
     *
     * @param classesId 班级id
     * @param courseId  课程id
     * @return {@link Result}
     */
    @GetMapping("/findByClasses")
    public Result findByClasses(@RequestParam("classesId") int classesId,
                                @RequestParam("courseId") int courseId){
        List<Student> list = studentService.findByClasses(classesId,courseId);
        if (list.isEmpty()){
            return Result.error("暂无数据！");
        }
        return Result.success(list,"查询成功!");
    }

    /**
     * 查询学生数量
     *
     * @return {@link Result}
     */
    @GetMapping("findCount")
    public Result findCount(){
        int count = studentService.findCount();
        return Result.success(count,"查询成功!");
    }

    /**
     * 修改密码判断与旧密码加密后是否相同
     *
     * @param studentId 学生证
     * @param password  密码
     * @return {@link Result}
     */
    @GetMapping("/updatePassword")
    public Result updatePassword(@RequestParam("studentId") int studentId,
                                 @RequestParam("password") String password){
        Student student = studentService.findById(studentId);
        if (student.getStudentPassword().equals(SecureUtil.md5(password))){
            return Result.success("与旧密码相同");
        }
        return Result.error("密码不同");
    }

    /**
     * 发送电子邮件
     *
     * @param studentNum   学生学号
     * @param emailAddress 电子邮件地址
     * @return {@link Result}
     */
    @GetMapping("/sendEmail")
    public Result sendEmail(@RequestParam("studentNum") String studentNum,
                            @RequestParam("emailAddress") String emailAddress){
        Student student = studentService.findByStudentNum(studentNum);
        if (null == student){
            return Result.error("请输入正确的学号！");
        }
        if (!student.getStudentEmail().equals(emailAddress)){
            return Result.error("请输入绑定的邮箱！");
        }
        String code = VerCode.getVerCode();
        SendEmailConfig.sendEmail(emailAddress,code);
        student.setCode(code);
        return Result.success(student,"发送成功,注意查收！");
    }
}

