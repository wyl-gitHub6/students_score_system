package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.example.constant.MyConstant;
import com.example.entity.Classes;
import com.example.entity.Student;
import com.example.dao.StudentDao;
import com.example.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 通过ID查询单条数据
     *
     * @param studentId 主键
     * @return 实例对象
     */
    @Override
    public Student findById(Integer studentId) {
        return this.studentDao.findById(studentId);
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Student> findAll() {
        return this.studentDao.findAll();
    }

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Student student) {
        Classes classes = new Classes();
        classes.setClassesId(MyConstant.ZERO);
        student.setStudentPassword(SecureUtil.md5(MyConstant.DEFAULT_PASSWORD));
        student.setClasses(classes);
        return this.studentDao.insert(student);
    }

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Student student) {
        Student s = studentDao.findById(student.getStudentId());
        if (!s.getStudentPassword().equals(student.getStudentPassword())){
            student.setStudentPassword(SecureUtil.md5(student.getStudentPassword()));
        }
        return this.studentDao.update(student);
    }

    /**
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer studentId) {
        return this.studentDao.deleteById(studentId) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
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
    public Student findByStudentNum(Integer studentNum) {
        return studentDao.findByStudentNum(studentNum);
    }

    @Override
    public Student findByStudentNumAndId(Integer studentNum, Integer studentId) {
        return studentDao.findByStudentNumAndId(studentNum,studentId);
    }

    @Override
    public List<Student> findByClassesId(int currentPage, int pageSize, int classesId,String studentNum, String studentName) {
        PageHelper.startPage(currentPage,pageSize);
        return studentDao.findByClassesId(classesId,studentNum,studentName);
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
}
