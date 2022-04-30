package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.constant.MyConstant;
import com.example.entity.Teacher;
import com.example.dao.TeacherDao;
import com.example.service.TeacherService;
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
 * (Teacher)表服务实现类
 *
 * @author wyl
 * @since 2021-10-06 18:43:28
 */
@Service("teacherService")
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> findList(int currentPage, int pageSize, String teacherNum, String teacherName) {
        PageHelper.startPage(currentPage,pageSize);
        return teacherDao.findList(teacherNum,teacherName);
    }

    @Override
    public boolean deleteBatch(int[] ids) {
        return teacherDao.deleteBatch(ids);
    }

    @Override
    public Teacher login(String teacherNum, String password) {
        return teacherDao.findByTeacherNumAndPassword(teacherNum,password);
    }

    @Override
    public int findCount() {
        return teacherDao.findCount();
    }

    @Override
    public Teacher findByNum(String teacherNum) {
        return teacherDao.findByTeacherNum(teacherNum);
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
              从行中获取数据
              getNumericCellValue() 获取数字
              getStringCellValue 获取String
             */
            String name = row.getCell(0).getStringCellValue();
            int age = (int)row.getCell(1).getNumericCellValue();
            int sex = (int)row.getCell(2).getNumericCellValue();
            String email = row.getCell(3).getStringCellValue();
            String phone = row.getCell(4).getStringCellValue();
            String national = row.getCell(5).getStringCellValue();
            String card = row.getCell(6).getStringCellValue();
            Teacher t = new Teacher();
            t.setTeacherName(name);
            t.setTeacherAge(age);
            t.setTeacherSex(sex);
            t.setTeacherEmail(email);
            t.setTeacherPhone(phone);
            t.setTeacherNational(national);
            t.setTeacherCard(card);
            t.setTeacherPassword(SecureUtil.md5(MyConstant.DEFAULT_PASSWORD));
            t.setTeacherNum(RandomUtil.randomString(MyConstant.NUM_BIT));
            teacherDao.insert(t);
        }
        return i-1;
    }

    @Override
    public Teacher findById(Integer teacherId) {
        return this.teacherDao.findById(teacherId);
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherDao.findAll();
    }

    @Override
    public int insert(Teacher teacher) {
        teacher.setTeacherNum(RandomUtil.randomString(MyConstant.NUM_BIT));
        teacher.setTeacherPassword(SecureUtil.md5(MyConstant.DEFAULT_PASSWORD));
        return this.teacherDao.insert(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        Teacher t = teacherDao.findById(teacher.getTeacherId());
        if (!t.getTeacherPassword().equals(teacher.getTeacherPassword())){
            teacher.setTeacherPassword(SecureUtil.md5(teacher.getTeacherPassword()));
        }
        return this.teacherDao.update(teacher);
    }

    @Override
    public boolean deleteById(Integer teacherId) {
        return this.teacherDao.deleteById(teacherId) > 0;
    }

}
