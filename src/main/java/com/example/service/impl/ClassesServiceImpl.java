package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.constant.MyConstant;
import com.example.dao.StudentDao;
import com.example.entity.Classes;
import com.example.dao.ClassesDao;
import com.example.entity.Student;
import com.example.service.ClassesService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.annotation.Resource;

/**
 * (Classes)表服务实现类
 *
 * @author wyl
 * @since 2021-10-09 11:02:58
 */
@Service("classesService")
@Transactional(rollbackFor = Exception.class)
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesDao classesDao;

    @Resource
    private StudentDao studentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param classesId 主键
     * @return 实例对象
     */
    @Override
    public Classes findById(Integer classesId) {
        Classes byId = this.classesDao.findById(classesId);
        byId.setClassesNumber(studentDao.findCount(byId.getClassesId()));
        return byId;
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Classes> findAll() {
        List<Classes> list = this.classesDao.findAll();
        for (Classes c:list) {
            c.setClassesNumber(studentDao.findCount(c.getClassesId()));
        }
        return list;
    }

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Classes classes) {
        classes.setClassesNum(RandomUtil.randomString(MyConstant.NUM_BIT));
        /*默认状态为未满--0*/
        classes.setClassesState(MyConstant.ZERO);
        return this.classesDao.insert(classes);
    }

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Classes classes) {
        return this.classesDao.update(classes);
    }

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer classesId) {
        return this.classesDao.deleteById(classesId) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(int[] ids) {
        return classesDao.deleteBatch(ids);
    }

    @Override
    public List<Classes> findList(int currentPage, int pageSize, String classesNum, String classesName) {
        PageHelper.startPage(currentPage,pageSize);
        List<Classes> list = classesDao.findList(classesNum, classesName);
        for (Classes c:list) {
            c.setClassesNumber(studentDao.findCount(c.getClassesId()));
        }
        return list;
    }

    @Override
    public Classes findByGradeIdAndClassesName(Integer gradeId, String classesName) {
        return classesDao.findByGradeIdAndClassesName(gradeId,classesName);
    }

    @Override
    public Classes findByIdGradeIdAndClassesName(Integer classesId, Integer gradeId, String classesName) {
        return classesDao.findByIdGradeIdAndClassesName(classesId,gradeId,classesName);
    }

    @Override
    public List<Classes> findByGradeId(int currentPage, int pageSize, int gradeId, String classesNum, String classesName) {
        PageHelper.startPage(currentPage,pageSize);
        List<Classes> list = classesDao.findByGradeId(gradeId, classesNum, classesName);
        for (Classes c:list) {
            c.setClassesNumber(studentDao.findCount(c.getClassesId()));
        }
        return list;
    }

    @Override
    public int driver(int[] ids, int classesId) {
        int i = 0;
        Classes classes = classesDao.findById(classesId);
        for (int studentId:ids) {
            int count = studentDao.findCount(classesId);
            /*如果班级未满 则添加学生*/
            if (count < classes.getClassesAllNumber()){
                Student student = new Student();
                classes.setClassesId(classesId);
                student.setStudentId(studentId);
                student.setClasses(classes);
                studentDao.update(student);
                i++;
            }else{
                return i;
            }
        }
        return i;
    }

    @Override
    public int findCount() {
        return classesDao.findCount();
    }

    @Override
    public List<Classes> findByTeacherId(int teacherId) {
        List<Classes> list = classesDao.findByTeacherId(teacherId);
        for (Classes c:list) {
            c.setClassesNumber(studentDao.findCount(c.getClassesId()));
        }
        return list;
    }
}
