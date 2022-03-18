package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.constant.MyConstant;
import com.example.entity.Course;
import com.example.dao.CourseDao;
import com.example.service.CourseService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import javax.annotation.Resource;

/**
 * (Course)表服务实现类
 *
 * @author wyl
 * @since 2021-10-11 22:26:53
 */
@Service("courseService")
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    @Override
    public Course findById(Integer courseId) {
        return this.courseDao.findById(courseId);
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Course> findAll() {
        return this.courseDao.findAll();
    }

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Course course) {
        course.setCourseNum(RandomUtil.randomString(MyConstant.NUM_BIT));
        course.setNumber(ObjectUtils.isEmpty(course.getNumber())?0:course.getNumber());
        return this.courseDao.insert(course);
    }

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Course course) {
        course.setNumber(ObjectUtils.isEmpty(course.getNumber())?0:course.getNumber());
        return this.courseDao.update(course);
    }

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer courseId) {
        return this.courseDao.deleteById(courseId) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(int[] ids) {
        return courseDao.deleteBatch(ids);
    }

    @Override
    public List<Course> findList(int currentPage, int pageSize, String courseNum, String courseName, int courseState) {
        PageHelper.startPage(currentPage,pageSize);
        return courseDao.findList(courseNum,courseName,courseState);
    }

    @Override
    public List<Course> findByCourseState(int courseState) {
        return courseDao.findByCourseState(courseState);
    }

    @Override
    public List<Course> findByTeacherId(int teacherId) {
        return courseDao.findByTeacherId(teacherId);
    }

}
