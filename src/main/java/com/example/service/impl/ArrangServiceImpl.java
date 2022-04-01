package com.example.service.impl;

import com.example.dao.CourseDao;
import com.example.entity.Arrang;
import com.example.dao.ArrangDao;
import com.example.entity.Classes;
import com.example.entity.Course;
import com.example.service.ArrangService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * (Arrang)表服务实现类
 *
 * @author wyl
 * @since 2021-10-13 22:58:58
 */
@Service("arrangService")
@Transactional(rollbackFor = Exception.class)
public class ArrangServiceImpl implements ArrangService {

    @Resource
    private ArrangDao arrangDao;

    @Resource
    private CourseDao courseDao;

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Classes> findClasses(int currentPage,int pageSize,String classesName) {
        PageHelper.startPage(currentPage,pageSize);
        return this.arrangDao.findClasses(classesName);
    }

    /**
     * 新增数据
     *
     * @param arrang 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Arrang arrang) {
        return this.arrangDao.insert(arrang);
    }

    @Override
    public String arrangCourse(int[] courseIdr, int classesId) {

        StringBuilder stringBuilder = new StringBuilder();
        Arrang a = new Arrang();
        Classes classes = new Classes();
        classes.setClassesId(classesId);
        Course course = new Course();
        /*遍历课程*/
        for (int courseId:courseIdr) {
            Arrang arrang = arrangDao.findByCourseIdAndClassesId(courseId,classesId);
            if (null != arrang){
                stringBuilder.append(arrang.getClasses().getClassesName())
                        .append("已有").append(arrang.getCourse().getCourseName()).append("课程！");
            }else {
                Course cou = courseDao.findById(courseId);
                course.setCourseId(courseId);
                a.setClasses(classes);
                a.setCourse(course);
                arrangDao.insert(a);
                stringBuilder.append(cou.getCourseName()).append("选择成功!");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Classes> findByClassesId(int classesId) {
        return arrangDao.findByClassesId(classesId);
    }

    @Override
    public boolean delete(int courseId, int classesId) {
        return arrangDao.delete(courseId,classesId);
    }

    @Override
    public List<Classes> findByCourseId(int courseId) {
        return arrangDao.findByCourseId(courseId);
    }
}
