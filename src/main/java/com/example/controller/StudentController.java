package com.example.controller;

import com.example.entity.Classes;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.utils.Result;
import com.example.utils.UploadXls;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * (Student)表控制层
 *
 * @author wyl
 * @since 2021-10-10 11:26:20
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "studentNum",defaultValue = "") String studentNum,
                           @RequestParam(value = "studentName",defaultValue = "") String studentName){
        List<Student> list = studentService.findList(currentPage, pageSize, studentNum, studentName);
        PageInfo pageInfo = new PageInfo(list);
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
            Classes classes = new Classes();
            classes.setClassesId(0);
            student.setClasses(classes);
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
        Student stu = studentService.findByStudentNumAndId(student.getStudentNum(), student.getStudentId());
        if (null == stu){
            int i = studentService.update(student);
            if (i > 0){
                return Result.success("编辑成功!");
            }
            return Result.error("编辑失败!");
        }
        return Result.error("学号重复!");
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
        boolean i = studentService.deleteBatch(ids);
        if (i == true){
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
        HSSFSheet sheet = UploadXls.uploadXls(file);
        // 4.从表中获取到行数据  从第二行开始 到 最后一行  getLastRowNum() 获取最后一行的下标
        int lastRowNum = sheet.getLastRowNum();
        int j = 0;
        for (int i = 1; i <= lastRowNum; i++) {
            //通过下标获取行
            HSSFRow row = sheet.getRow(i);
            //从行中获取数据

            /**
             * getNumericCellValue() 获取数字
             * getStringCellValue 获取String
             */
            String studentName = row.getCell(0).getStringCellValue();
            double num = row.getCell(1).getNumericCellValue();
            double age = row.getCell(2).getNumericCellValue();
            double sex = row.getCell(3).getNumericCellValue();
            String email = row.getCell(4).getStringCellValue();
            String phone = row.getCell(5).getStringCellValue();
            String national = row.getCell(6).getStringCellValue();
            String card = row.getCell(7).getStringCellValue();
            double classesId = row.getCell(8).getNumericCellValue();
            Student s = new Student();
            s.setStudentNum((int)num);
            Student student = studentService.findByStudentNum(s.getStudentNum());
            if(student == null) {
                j++;
                s.setStudentAge((int)age);
                s.setStudentSex((int)sex);
                s.setStudentEmail(email);
                s.setStudentPhone(phone);
                s.setStudentNational(national);
                s.setStudentCard(card);
                s.setStudentName(studentName);
                Classes classes = new Classes();
                classes.setClassesId((int)classesId);
                s.setClasses(classes);
                studentService.insert(s);
            }
        }
        return Result.success("共导入"+j+"条数据!");
    }

    /**
     * 根据班级查询
     * @param classesId
     * @return
     */
    @GetMapping("/findByClassesId")
    public Result findByClassesId(@RequestParam("classesId") int classesId,
                                  @RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                  @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                  @RequestParam(value = "studentNum",defaultValue = "") String  studentNum,
                                  @RequestParam(value = "studentName",defaultValue = "") String studentName){
        List<Student> list = studentService.findByClassesId(currentPage,pageSize,classesId,studentNum,studentName);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }
}

