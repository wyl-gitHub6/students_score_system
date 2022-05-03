package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.example.constant.MyConstant;
import com.example.entity.Classes;
import com.example.entity.Student;
import com.example.dao.StudentDao;
import com.example.service.StudentService;
import com.example.utils.OverAll;
import com.example.utils.UploadXls;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

/**
 * (Student)表服务实现类
 *
 * @author wyl
 * @since 2021-10-10 11:26:21
 */
@Service("studentService")
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private OverAll overAll;

    @Override
    public Student findById(Integer studentId) {
        return this.studentDao.findById(studentId);
    }

    @Override
    public List<Student> findAll() {
        return this.studentDao.findAll();
    }

    @Override
    public int insert(Student student) {
        Classes classes = new Classes();
        classes.setClassesId(MyConstant.ZERO);
        student.setStudentPassword(SecureUtil.md5(MyConstant.DEFAULT_PASSWORD));
        student.setClasses(classes);
        return this.studentDao.insert(student);
    }

    @Override
    public int update(Student student) {
        Student s = studentDao.findById(student.getStudentId());
        if (!s.getStudentPassword().equals(student.getStudentPassword())){
            student.setStudentPassword(SecureUtil.md5(student.getStudentPassword()));
        }
        return this.studentDao.update(student);
    }

    @Override
    public boolean deleteById(Integer studentId) {
        return this.studentDao.deleteById(studentId) > 0;
    }

    @Override
    public boolean deleteBatch(int[] ids) {
        return studentDao.deleteBatch(ids);
    }

    @Override
    public List<Student> findList(int currentPage, int pageSize, String studentNum, String studentName) {
        PageHelper.startPage(currentPage,pageSize);
        return studentDao.findList(studentNum,studentName);
    }

    @Override
    public Student findByStudentNum(String studentNum) {
        return studentDao.findByStudentNum(studentNum);
    }

    @Override
    public List<Student> findByClassesId(int currentPage, int pageSize, int classesId,String studentNum, String studentName) {
        PageHelper.startPage(currentPage,pageSize);
        return studentDao.findByClassesList(classesId,studentNum,studentName);
    }

    @Override
    public Student login(String studentNum, String password) {
        return studentDao.findByStudentNumAndPassword(studentNum,password);
    }

    @Override
    public List<Student> findByClasses(int classesId,int courseId) {
        return studentDao.findByClasses(classesId,courseId);
    }

    @Override
    public int findCount() {
        return studentDao.getCount();
    }

    @Override
    public int uploadXls(MultipartFile file) throws IOException {
        HSSFSheet sheet = UploadXls.uploadXls(file);
        // 4.从表中获取到行数据  从第二行开始 到 最后一行  getLastRowNum() 获取最后一行的下标
        int lastRowNum = sheet.getLastRowNum();
        int i;
        for (i = 1; i <= lastRowNum; i++) {
            //通过下标获取行
            HSSFRow row = sheet.getRow(i);

            /*
              getNumericCellValue() 获取数字
              getStringCellValue 获取String
             */
            String studentName = row.getCell(0).getStringCellValue();
            int age = (int)row.getCell(1).getNumericCellValue();
            int sex = (int)row.getCell(2).getNumericCellValue();
            String email = row.getCell(3).getStringCellValue();
            String phone = row.getCell(4).getStringCellValue();
            String national = row.getCell(5).getStringCellValue();
            String card = row.getCell(6).getStringCellValue();
            int classesId = (int)row.getCell(7).getNumericCellValue();

            Student s = new Student();
            /*
              获取学号
             */
            String studentNum = overAll.getStudentNum(classesId);

            s.setStudentNum(studentNum);
            s.setStudentAge(age);
            s.setStudentSex(sex);
            s.setStudentEmail(email);
            s.setStudentPhone(phone);
            s.setStudentNational(national);
            s.setStudentCard(card);
            s.setStudentName(studentName);
            s.setStudentPassword(SecureUtil.md5(MyConstant.DEFAULT_PASSWORD));
            Classes classes = new Classes();
            classes.setClassesId(classesId);
            s.setClasses(classes);
            studentDao.insert(s);
        }
        return i-1;
    }

    @Override
    public List<Student> findByClassesId(int classesId) {
        return studentDao.findByClassesId(classesId);
    }
}
