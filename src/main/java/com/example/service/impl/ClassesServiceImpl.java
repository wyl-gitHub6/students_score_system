package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.entity.Classes;
import com.example.dao.ClassesDao;
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

    /**
     * 通过ID查询单条数据
     *
     * @param classesId 主键
     * @return 实例对象
     */
    @Override
    public Classes findById(Integer classesId) {
        return this.classesDao.findById(classesId);
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Classes> findAll() {
        return this.classesDao.findAll();
    }

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Classes classes) {
        classes.setClassesNum(RandomUtil.randomString(9));
        /*默认状态为未满--0*/
        classes.setClassesState(0);
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
        return classesDao.findList(classesNum,classesName);
    }

    @Override
    public Classes findByGradeIdAndClassesName(Integer gradeId, String classesName) {
        return classesDao.findByGradeIdAndClassesName(gradeId,classesName);
    }

    @Override
    public Classes findByIdGradeIdAndClassesName(Integer classesId, Integer gradeId, String classesName) {
        return classesDao.findByIdGradeIdAndClassesName(classesId,gradeId,classesName);
    }
}
